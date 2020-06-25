package raystark.eflib.function.notnull.recursive;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NP1;
import raystark.eflib.function.recursive.BooleanTailCall;

/**
 * 再帰的に定義された型T1の述語です。
 *
 * <p>このインターフェースは{@link NRP1#test}を関数メソッドに持つ関数型インターフェースです。
 * <p>この関数はnullを扱いません。
 *
 * <p>このインターフェースは一変数述語{@link NP1}を再帰的ラムダ式で定義するためのインターフェースです。
 * このインターフェース自体のインスタンスからは部分適用等の{@link NP1}で定義されるdefaultメソッドにはアクセスできません。
 * {@link NRP1#of}メソッドを使うことで再帰的ラムダ式から{@link NP1}型の関数オブジェクトを作成できます。
 *
 * @param <T1> 第一引数の型
 */
@FunctionalInterface
public interface NRP1<T1> {

    /**
     * 一変数述語を再帰的に定義します。
     *
     * <p>selfにこの関数自身を渡した場合、一変数述語として引数をこの関数に適用します。
     * selfに自身以外を渡した場合の動作は未定義です。
     *
     * <p>再帰的ラムダ式では{@link BooleanTailCall#call}に渡すSupplierの中でselfを参照し、{@link NRP1#test}メソッドを呼び出してください。
     *
     * @param t1 第一引数
     * @param self this参照
     * @return 再帰関数の末尾呼び出し
     * @see NRP1#test
     * @see BooleanTailCall#call
     * @see BooleanTailCall#complete
     */
    @NotNull
    BooleanTailCall test(@NotNull T1 t1, @NotNull NRP1<T1> self);

    /**
     * 指定された引数でこの述語を評価します。
     *
     * <p>この実装では{@link NRP1#test}メソッドに引数と関数自身を渡します。
     *
     * @param t1 第一引数
     * @return 再帰関数の末尾呼び出し
     */
    @NotNull
    default BooleanTailCall test(@NotNull T1 t1) {
        return test(t1, this);
    }

    /**
     * 再帰的ラムダ式から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数に渡された再帰的ラムダ式をP1型に変換して返します。
     * {@link BooleanTailCall#evaluate}の実行は{@link NP1#test}の実行まで遅延されます。
     *
     * <p>再帰的ラムダ式の型が推論されない場合は次のように明示的に型を指定してください。
     * <pre>{@code
     *  NP1<T1> p1 = NRP1.<T1>of((t1, self) -> {
     *      if(someCondition) return complete(someValue);
     *      return call(() -> self.apply(modify(t1)));
     *  }.and(SomeClass2::someMethod);
     * }</pre>
     *
     * @param rp1 再帰的ラムダ式
     * @param <T1> 第一引数の型
     * @return ラムダ式のNP1変換
     */
    @NotNull
    static <T1> NP1<T1> of(@NotNull NRP1<T1> rp1) {
        return t1 -> rp1.test(t1).evaluate();
    }
}
