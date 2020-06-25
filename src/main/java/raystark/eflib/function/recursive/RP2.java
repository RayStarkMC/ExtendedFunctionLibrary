package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.function.P2;

/**
 * 再帰的に定義された型T1, 型T2の述語です。
 *
 * <p>このインターフェースは{@link RP2#test(T1, T2, RP2)}を関数メソッドに持つ関数型インターフェースです。
 *
 * <p>このインターフェースは二変数述語{@link P2}を再帰的ラムダ式で定義するためのインターフェースです。
 * このインターフェース自体のインスタンスからは部分適用等の{@link P2}で定義されるdefaultメソッドにはアクセスできません。
 * {@link RP2#of}メソッドを使うことで再帰的ラムダ式から{@link P2}型の関数オブジェクトを作成できます。
 *
 * @param <T1> 第一引数の型
 * @param <T2> 第二引数の型
 */
@FunctionalInterface
public interface RP2<T1, T2> {

    /**
     * 二変数述語を再帰的に定義します。
     *
     * <p>selfにこの関数自身を渡した場合、二変数述語として引数をこの関数に適用します。
     * selfに自身以外を渡した場合の動作は未定義です。
     *
     * <p>再帰的ラムダ式では{@link BooleanTailCall#call}に渡すSupplierの中でselfを参照し、{@link RP2#test(T1, T2)}メソッドを呼び出してください。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @param self this参照
     * @return 再帰関数の末尾呼び出し
     * @see RP2#test(T1, T2)
     * @see BooleanTailCall#call
     * @see BooleanTailCall#complete
     */
    @NotNull
    BooleanTailCall test(@Nullable T1 t1, @Nullable T2 t2, @NotNull RP2<T1, T2> self);

    /**
     * 指定された引数でこの述語を評価します。
     *
     * <p>この実装では{@link RP2#test(T1, T2, RP2)}メソッドに引数と関数自身を渡します。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @return 再帰関数の末尾呼び出し
     */
    @NotNull
    default BooleanTailCall test(@Nullable T1 t1, @Nullable T2 t2) {
        return test(t1, t2, this);
    }

    /**
     * 再帰的ラムダ式から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数に渡された再帰的ラムダ式をP2型に変換して返します。
     * {@link TailCall#evaluate}の実行は{@link P2#test}の実行まで遅延されます。
     *
     * <p>再帰的ラムダ式の型が推論されない場合は次のように明示的に型を指定してください。
     * <pre>{@code
     *  P2<T1, T2> p2 = RP2.<T1, T2>of((t1, t2, self) -> {
     *      if(someCondition) return complete(someValue);
     *      return call(() -> self.apply(modify(t1), modify(t2)));
     *  }.and(SomeClass2::someMethod);
     * }</pre>
     *
     * @param rp2 再帰的ラムダ式
     * @param <T1> 第一引数の型
     * @param <T2> 第二引数の型
     * @return ラムダ式のP2変換
     */
    @NotNull
    static <T1, T2> P2<T1, T2> of(@NotNull RP2<T1, T2> rp2) {
        return (t1, t2) -> rp2.test(t1, t2).evaluate();
    }
}