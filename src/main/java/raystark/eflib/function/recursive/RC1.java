package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.function.C1;

/**
 * 再帰的に定義された型T1のConsumerです。
 *
 * <p>このインターフェースは{@link RC1#accept(T1, RC1)}を関数メソッドに持つ関数型インターフェースです。
 *
 * <p>このインターフェースは一変数Consumer {@link C1}を再帰的ラムダ式で定義するためのインターフェースです。
 * このインターフェース自体のインスタンスからは部分適用等の{@link C1}で定義されるdefaultメソッドにはアクセスできません。
 * {@link RC1#of}メソッドを使うことで再帰的ラムダ式から{@link C1}型の関数オブジェクトを作成できます。
 *
 * @param <T1> 第一引数の型
 */
@FunctionalInterface
public interface RC1<T1> {

    /**
     * 一変数Consumerを再帰的に定義します。
     *
     * <p>selfにこの関数自身を渡した場合、一変数Consumerとして引数をこの関数に適用します。
     * selfに自身以外を渡した場合の動作は未定義です。
     *
     * <p>再帰的ラムダ式では{@link VoidTailCall#call}に渡すSupplierの中でselfを参照し、{@link RC1#accept(T1)}メソッドを呼び出してください。
     *
     * @param t1 第一引数
     * @param self this参照
     * @return 再帰関数の末尾呼び出し
     * @see RC1#accept(T1)
     * @see VoidTailCall#call
     */
    @NotNull
    VoidTailCall accept(@Nullable T1 t1, @NotNull RC1<T1> self);

    /**
     * 一変数関数として引数をこの関数に適用します。
     *
     * <p>この実装では{@link RC1#accept(T1, RC1)}メソッドに引数と関数自身を渡します。
     *
     * @param t1 第一引数
     * @return 再帰関数の末尾呼び出し
     * @see VoidTailCall#call
     */
    @NotNull
    default VoidTailCall accept(@Nullable T1 t1) {
        return accept(t1, this);
    }

    /**
     * 再帰的ラムダ式から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数に渡された再帰的ラムダ式をC1型に変換して返します。
     * {@link TailCall#evaluate}の実行は{@link C1#accept}の実行まで遅延されます。
     *
     * <p>再帰的ラムダ式の型が推論されない場合は次のように明示的に型を指定してください。
     * <pre>{@code
     *  C1<T1> c1 = RC1.<R1>of((r1, self) -> {
     *      if(someCondition) return VoidTailCall.complete(someValue);
     *      return VoidTailCall.call(() -> self.apply(modify(t1)));
     *  }.compose1(SomeClass2::someMethod);
     * }</pre>
     *
     * @param rc1 再帰的ラムダ式
     * @param <T1> 第一引数の型
     * @return ラムダ式のC1変換
     */
    @NotNull
    static <T1> C1<T1> of(@NotNull RC1<T1> rc1) {
        return t1 -> rc1.accept(t1).execute();
    }
}
