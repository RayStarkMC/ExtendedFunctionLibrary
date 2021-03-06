package raystark.eflib.function.notnull.recursive;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NC2;
import raystark.eflib.function.recursive.VoidTailCall;

/**
 * 再帰的に定義された型T1, 型T2のConsumerです。
 *
 * <p>このインターフェースは{@link NRC2#accept}を関数メソッドに持つ関数型インターフェースです。
 *
 * <p>このインターフェースは二変数Consumer {@link NC2}を再帰的ラムダ式で定義するためのインターフェースです。
 * このインターフェース自体のインスタンスからは部分適用等の{@link NC2}で定義されるdefaultメソッドにはアクセスできません。
 * {@link NRC2#of}メソッドを使うことで再帰的ラムダ式から{@link NC2}型の関数オブジェクトを作成できます。
 *
 * @param <T1> 第一引数の型
 * @param <T2> 第二引数の型
 */
@FunctionalInterface
public interface NRC2<T1, T2> {

    /**
     * 二変数Consumerを再帰的に定義します。
     *
     * <p>selfにこの関数自身を渡した場合、二変数Consumerとして引数をこの関数に適用します。
     * selfに自身以外を渡した場合の動作は未定義です。
     *
     * <p>再帰的ラムダ式では{@link VoidTailCall#call}に渡すSupplierの中でselfを参照し、{@link NRC2#accept}メソッドを呼び出してください。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @param self this参照
     * @return 再帰関数の末尾呼び出し
     * @see NRC2#accept
     * @see VoidTailCall#call
     * @see VoidTailCall#complete
     */
    @NotNull
    VoidTailCall accept(@NotNull T1 t1, @NotNull T2 t2, @NotNull NRC2<T1, T2> self);

    /**
     * 二変数関数として引数をこの関数に適用します。
     *
     * <p>この実装では{@link NRC2#accept}メソッドに引数と関数自身を渡します。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @return 再帰関数の末尾呼び出し
     */
    @NotNull
    default VoidTailCall accept(@NotNull T1 t1, @NotNull T2 t2) {
        return accept(t1, t2, this);
    }

    /**
     * 再帰的ラムダ式から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数に渡された再帰的ラムダ式をNC2型に変換して返します。
     * {@link VoidTailCall#execute}の実行は{@link NC2#accept}の実行まで遅延されます。
     *
     * <p>再帰的ラムダ式の型が推論されない場合は次のように明示的に型を指定してください。
     * <pre>{@code
     *  NC2<T1, T2> c2 = NRC2.<R1, T2>of((r1, t2, self) -> {
     *      if(someCondition) return VoidTailCall.complete();
     *      return VoidTailCall.call(() -> self.apply(modify(r1), modify(t2)));
     *  }.compose1(SomeClass2::someMethod);
     * }</pre>
     *
     * @param rc2 再帰的ラムダ式
     * @param <T1> 第一引数の型
     * @param <T2> 第二引数の型
     * @return ラムダ式のNC2変換
     */
    @NotNull
    static <T1, T2> NC2<T1, T2> of(@NotNull NRC2<T1, T2> rc2) {
        return (t1, t2) -> rc2.accept(t1, t2).execute();
    }
}
