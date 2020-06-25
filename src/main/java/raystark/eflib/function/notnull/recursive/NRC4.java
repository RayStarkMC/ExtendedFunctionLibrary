package raystark.eflib.function.notnull.recursive;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NC4;
import raystark.eflib.function.recursive.VoidTailCall;

/**
 * 再帰的に定義された型T1, 型T2, 型T3, 型T4のConsumerです。
 *
 * <p>このインターフェースは{@link NRC4#accept}を関数メソッドに持つ関数型インターフェースです。
 *
 * <p>このインターフェースは四変数Consumer {@link NC4}を再帰的ラムダ式で定義するためのインターフェースです。
 * このインターフェース自体のインスタンスからは部分適用等の{@link NC4}で定義されるdefaultメソッドにはアクセスできません。
 * {@link NRC4#of}メソッドを使うことで再帰的ラムダ式から{@link NC4}型の関数オブジェクトを作成できます。
 *
 * @param <T1> 第一引数の型
 * @param <T2> 第二引数の型
 * @param <T3> 第三引数の型
 * @param <T4> 第四引数の型
 */
@FunctionalInterface
public interface NRC4<T1, T2, T3, T4> {

    /**
     * 四変数Consumerを再帰的に定義します。
     *
     * <p>selfにこの関数自身を渡した場合、四変数Consumerとして引数をこの関数に適用します。
     * selfに自身以外を渡した場合の動作は未定義です。
     *
     * <p>再帰的ラムダ式では{@link VoidTailCall#call}に渡すSupplierの中でselfを参照し、{@link NRC4#accept}メソッドを呼び出してください。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @param t3 第三引数
     * @param t4 第四引数
     * @param self this参照
     * @return 再帰関数の末尾呼び出し
     * @see NRC4#accept
     * @see VoidTailCall#call
     * @see VoidTailCall#complete
     */
    @NotNull
    VoidTailCall accept(@NotNull T1 t1, @NotNull T2 t2, @NotNull T3 t3, @NotNull T4 t4, @NotNull NRC4<T1, T2, T3, T4> self);

    /**
     * 四変数関数として引数をこの関数に適用します。
     *
     * <p>この実装では{@link NRC4#accept}メソッドに引数と関数自身を渡します。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @param t3 第三引数
     * @param t4 第四引数
     * @return 再帰関数の末尾呼び出し
     */
    @NotNull
    default VoidTailCall accept(@NotNull T1 t1, @NotNull T2 t2, @NotNull T3 t3, @NotNull T4 t4) {
        return accept(t1, t2, t3, t4, this);
    }

    /**
     * 再帰的ラムダ式から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数に渡された再帰的ラムダ式をNC4型に変換して返します。
     * {@link VoidTailCall#execute}の実行は{@link NC4#accept}の実行まで遅延されます。
     *
     * <p>再帰的ラムダ式の型が推論されない場合は次のように明示的に型を指定してください。
     * <pre>{@code
     *  NC3<T1, T2, T3, T4> c3 = NRC3.<R1, T2, T3, T4>of((r1, t2, t3, t4, self) -> {
     *      if(someCondition) return VoidTailCall.complete();
     *      return VoidTailCall.call(() -> self.apply(modify(r1), modify(t2), modify(t3), modify(t4)));
     *  }.compose1(SomeClass::someMethod);
     * }</pre>
     *
     * @param rc4 再帰的ラムダ式
     * @param <T1> 第一引数の型
     * @param <T2> 第二引数の型
     * @param <T3> 第三引数の型
     * @param <T4> 第四引数
     * @return ラムダ式のNC4変換
     */
    @NotNull
    static <T1, T2, T3, T4> NC4<T1, T2, T3, T4> of(@NotNull NRC4<T1, T2, T3, T4> rc4) {
        return (t1, t2, t3, t4) -> rc4.accept(t1, t2, t3, t4).execute();
    }
}
