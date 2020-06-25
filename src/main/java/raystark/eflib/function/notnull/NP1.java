package raystark.eflib.function.notnull;

import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

/**
 * 型T1の述語です。またBoolean型を返す関数です。
 *
 * <p>このインターフェースは{@link NP1#test}を関数メソッドに持つ関数型インターフェースです。
 * <p>この関数はnullを扱いません。
 *
 * <p>オーバーライドされた{@link NF1}のメソッドの内、関数を返すものは対応する述語を返します。
 *
 * <p>このインターフェースは{@link Predicate}に対応します。{@link Predicate}に変換する場合次のイディオムが使えます。
 *
 * <pre>{@code
 *  NP1<T1> p1 = SomeClass1::someMethod;
 *  Predicate<T1> p2 = p1::test;
 * }</pre>
 *
 * <p>この述語には合成述語を作成するメソッド、自身を他の関数に変換するメソッド、
 * ラムダやメソッド参照からインスタンスを作成するメソッドが定義されています。
 *
 * @param <T1> 第一引数の型
 * @see NF1
 */
@FunctionalInterface
public interface NP1<T1> extends NF1<T1, Boolean> {
    /**
     * 指定された引数でこの述語を評価します。
     *
     * @param t1 第一引数
     * @return 引数が述語に一致する場合true, それ以外の場合false
     */
    boolean test(@NotNull T1 t1);

    /**
     * 指定された引数でこの述語を評価します。
     *
     * <p>このメソッドは{@link NP1#test}の結果をBoolean型にラップします。
     *
     * @param t1 第一引数
     * @return Boolean型にラップされた述語の結果
     */
    @Override
    @NotNull
    default Boolean apply(@NotNull T1 t1) {
        return test(t1);
    }

    /**
     * この述語と述語otherの短絡論理積を表す合成述語を返します。
     *
     * <p>この述語がfalseだった場合、又はこの述語の評価時に例外がスローされた場合otherは評価されません。
     *
     * <p>いずれかの述語の評価時にスローされた例外は呼び出し元に中継されます。
     *
     * @param other この述語と論理積を取る述語
     * @return 合成述語
     */
    @NotNull
    default NP1<T1> and(@NotNull NP1<? super T1> other) {
        return t1 -> test(t1) && other.test(t1);
    }

    /**
     * この述語と述語otherの短絡論理和を表す合成述語を返します。
     *
     * <p>この述語がtrueだった場合、又はこの述語の評価時に例外がスローされた場合otherは評価されません。
     *
     * <p>いずれかの述語の評価時にスローされた例外は呼び出し元に中継されます。
     *
     * @param other この述語と論理和を取る述語
     * @return 合成述語
     */
    @NotNull
    default NP1<T1> or(@NotNull NP1<? super T1> other) {
        return t1 -> test(t1) && other.test(t1);
    }

    /**
     * この述語の論理否定を表す述語を返します。
     *
     * @return 述語
     */
    @NotNull
    default NP1<T1> not() {
        return t1 -> !test(t1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default <V1> NP1<V1> compose1(@NotNull NF1<? super V1, ? extends T1> before) {
        return v1 -> test(before.apply(v1));
    }

    /**
     * ラムダやメソッド参照から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数をそのまま返します。オーバーロードされているメソッドを参照する場合は次のようにラムダの引数等で明示的に型を指定してください。
     *
     * <pre>{@code
     *  NP1<T1> p1 = NP1.of((T1 t1) -> SomeClass1.someMethod(t1)).and(SomeClass2::someMethod);
     *  NP1<T1> p2 = NP1.<T1>of(SomeClass1::someMethod).then1(SomeClass2::someMethod);
     * }</pre>
     *
     * <p>このメソッドの呼び出しに対して様々なメソッドをチェインできます。
     *
     * @param p1 ラムダやメソッド参照で記述された述語
     * @param <T1> 第一引数の型
     * @return 引数に渡された述語
     */
    @NotNull
    static <T1> NP1<T1> of(@NotNull NP1<T1> p1) {
        return p1;
    }
}
