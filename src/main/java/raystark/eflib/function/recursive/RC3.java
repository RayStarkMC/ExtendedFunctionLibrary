package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.function.C3;

/**
 * 再帰的に定義された型T1, 型T2, 型T3のConsumerです。
 *
 * <p>このインターフェースは{@link RC3#accept}を関数メソッドに持つ関数型インターフェースです。
 *
 * <p>このインターフェースは三変数Consumer {@link C3}を再帰的ラムダ式で定義するためのインターフェースです。
 * このインターフェース自体のインスタンスからは部分適用等の{@link C3}で定義されるdefaultメソッドにはアクセスできません。
 * {@link RC3#of}メソッドを使うことで再帰的ラムダ式から{@link C3}型の関数オブジェクトを作成できます。
 *
 * @param <T1> 第一引数の型
 * @param <T2> 第二引数の型
 * @param <T3> 第三引数の型
 */
@FunctionalInterface
public interface RC3<T1, T2, T3> {

    /**
     * 三変数Consumerを再帰的に定義します。
     *
     * <p>selfにこの関数自身を渡した場合、三変数Consumerとして引数をこの関数に適用します。
     * selfに自身以外を渡した場合の動作は未定義です。
     *
     * <p>再帰的ラムダ式では{@link VoidTailCall#call}に渡すSupplierの中でselfを参照し、{@link RC3#accept}メソッドを呼び出してください。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @param t3 第三引数
     * @param self this参照
     * @return 再帰関数の末尾呼び出し
     * @see RC3#accept
     * @see VoidTailCall#call
     * @see VoidTailCall#complete
     */
    @NotNull
    VoidTailCall accept(@Nullable T1 t1, @Nullable T2 t2, @Nullable T3 t3, @NotNull RC3<T1, T2, T3> self);

    /**
     * 三変数関数として引数をこの関数に適用します。
     *
     * <p>この実装では{@link RC3#accept}メソッドに引数と関数自身を渡します。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @param t3 第三引数
     * @return 再帰関数の末尾呼び出し
     */
    @NotNull
    default VoidTailCall accept(@Nullable T1 t1, @Nullable T2 t2, @Nullable T3 t3) {
        return accept(t1, t2, t3, this);
    }

    /**
     * 再帰的ラムダ式から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数に渡された再帰的ラムダ式をC3型に変換して返します。
     * {@link VoidTailCall#execute}の実行は{@link C3#accept}の実行まで遅延されます。
     *
     * <p>再帰的ラムダ式の型が推論されない場合は次のように明示的に型を指定してください。
     * <pre>{@code
     *  C3<T1, T2, T3> c3 = RC3.<R1, T2, T3>of((r1, t2, t3, self) -> {
     *      if(someCondition) return VoidTailCall.complete();
     *      return VoidTailCall.call(() -> self.apply(modify(r1), modify(t2), modify(t3)));
     *  }.compose1(SomeClass::someMethod);
     * }</pre>
     *
     * @param rc3 再帰的ラムダ式
     * @param <T1> 第一引数の型
     * @param <T2> 第二引数の型
     * @param <T3> 第三引数の型
     * @return ラムダ式のC3変換
     */
    @NotNull
    static <T1, T2, T3> C3<T1, T2, T3> of(@NotNull RC3<T1, T2, T3> rc3) {
        return (t1, t2, t3) -> rc3.accept(t1, t2, t3).execute();
    }
}