package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.function.recursive.TailCallHelper.Completed;

import static raystark.eflib.function.recursive.TailCallHelper.isCompleted;

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
 * <p>最適化された末尾再帰関数を定義するためには再帰関数のreturn文で以下の2つのメソッドを利用してTailCallを実装します。
 * <ul>
 *     <li>{@link TailCall#call}: メソッドの再帰呼び出しを行う実装を返します。引数には次に呼び出すメソッドを実行するSupplierを渡します。</li>
 *     <li>{@link TailCall#complete}: 末尾再帰関数の評価後の値を保持した実装を返します。再帰を抜けるために必ず利用します。</li>
 * </ul>
 *
 * <p>以下に従うことで最適化された末尾再帰関数を定義できます。
 * <ol>
 *     <li>通常の末尾再帰関数を定義します。</li>
 *     <li>関数の戻り値の型をTailCallでラップします。</li>
 *     <li>終了条件を満たす場合の戻り値を{@link TailCall#complete}メソッドでラップします。</li>
 *     <li>再帰条件を満たす場合のメソッド呼び出しを{@link TailCall#call}メソッドでラップします。</li>
 * </ol>
 *
 * <p>また、以下の点に注意してください。
 * <ul>
 *     <li>return文には{@link TailCall#complete}、{@link TailCall#call}のみを利用してください。</li>
 *     <li>return文以外で{@link TailCall#complete}、{@link TailCall#call}を利用しないでください。</li>
 *     <li>再帰関数内で直接自身を呼び出さないでください。</li>
 * </ul>
 *
 * <p>以下に正しく実装された一般フィボナッチ数列の第n項を求める末尾再帰関数fibを示します。
 * <pre>{@code
 *      public static void main(String[] args) {
 *          System.out.println(fib(BigInteger.ZERO, BigInteger.ONE, BigInteger.valueOf(30000)).evaluate());
 *      }
 *
 *      public static TailCall<BigInteger> fib(BigInteger t0, BigInteger t1, BigInteger n) {
 *          if(n.compareTo(BigInteger.ZERO) < 0) throw new IllegalArgumentException();
 *          if(n.compareTo(BigInteger.ZERO) == 0) return complete(t0);
 *          if(n.compareTo(BigInteger.ONE) == 0) return complete(t1);
 *
 *          return call(() -> fib(t1, t0.add(t1), n.subtract(BigInteger.ONE)));
 *      }
 * }</pre>
 *
 * <p>末尾再帰関数の実行は{@link TailCall#evaluate}の実行まで遅延されます。
 *
 * @param <T> 末尾再帰関数の戻り値の型
 */
@FunctionalInterface
public interface TailCall<T> {

    /**
     * このTailCallの次に評価されるTailCallを返します。
     *
     * @return 次に呼び出されるTailCall
     */
    @NotNull
    TailCall<T> next();

    /**
     * このTailCallを評価し、値を取得します。
     *
     * <p>メソッドの再起呼び出しはループに変換されます。
     * 再帰の終了条件が満たされない場合、このメソッドは無限ループに陥る可能性があります。
     *
     * <p>いずれかの関数の評価時にスローされた例外は呼び出し元に中継されます。
     *
     * @return 末尾再帰関数の戻り値
     */
    @Nullable
    default T evaluate() {
        for(TailCall<T> tailCall = this; ; tailCall = tailCall.next())
            if(isCompleted(tailCall)) return tailCall.evaluate();
    }

    /**
     * 再帰的にメソッドを呼び出すTailCallを実装します。
     *
     * <p>引数のsupplierの中で関数を再帰的に呼び出してください。
     *
     * @param supplier 次に呼び出されるTailCallのSupplier
     * @param <T> TailCallの戻り値の型
     * @return このTailCallの次に呼び出されるTailCall
     */
    @NotNull
    static <T> TailCall<T> call(@NotNull TailCallS<T> supplier) {
        return supplier::get;
    }

    /**
     * 末尾再帰関数の評価値を返すTailCallを実装します。
     *
     * <p>引数には末尾再帰関数の戻り値を渡してください。
     *
     * @param value 末尾再帰関数の戻り値
     * @param <T> 末尾再帰関数の戻り値の型
     * @return 再帰が完了したTailCall
     */
    @NotNull
    static <T> TailCall<T> complete(@Nullable T value) {
        return (Completed<T>)() -> value;
    }

    @FunctionalInterface
    interface TailCallS<T> {
        @NotNull
        TailCall<T> get();
    }
}
