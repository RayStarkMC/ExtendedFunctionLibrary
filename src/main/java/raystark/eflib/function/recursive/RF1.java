package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.function.F1;

/**
 * 再帰的に定義された一変数関数です。
 *
 * <p>このインターフェースは{@link RF1#apply(T1, RF1)}を関数メソッドに持つ関数型インターフェースです。
 *
 * <p>このインターフェースは一変数関数{@link F1}を再帰的ラムダ式で定義するためのインターフェースです。
 * このインターフェース自体のインスタンスからは部分適用等の{@link F1}で定義されるdefaultメソッドにはアクセスできません。
 * {@link RF1#of}メソッドを使うことで再帰的ラムダ式から{@link F1}型の関数オブジェクトを作成できます。
 *
 * @param <T1> 第一引数の型
 * @param <R> 戻り値の型
 */
@FunctionalInterface
public interface RF1<T1, R> {

    /**
     * 一変数関数を再帰的に定義します。
     *
     * <p>selfにこの関数自身を渡した場合、一変数関数として引数をこの関数に適用します。
     * selfに自身以外を渡した場合の動作は未定義です。
     *
     * <p>再帰的ラムダ式では{@link TailCall#call}に渡すSupplierの中でselfを参照し、{@link RF1#apply(T1)}メソッドを呼び出してください。
     *
     * @param t1 第一引数
     * @param self this参照
     * @return 適用結果
     * @see RF1#apply(T1)
     * @see TailCall#call
     */
    @NotNull
    TailCall<R> apply(@Nullable T1 t1, @NotNull RF1<T1, R> self);

    /**
     * 一変数関数として引数をこの関数に適用します。
     *
     * <p>この実装では{@link RF1#apply(T1, RF1)}メソッドに引数と関数自身を渡します。
     *
     * @param t1 第一引数
     * @return 適用結果
     * @see TailCall#call
     */
    @NotNull
    default TailCall<R> apply(@Nullable T1 t1) {
        return apply(t1, this);
    }

    /**
     * 再帰的ラムダ式から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数に渡された再帰的ラムダ式をF1型に変換して返します。
     * {@link TailCall#evaluate}の実行は{@link F1#apply}の実行まで遅延されます。
     *
     * <p>再帰的ラムダ式の型が推論されない場合は次のように明示的に型を指定してください。
     * <pre>{@code
     *  F1<T1, R> f1 = RF1.<T1, R>of((t1, self) -> {
     *      if(someCondition) return complete(someValue);
     *      return call(() -> self.apply(modify(t1)));
     *  }.then1(SomeClass2::someMethod);
     * }</pre>
     *
     * @param rf1 再帰的ラムダ式
     * @param <T1> 第一引数の型
     * @param <R> 戻り値の型
     * @return ラムダ式のF1変換
     */
    @NotNull
    static <T1, R> F1<T1, R> of(@NotNull RF1<T1, R> rf1) {
        return t1 -> rf1.apply(t1).evaluate();
    }
}
