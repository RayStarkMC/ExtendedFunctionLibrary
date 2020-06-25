package raystark.eflib.function.notnull.recursive;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NP2;
import raystark.eflib.function.recursive.BooleanTailCall;

/**
 * 再帰的に定義された型T1, 型T2の述語です。
 *
 * <p>このインターフェースは{@link NRP2#test}を関数メソッドに持つ関数型インターフェースです。
 * <p>この関数はnullを扱いません。
 *
 * <p>このインターフェースは二変数述語{@link NP2}を再帰的ラムダ式で定義するためのインターフェースです。
 * このインターフェース自体のインスタンスからは部分適用等の{@link NP2}で定義されるdefaultメソッドにはアクセスできません。
 * {@link NRP2#of}メソッドを使うことで再帰的ラムダ式から{@link NP2}型の関数オブジェクトを作成できます。
 *
 * @param <T1> 第一引数の型
 * @param <T2> 第二引数の型
 */
@FunctionalInterface
public interface NRP2<T1, T2> {

    /**
     * 二変数述語を再帰的に定義します。
     *
     * <p>selfにこの関数自身を渡した場合、二変数述語として引数をこの関数に適用します。
     * selfに自身以外を渡した場合の動作は未定義です。
     *
     * <p>再帰的ラムダ式では{@link BooleanTailCall#call}に渡すSupplierの中でselfを参照し、{@link NRP2#test}メソッドを呼び出してください。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @param self this参照
     * @return 再帰関数の末尾呼び出し
     * @see NRP2#test
     * @see BooleanTailCall#call
     * @see BooleanTailCall#complete
     */
    @NotNull
    BooleanTailCall test(@NotNull T1 t1, @NotNull T2 t2, @NotNull NRP2<T1, T2> self);

    /**
     * 指定された引数でこの述語を評価します。
     *
     * <p>この実装では{@link NRP2#test}メソッドに引数と関数自身を渡します。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @return 再帰関数の末尾呼び出し
     */
    @NotNull
    default BooleanTailCall test(@NotNull T1 t1, @NotNull T2 t2) {
        return test(t1, t2, this);
    }

    /**
     * 再帰的ラムダ式から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数に渡された再帰的ラムダ式をNP2型に変換して返します。
     * {@link BooleanTailCall#evaluate}の実行は{@link NP2#test}の実行まで遅延されます。
     *
     * <p>再帰的ラムダ式の型が推論されない場合は次のように明示的に型を指定してください。
     * <pre>{@code
     *  NP2<T1, T2> p2 = NRP2.<T1, T2>of((t1, t2, self) -> {
     *      if(someCondition) return complete(someValue);
     *      return call(() -> self.apply(modify(t1), modify(t2)));
     *  }.and(SomeClass2::someMethod);
     * }</pre>
     *
     * @param rp2 再帰的ラムダ式
     * @param <T1> 第一引数の型
     * @param <T2> 第二引数の型
     * @return ラムダ式のNP2変換
     */
    @NotNull
    static <T1, T2> NP2<T1, T2> of(@NotNull NRP2<T1, T2> rp2) {
        return (t1, t2) -> rp2.test(t1, t2).evaluate();
    }
}
