package raystark.eflib.function.notnull.recursive;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;

/**
 * 再帰的に定義された一変数関数です。
 *
 * <p>このインターフェースは{@link NRF1#apply(T1, NRF1)}を関数メソッドに持つ関数型インターフェースです。
 * <p>この関数はnullを扱いません。
 *
 * <p>このインターフェースは一変数関数{@link NF1}を再帰的ラムダ式で定義するためのインターフェースです。
 * このインターフェース自体のインスタンスからは部分適用等の{@link NF1}で定義されるdefaultメソッドにはアクセスできません。
 * {@link NRF1#of}メソッドを使うことで再帰的ラムダ式から{@link NF1}型の関数オブジェクトを作成できます。
 *
 * @param <T1> 第一引数の型
 * @param <R> 戻り値の型
 */
@FunctionalInterface
public interface NRF1<T1, R> {

    /**
     * 一変数関数を再帰的に定義します。
     *
     * <p>selfにこの関数自身を渡した場合、一変数関数として引数をこの関数に適用します。
     * selfに自身以外を渡した場合の動作は未定義です。
     *
     * <p>再帰的ラムダ式では{@link NTailCall#call}に渡すSupplierの中でselfを参照し、{@link NRF1#apply(T1)}メソッドを呼び出してください。
     *
     * @param t1 第一引数
     * @param self this参照
     * @return 再帰関数の末尾呼び出し
     * @see NRF1#apply(T1)
     * @see NTailCall#call
     * @see NTailCall#complete
     */
    @NotNull
    NTailCall<R> apply(@NotNull T1 t1, @NotNull NRF1<T1, R> self);

    /**
     * 一変数関数として引数をこの関数に適用します。
     *
     * <p>この実装では{@link NRF1#apply(T1, NRF1)}メソッドに引数と関数自身を渡します。
     *
     * @param t1 第一引数
     * @return 再帰関数の末尾呼び出し
     */
    @NotNull
    default NTailCall<R> apply(@NotNull T1 t1) {
        return apply(t1, this);
    }

    /**
     * 再帰的ラムダ式から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数に渡された再帰的ラムダ式をF1型に変換して返します。
     * {@link NTailCall#evaluate}の実行は{@link NF1#apply}の実行まで遅延されます。
     *
     * <p>再帰的ラムダ式の型が推論されない場合は次のように明示的に型を指定してください。
     * <pre>{@code
     *  NF1<T1, R> f1 = NRF1.<T1, R>of((t1, self) -> {
     *      if(someCondition) return complete(someValue);
     *      return call(() -> self.apply(modify(t1)));
     *  }.then1(SomeClass2::someMethod);
     * }</pre>
     *
     * @param rf1 再帰的ラムダ式
     * @param <T1> 第一引数の型
     * @param <R> 戻り値の型
     * @return ラムダ式のNF1変換
     */
    @NotNull
    static <T1, R> NF1<T1, R> of(@NotNull NRF1<T1, R> rf1) {
        return t1 -> rf1.apply(t1).evaluate();
    }
}
