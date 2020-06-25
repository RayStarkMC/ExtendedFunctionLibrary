package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.function.F3;

/**
 * 再帰的に定義された三変数関数です。
 *
 * <p>このインターフェースは{@link RF3#apply(T1, T2, T3, RF3)}を関数メソッドに持つ関数型インターフェースです。
 *
 * <p>このインターフェースは一変数関数{@link F3}を再帰的ラムダ式で定義するためのインターフェースです。
 * このインターフェース自体のインスタンスからは部分適用等の{@link F3}で定義されるdefaultメソッドにはアクセスできません。
 * {@link RF3#of}メソッドを使うことで再帰的ラムダ式から{@link F3}型の関数オブジェクトを作成できます。
 *
 * @param <T1> 第一引数の型
 * @param <T2> 第二引数の型
 * @param <T3> 第三引数の型
 * @param <R> 戻り値の型
 */
@FunctionalInterface
public interface RF3<T1, T2, T3, R> {

    /**
     * 三変数関数を再帰的に定義します。
     *
     * <p>selfにこの関数自身を渡した場合、三変数関数として引数をこの関数に適用します。
     * selfに自身以外を渡した場合の動作は未定義です。
     *
     * <p>再帰的ラムダ式では{@link TailCall#call}に渡すSupplierの中でselfを参照し、{@link RF3#apply(T1, T2, T3)}メソッドを呼び出してください。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @param t3 第三引数
     * @param self this参照
     * @return 再帰関数の末尾呼び出し
     * @see RF3#apply(T1, T2, T3)
     * @see TailCall#call
     * @see TailCall#complete
     */
    @NotNull
    TailCall<R> apply(@Nullable T1 t1, @Nullable T2 t2, @Nullable T3 t3, @NotNull RF3<T1, T2, T3, R> self);

    /**
     * 三変数関数として引数をこの関数に適用します。
     *
     * <p>この実装では{@link RF3#apply(T1, T2, T3, RF3)}メソッドに引数と関数自身を渡します。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @param t3 第三引数
     * @return 再帰関数の末尾呼び出し
     */
    @NotNull
    default TailCall<R> apply(@Nullable T1 t1, @Nullable T2 t2, @Nullable T3 t3) {
        return apply(t1, t2, t3, this);
    }

    /**
     * 再帰的ラムダ式から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数に渡された再帰的ラムダ式をF3型に変換して返します。
     * {@link TailCall#evaluate}の実行は{@link F3#apply}の実行まで遅延されます。
     *
     * <p>再帰的ラムダ式の型が推論されない場合は次のように明示的に型を指定してください。
     * <pre>{@code
     *  F3<T1, T2, T3, R> f3 = RF3.<T1, T2, T3, R>of((t1, t2, t3, self) -> {
     *      if(someCondition) return complete(someValue);
     *      return call(() -> self.apply(modify(t1), modify(t2), modify(t3)));
     *  }.then3(SomeClass::someMethod);
     * }</pre>
     *
     * @param rf3 再帰的ラムダ式
     * @param <T1> 第一引数の型
     * @param <T2> 第二引数の型
     * @param <T3> 第三引数の型
     * @param <R> 戻り値の型
     * @return ラムダ式のF3変換
     */
    @NotNull
    static <T1, T2, T3, R> F3<T1, T2, T3, R> of(@NotNull RF3<T1, T2, T3, R> rf3) {
        return (t1, t2, t3) -> rf3.apply(t1, t2, t3).evaluate();
    }
}