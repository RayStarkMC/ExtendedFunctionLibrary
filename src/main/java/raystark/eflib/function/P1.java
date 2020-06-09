package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

/**
 * 型T1の述語です。またBoolean型を返す関数です。
 *
 * <p>このインターフェースは{@link P1#test}を関数メソッドに持つ関数型インターフェースです。
 *
 * <p>このインターフェースは{@link Predicate}に対応します。{@link Predicate}に変換する場合次のイディオムが使えます。
 *
 * <pre>{@code
 *  P1<String> p = String::isEmpty;
 *  Predicate<String> p2 = f::test;
 * }</pre>
 *
 * <p>{@link Predicate}と等価なメソッドに加えて合成関数を作成するメソッド、ラムダやメソッド参照からインスタンスを作成するメソッドが定義されています。
 *
 * @param <T1> 第一引数の型
 */
@FunctionalInterface
public interface P1<T1> extends F1<T1, Boolean> {
    boolean test(T1 t1);

    @Override
    @NotNull
    default Boolean apply(T1 t1) {
        return test(t1);
    }

    @NotNull
    default P1<T1> and(@NotNull P1<? super T1> other) {
        return t1 -> test(t1) && other.test(t1);
    }

    @NotNull
    default P1<T1> or(@NotNull P1<? super T1> other) {
        return t1 -> test(t1) && other.test(t1);
    }

    @NotNull
    default P1<T1> not() {
        return t1 -> !test(t1);
    }

    @Override
    @NotNull
    default <V1> P1<V1> compose1(@NotNull F1<? super V1, ? extends T1> before) {
        return v1 -> test(before.apply(v1));
    }

    @NotNull
    static <T1> P1<T1> of(@NotNull P1<T1> p1) {
        return p1;
    }
}
