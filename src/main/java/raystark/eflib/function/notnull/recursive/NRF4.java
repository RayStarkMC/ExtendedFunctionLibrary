package raystark.eflib.function.notnull.recursive;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF4;

/**
 * 再帰的に定義された四変数関数です。
 *
 * <p>このインターフェースは{@link NRF4#apply}を関数メソッドに持つ関数型インターフェースです。
 * <p>この関数はnullを扱いません。
 *
 * <p>このインターフェースは一変数関数{@link NF4}を再帰的ラムダ式で定義するためのインターフェースです。
 * このインターフェース自体のインスタンスからは部分適用等の{@link NF4}で定義されるdefaultメソッドにはアクセスできません。
 * {@link NRF4#of}メソッドを使うことで再帰的ラムダ式から{@link NF4}型の関数オブジェクトを作成できます。
 *
 * @param <T1> 第一引数の型
 * @param <T2> 第二引数の型
 * @param <T3> 第三引数の型
 * @param <T4> 第四引数の型
 * @param <R> 戻り値の型
 */
@FunctionalInterface
public interface NRF4<T1, T2, T3, T4, R> {

    /**
     * 四変数関数を再帰的に定義します。
     *
     * <p>selfにこの関数自身を渡した場合、四変数関数として引数をこの関数に適用します。
     * selfに自身以外を渡した場合の動作は未定義です。
     *
     * <p>再帰的ラムダ式では{@link NTailCall#call}に渡すSupplierの中でselfを参照し、{@link NRF4#apply}メソッドを呼び出してください。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @param t3 第三引数
     * @param t4 第四引数
     * @param self this参照
     * @return 再帰関数の末尾呼び出し
     * @see NRF4#apply
     * @see NTailCall#call
     * @see NTailCall#complete
     */
    @NotNull
    NTailCall<R> apply(@NotNull T1 t1, @NotNull T2 t2, @NotNull T3 t3, @NotNull T4 t4, @NotNull NRF4<T1, T2, T3, T4, R> self);

    /**
     * 四変数関数として引数をこの関数に適用します。
     *
     * <p>この実装では{@link NRF4#apply}メソッドに引数と関数自身を渡します。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @param t3 第三引数
     * @param t4 第四引数
     * @return 再帰関数の末尾呼び出し
     */
    @NotNull
    default NTailCall<R> apply(@NotNull T1 t1, @NotNull T2 t2, @NotNull T3 t3, @NotNull T4 t4) {
        return apply(t1, t2, t3, t4, this);
    }

    /**
     * 再帰的ラムダ式から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数に渡された再帰的ラムダ式をF4型に変換して返します。
     * {@link NTailCall#evaluate}の実行は{@link NF4#apply}の実行まで遅延されます。
     *
     * <p>再帰的ラムダ式の型が推論されない場合は次のように明示的に型を指定してください。
     * <pre>{@code
     *  NF4<T1, T2, T3, T4, R> f4 = NRF4.<T1, T2, T3, T4, R>of((t1, t2, t3, t4, self) -> {
     *      if(someCondition) return complete(someValue);
     *      return call(() -> self.apply(modify(t1), modify(t2), modify(t3), modify(t4)));
     *  }.then4(SomeClass::someMethod);
     * }</pre>
     *
     * @param rf4 再帰的ラムダ式
     * @param <T1> 第一引数の型
     * @param <T2> 第二引数の型
     * @param <T3> 第三引数の型
     * @param <T4> 第四引数の型
     * @param <R> 戻り値の型
     * @return ラムダ式のNF4変換
     */
    @NotNull
    static <T1, T2, T3, T4, R> NF4<T1, T2, T3, T4, R> of(@NotNull NRF4<T1, T2, T3, T4, R> rf4) {
        return (t1, t2, t3, t4) -> rf4.apply(t1, t2, t3, t4).evaluate();
    }
}
