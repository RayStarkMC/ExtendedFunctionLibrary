package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.function.P1;

/**
 * 再帰的に定義された型T1の述語です。
 *
 * <p>このインターフェースは{@link RP1#test(T1, RP1)}を関数メソッドに持つ関数型インターフェースです。
 *
 * <p>このインターフェースは一変数述語{@link P1}を再帰的ラムダ式で定義するためのインターフェースです。
 * このインターフェース自体のインスタンスからは部分適用等の{@link P1}で定義されるdefaultメソッドにはアクセスできません。
 * {@link RP1#of}メソッドを使うことで再帰的ラムダ式から{@link P1}型の関数オブジェクトを作成できます。
 *
 * @param <T1> 第一引数の型
 */
@FunctionalInterface
public interface RP1<T1> {

    /**
     * 一変数述語を再帰的に定義します。
     *
     * <p>selfにこの関数自身を渡した場合、一変数述語として引数をこの関数に適用します。
     * selfに自身以外を渡した場合の動作は未定義です。
     *
     * <p>再帰的ラムダ式では{@link TailCall#call}に渡すSupplierの中でselfを参照し、{@link RP1#test(T1)}メソッドを呼び出してください。
     *
     * @param t1 第一引数
     * @param self this参照
     * @return 適用結果
     * @see RP1#test(T1)
     * @see TailCall#call
     */
    @NotNull
    BooleanTailCall test(@Nullable T1 t1, @NotNull RP1<T1> self);

    /**
     * 指定された引数でこの述語を評価します。
     *
     * <p>この実装では{@link RF1#apply(T1, RF1)}メソッドに引数と関数自身を渡します。
     *
     * @param t1 第一引数
     * @return 適用結果
     * @see TailCall#call
     */
    @NotNull
    default BooleanTailCall test(@Nullable T1 t1) {
        return test(t1, this);
    }

    /**
     * 再帰的ラムダ式から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数に渡された再帰的ラムダ式をP1型に変換して返します。
     * {@link TailCall#evaluate}の実行は{@link P1#test}の実行まで遅延されます。
     *
     * <p>再帰的ラムダ式の型が推論されない場合は次のように明示的に型を指定してください。
     * <pre>{@code
     *  P1<T1> f1 = RP1.<T1>of((t1, self) -> {
     *      if(someState) return complete(someValue1);
     *      return call(() -> self.apply(modify(t1)));
     *  }.and(SomeClass2::someMethod);
     * }</pre>
     *
     * @param rp1 再帰的ラムダ式
     * @param <T1> 第一引数の型
     * @return ラムダ式のP1変換
     */
    @NotNull
    static <T1> P1<T1> of(@NotNull RP1<T1> rp1) {
        return t1 -> rp1.test(t1).evaluate();
    }
}
