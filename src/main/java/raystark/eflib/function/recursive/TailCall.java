package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.function.recursive.RS.TailCallS;

import static raystark.eflib.function.recursive.TailCallUtil.VOID_COMPLETED;

/**
 * 末尾再帰呼び出しを表すインターフェースです。
 *
 * <p>このインターフェースは{@link TailCall#next}を関数メソッドに持つ関数型インターフェースです。
 *
 * <p>このインターフェースを使って末尾再帰関数を定義することでコールスタックによるスタック領域のメモリ消費をTailCallインスタンスによる
 * ヒープ領域のメモリ消費に変換することが出来ます。
 * 末尾再帰関数はループに展開され、繰り返し{@link TailCall#next}を呼び出すことで末尾再帰関数を実行します。
 * ループの実行中に過去に評価されたTailCallのメモリはGCに回収される可能性があるため、
 * 末尾再帰関数のネストが深い場合に通常の再帰関数より少ないメモリ消費で実行できる可能性があります。
 *
 * <p>末尾再帰でない再帰関数を定義した場合は最適化されない事に注意してください。そのような再帰関数はコールスタックを保持しておく必要があり、
 * 再帰呼び出しをループに展開することが出来ません。
 *
 * <p>末尾再帰関数の実行は{@link TailCall#evaluate}の実行まで遅延されます。
 *
 * @param <T> 末尾再帰関数の戻り値の型
 */
@FunctionalInterface
public interface TailCall<T> {
    @NotNull
    TailCall<T> next();

    @Nullable
    default T evaluate() {
        for(TailCall<T> tailCall = this; ; tailCall = tailCall.next())
            if(isCompleted(tailCall)) return tailCall.evaluate();
    }

    @FunctionalInterface
    interface Completed<T> extends TailCall<T> {

        @Override
        @NotNull
        default TailCall<T> next() {
            return this;
        }

        @Override
        @Nullable
        T evaluate();
    }

    static boolean isCompleted(@NotNull TailCall<?> tailCall) {
        return tailCall instanceof Completed;
    }

    @NotNull
    static <T> TailCall<T> call(@NotNull TailCallS<T> tailCall) {
        return tailCall::get;
    }

    @NotNull
    static <T> Completed<T> complete(@Nullable T value) {
        return () -> value;
    }

    @NotNull
    static Completed<?> complete() {
        return VOID_COMPLETED;
    }
}
