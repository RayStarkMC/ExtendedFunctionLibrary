package raystark.eflib.function.notnull;

import org.jetbrains.annotations.NotNull;

/**
 * 型T1, 型T2, 型T3, 型T4の述語です。またBoolean型を返す関数です。
 *
 * <p>このインターフェースは{@link NP4#test}を関数メソッドに持つ関数型インターフェースです。
 * <p>この関数はnullを扱いません。
 *
 * <p>オーバーライドされた{@link NF4}のメソッドの内、関数を返すものは対応する述語を返します。
 *
 * <p>この述語には部分適用を行うメソッド、合成述語を作成するメソッド、引数の順序を変えるメソッド、
 * 自身を他の関数に変換するメソッド、ラムダやメソッド参照からインスタンスを作成するメソッドが定義されています。
 *
 * @param <T1> 第一引数の型
 * @param <T2> 第二引数の型
 * @param <T3> 第三引数の型
 * @param <T4> 第四引数の型
 * @see NF4
 */
@FunctionalInterface
public interface NP4<T1, T2, T3, T4> extends NF4<T1, T2, T3, T4, Boolean> {
    /**
     * 指定された引数でこの述語を評価します。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @param t3 第三引数
     * @param t4 第四引数
     * @return 引数が述語に一致する場合true, それ以外の場合false
     */
    boolean test(@NotNull T1 t1, @NotNull T2 t2, @NotNull T3 t3, @NotNull T4 t4);

    /**
     * 指定された引数でこの述語を評価します。
     *
     * <p>このメソッドは{@link NP4#test}の結果をBoolean型にラップします。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @param t3 第三引数
     * @param t4 第四引数
     * @return Boolean型にラップされた述語の結果
     */
    @Override
    @NotNull
    default Boolean apply(@NotNull T1 t1, @NotNull T2 t2, @NotNull T3 t3, @NotNull T4 t4) {
        return test(t1, t2, t3, t4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default NP3<T2, T3, T4> apply(@NotNull T1 t1) {
        return (t2, t3, t4) -> test(t1, t2, t3, t4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default NP3<T2, T3, T4> apply(@NotNull NS<? extends T1> t1) {
        return (t2, t3, t4) -> test(t1.get(), t2, t3, t4);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default NP2<T3, T4> apply(@NotNull T1 t1, @NotNull T2 t2) {
        return (t3, t4) -> test(t1, t2, t3, t4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default NP2<T3, T4> apply(@NotNull NS<? extends T1> t1, @NotNull NS<? extends T2> t2) {
        return (t3, t4) -> test(t1.get(), t2.get(), t3, t4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default NP1<T4> apply(@NotNull T1 t1, @NotNull T2 t2, @NotNull T3 t3) {
        return t4 -> test(t1, t2, t3,t4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default NP1<T4> apply(@NotNull NS<? extends T1> t1, @NotNull NS<? extends T2> t2, @NotNull NS<? extends T3> t3) {
        return t4 -> test(t1.get(), t2.get(), t3.get(),t4);
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
    default NP4<T1, T2, T3, T4> and(@NotNull NP4<? super T1, ? super T2, ? super T3, ? super T4> other) {
        return (t1, t2, t3, t4) -> test(t1, t2, t3, t4) && other.test(t1, t2, t3, t4);
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
    default NP4<T1, T2, T3, T4> or(@NotNull NP4<? super T1, ? super T2, ? super T3, ? super T4> other) {
        return (t1, t2, t3, t4) -> test(t1, t2, t3, t4) || other.test(t1, t2, t3, t4);
    }

    /**
     * この述語の論理否定を表す述語を返します。
     *
     * @return 述語
     */
    @NotNull
    default NP4<T1, T2, T3, T4> not() {
        return (t1, t2, t3, t4) -> !test(t1, t2, t3, t4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default <V1> NP4<V1, T2, T3, T4> compose1(@NotNull NF1<? super V1, ? extends T1> before) {
        return (v1, t2, t3, t4) -> test(before.apply(v1), t2, t3, t4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default <V2> NP4<T1, V2, T3, T4> compose2(@NotNull NF1<? super V2, ? extends T2> before) {
        return (t1, v2, t3, t4) -> test(t1, before.apply(v2), t3, t4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default <V3> NP4<T1, T2, V3, T4> compose3(@NotNull NF1<? super V3, ? extends T3> before) {
        return (t1, t2, v3, t4) -> test(t1, t2, before.apply(v3), t4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default <V4> NP4<T1, T2, T3, V4> compose4(@NotNull NF1<? super V4, ? extends T4> before) {
        return (t1, t2, t3, v4) -> test(t1, t2, t3, before.apply(v4));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default NP4<T2, T1, T3, T4> swap2() {
        return (t2, t1, t3, t4) -> test(t1, t2, t3, t4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default NP4<T3, T2, T1, T4> swap3() {
        return (t3, t2, t1, t4) -> test(t1, t2, t3, t4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default NP4<T4, T2, T3, T1> swap4() {
        return (t4, t2, t3, t1) -> test(t1, t2, t3, t4);
    }

    /**
     * ラムダやメソッド参照から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数をそのまま返します。オーバーロードされているメソッドを参照する場合は次のようにラムダの引数等で明示的に型を指定してください。
     *
     * <pre>{@code
     *  NP4<T1, T2, T3, T4> p1 = NP4.of((T1 t1, T2 t2, T3 t3, T4 t4) -> SomeClass1.someMethod(t1, t2, t3, t4)).and(SomeClass2::someMethod);
     *  NP4<T1, T2, T3, T4> p2 = NP4.<T1, T2, T3, T4>of(SomeClass1::someMethod).then1(SomeClass2::someMethod);
     * }</pre>
     *
     * <p>このメソッドの呼び出しに対して様々なメソッドをチェインできます。
     *
     * @param p4 ラムダやメソッド参照で記述された述語
     * @param <T1> 第一引数の型
     * @param <T2> 第二引数の型
     * @param <T3> 第三引数の型
     * @param <T4> 第四引数の型
     * @return 引数に渡された述語
     */
    @NotNull
    static <T1, T2, T3, T4> NP4<T1, T2, T3, T4> of(@NotNull NP4<T1, T2, T3, T4> p4) {
        return p4;
    }

    /**
     * 型変数の変性を表すキャストメソッド。
     *
     * @param p4 キャスト対象
     * @param <T1> キャスト後第一引数の型
     * @param <T2> キャスト後第二引数の型
     * @param <T3> キャスト後第三引数の型
     * @param <T4> キャスト後第四引数の型
     * @return キャスト対象の参照
     */
    @SuppressWarnings("unchecked")
    @NotNull
    static <T1, T2, T3, T4> NP4<T1, T2, T3, T4> cast(@NotNull NP4<? super T1, ? super T2, ? super T3, ? super T4> p4) {
        return (NP4<T1, T2, T3, T4>) p4;
    }
}
