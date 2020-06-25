package raystark.eflib.function.notnull.recursive;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NP4;
import raystark.eflib.function.recursive.BooleanTailCall;

/**
 * 再帰的に定義された型T1, 型T2, 型T3, 型T4の述語です。
 *
 * <p>このインターフェースは{@link NRP4#test(T1, T2, T3, T4, NRP4)}を関数メソッドに持つ関数型インターフェースです。
 * <p>この関数はnullを扱いません。
 *
 * <p>このインターフェースは三変数述語{@link NP4}を再帰的ラムダ式で定義するためのインターフェースです。
 * このインターフェース自体のインスタンスからは部分適用等の{@link NP4}で定義されるdefaultメソッドにはアクセスできません。
 * {@link NRP4#of}メソッドを使うことで再帰的ラムダ式から{@link NP4}型の関数オブジェクトを作成できます。
 *
 * @param <T1> 第一引数の型
 * @param <T2> 第二引数の型
 * @param <T3> 第三引数の型
 * @param <T4> 第四引数の型
 */
@FunctionalInterface
public interface NRP4<T1, T2, T3, T4> {

    /**
     * 四変数述語を再帰的に定義します。
     *
     * <p>selfにこの関数自身を渡した場合、四変数述語として引数をこの関数に適用します。
     * selfに自身以外を渡した場合の動作は未定義です。
     *
     * <p>再帰的ラムダ式では{@link BooleanTailCall#call}に渡すSupplierの中でselfを参照し、{@link NRP4#test(T1, T2, T3, T4)}メソッドを呼び出してください。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @param t3 第三引数
     * @param t4 第四引数
     * @param self this参照
     * @return 再帰関数の末尾呼び出し
     * @see NRP4#test(T1, T2, T3, T4)
     * @see BooleanTailCall#call
     * @see BooleanTailCall#complete
     */
    @NotNull
    BooleanTailCall test(@NotNull T1 t1, @NotNull T2 t2, @NotNull T3 t3, @NotNull T4 t4, @NotNull NRP4<T1, T2, T3, T4> self);

    /**
     * 指定された引数でこの述語を評価します。
     *
     * <p>この実装では{@link NRP4#test(T1, T2, T3, T4, NRP4)}メソッドに引数と関数自身を渡します。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @param t3 第三引数
     * @param t4 第四引数
     * @return 再帰関数の末尾呼び出し
     */
    @NotNull
    default BooleanTailCall test(@NotNull T1 t1, @NotNull T2 t2, @NotNull T3 t3, @NotNull T4 t4) {
        return test(t1, t2, t3, t4, this);
    }

    /**
     * 再帰的ラムダ式から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数に渡された再帰的ラムダ式をNP4型に変換して返します。
     * {@link BooleanTailCall#evaluate}の実行は{@link NP4#test}の実行まで遅延されます。
     *
     * <p>再帰的ラムダ式の型が推論されない場合は次のように明示的に型を指定してください。
     * <pre>{@code
     *  NP4<T1, T2, T3, T4> p4 = NRP4.<T1, T2, T3, T4>of((t1, t2, t3, t4, self) -> {
     *      if(someCondition) return complete(someValue);
     *      return call(() -> self.apply(modify(t1), modify(t2), modify(t3), modify(t4));
     *  }.and(SomeClass2::someMethod);
     * }</pre>
     *
     * @param rp4 再帰的ラムダ式
     * @param <T1> 第一引数の型
     * @param <T2> 第二引数の型
     * @param <T3> 第三引数の型
     * @param <T4> 第四引数の型
     * @return ラムダ式のNP4変換
     */
    @NotNull
    static <T1, T2, T3, T4> NP4<T1, T2, T3, T4> of(@NotNull NRP4<T1, T2, T3, T4> rp4) {
        return (t1, t2, t3, t4) -> rp4.test(t1, t2, t3, t4).evaluate();
    }
}
