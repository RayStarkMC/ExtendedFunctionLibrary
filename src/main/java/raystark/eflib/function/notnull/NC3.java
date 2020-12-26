package raystark.eflib.function.notnull;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.A;

/**
 * 型T1の値を受け取り値を返さない手続きです。
 *
 * <p>このインターフェースは{@link NC3#accept}を関数メソッドに持つ関数型インターフェースです。
 * <p>この関数はnullを扱いません。
 *
 * <p>このConsumerには部分適用を行うメソッド、合成Consumerを作成するメソッド、引数の順序を変えるメソッド、
 * 自身を他の関数に変換するメソッド、ラムダやメソッド参照からインスタンスを作成するメソッドが定義されています。
 *
 * @param <T1> 第一引数の型
 * @param <T2> 第二引数の型
 * @param <T3> 第三引数の型
 */
@FunctionalInterface
public interface NC3<T1, T2, T3> {

    /**
     * 引数をこのConsumerに適用します。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @param t3 第三引数
     */
    void accept(@NotNull T1 t1, @NotNull T2 t2, @NotNull T3 t3);

    /**
     * 第一引数までをこのConsumerに部分適用します。
     *
     * @param t1 第一引数
     * @return 引数が部分適用されたConsumer
     */
    @NotNull
    default NC2<T2, T3> apply(@NotNull T1 t1) {
        return (t2, t3) -> accept(t1, t2, t3);
    }

    /**
     * 第一引数までをこのConsumerに部分適用します。
     *
     * <p>引数は遅延評価されます。
     *
     * @param t1 第一引数
     * @return 引数が部分適用されたConsumer
     */
    @NotNull
    default NC2<T2, T3> apply(@NotNull NS<? extends T1> t1) {
        return (t2, t3) -> accept(t1.get(), t2, t3);
    }

    /**
     * 第二引数までをこのConsumerに部分適用します。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @return 引数が部分適用されたConsumer
     */
    @NotNull
    default NC1<T3> apply(@NotNull T1 t1, @NotNull T2 t2) {
        return t3 -> accept(t1, t2, t3);
    }

    /**
     * 第二引数までをこのConsumerに部分適用します。
     *
     * <p>引数は遅延評価されます。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @return 引数が部分適用されたConsumer
     */
    @NotNull
    default NC1<T3> apply(@NotNull NS<? extends T1> t1, @NotNull NS<? extends T2> t2) {
        return t3 -> accept(t1.get(), t2.get(), t3);
    }

    /**
     * このConsumerを実行した後にConsumer afterを実行する合成Consumerを返します。
     *
     * <p>いずれかの関数の実行時にスローされた例外は呼び出し元に中継されます。
     *
     * @param after このConsumerの後に実行されるConsumer
     * @return 合成Consumer
     */
    @NotNull
    default NC3<T1, T2, T3> next(@NotNull NC3<? super T1, ? super T2, ? super T3> after) {
        return (t1, t2, t3) -> {
            accept(t1, t2, t3);
            after.accept(t1, t2, t3);
        };
    }

    /**
     * このConsumerを実行した後にAction afterを実行する合成Consumerを返します。
     *
     * <p>いずれかの関数の実行時にスローされた例外は呼び出し元に中継されます。
     *
     * @param after このConsumerの後に実行されるAction
     * @return 合成Consumer
     */
    @NotNull
    default NC3<T1, T2, T3> next(@NotNull A after) {
        return (t1, t2, t3) -> {
            this.accept(t1, t2, t3);
            after.run();
        };
    }

    /**
     * このConsumerを実行する前にConsumer beforeを実行する合成Consumerを返します。
     *
     * <p>いずれかの関数の実行時にスローされた例外は呼び出し元に中継されます。
     *
     * @param before このConsumerの前に実行されるConsumer
     * @return 合成Consumer
     */
    @NotNull
    default NC3<T1, T2, T3> prev(@NotNull NC3<? super T1, ? super T2, ? super T3> before) {
        return (t1, t2, t3) -> {
            before.accept(t1, t2, t3);
            this.accept(t1, t2, t3);
        };
    }

    /**
     * このConsumerを実行する前にAction beforeを実行する合成Consumerを返します。
     *
     * <p>いずれかの関数の実行時にスローされた例外は呼び出し元に中継されます。
     *
     * @param before このConsumerの前に実行されるAction
     * @return 合成Consumer
     */
    @NotNull
    default NC3<T1, T2, T3> prev(@NotNull A before) {
        return (t1, t2, t3) -> {
            before.run();
            this.accept(t1, t2, t3);
        };
    }

    /**
     * 第一引数への入力を関数beforeに適用し、その結果をこのConsumerの第一引数に適用する合成Consumerを返します。
     *
     * <p>いずれかの関数の評価時にスローされた例外は呼び出し元に中継されます。
     *
     * @param before このConsumerが適用される前に第一引数に適用される関数
     * @param <V1>　合成Consumerの第一引数の型
     * @return 合成Consumer
     */
    @NotNull
    default <V1> NC3<V1, T2, T3> compose1(@NotNull NF1<? super V1, ? extends T1> before) {
        return (v1, t2, t3) -> accept(before.apply(v1), t2, t3);
    }

    /**
     * 第二引数への入力を関数beforeに適用し、その結果をこのConsumerの第二引数に適用する合成Consumerを返します。
     *
     * <p>いずれかの関数の評価時にスローされた例外は呼び出し元に中継されます。
     *
     * @param before このConsumerが適用される前に第二引数に適用される関数
     * @param <V2>　合成Consumerの第二引数の型
     * @return 合成Consumer
     */
    @NotNull
    default <V2> NC3<T1, V2, T3> compose2(@NotNull NF1<? super V2, ? extends T2> before) {
        return (t1, v2, t3) -> accept(t1, before.apply(v2), t3);
    }

    /**
     * 第三引数への入力を関数beforeに適用し、その結果をこのConsumerの第三引数に適用する合成Consumerを返します。
     *
     * <p>いずれかの関数の評価時にスローされた例外は呼び出し元に中継されます。
     *
     * @param before このConsumerが適用される前に第三引数に適用される関数
     * @param <V3>　合成Consumerの第三引数の型
     * @return 合成Consumer
     */
    @NotNull
    default <V3> NC3<T1, T2, V3> compose3(@NotNull NF1<? super V3, ? extends T3> before) {
        return (t1, t2, v3) -> accept(t1, t2, before.apply(v3));
    }

    /**
     * このConsumerの第一引数と第二引数を入れ替えたConsumerを返します。
     *
     * @return 第一引数と第二引数を入れ替えたConsumer
     */
    @NotNull
    default NC3<T2, T1, T3> swap2() {
        return (t2, t1, t3) -> accept(t1, t2, t3);
    }

    /**
     * このConsumerの第一引数と第三引数を入れ替えたConsumerを返します。
     *
     * @return 第一引数と第三引数を入れ替えたConsumer
     */
    @NotNull
    default NC3<T3, T2, T1> swap3() {
        return (t3, t2, t1) -> accept(t1, t2, t3);
    }

    /**
     * このConsumerに引数を適用するActionを返します。
     *
     * <p>このconsumerの実行時にスローされた例外は呼び出し元に中継されます。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @param t3 第三引数
     * @return Action
     */
    @NotNull
    default A asA(@NotNull T1 t1, @NotNull T2 t2, @NotNull T3 t3) {
        return () -> accept(t1, t2, t3);
    }

    /**
     * このConsumerに引数を適用するActionを返します。
     *
     * <p>このconsumerの実行時にスローされた例外は呼び出し元に中継されます。
     * 引数は遅延評価されます。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @param t3 第三引数
     * @return Action
     */
    @NotNull
    default A asA(@NotNull NS<? extends T1> t1, @NotNull NS<? extends T2> t2, @NotNull NS<? extends T3> t3) {
        return () -> accept(t1.get(), t2.get(), t3.get());
    }

    /**
     * ラムダやメソッド参照から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数をそのまま返します。
     *
     * <p>このメソッドの呼び出しに対して様々なメソッドをチェインできます。
     *
     * @param c3 ラムダやメソッド参照で記述されたConsumer
     * @param <T1> 第一引数の型
     * @param <T2> 第二引数の型
     * @param <T3> 第三引数の型
     * @return 引数に渡されたConsumer
     */
    @NotNull
    static <T1, T2, T3> NC3<T1, T2, T3> of(@NotNull NC3<T1, T2, T3> c3) {
        return c3;
    }

    /**
     * 型変数の変性を表すキャストメソッド。
     *
     * @param c3 キャスト対象
     * @param <T1> キャスト後第一引数の型
     * @param <T2> キャスト後第二引数の型
     * @param <T3> キャスト後第三引数の型
     * @return キャスト対象の参照
     */
    @SuppressWarnings("unchecked")
    @NotNull
    static <T1, T2, T3> NC3<T1, T2, T3> cast(@NotNull NC3<? super T1, ? super T2, ? super T3> c3) {
        return (NC3<T1, T2, T3>) c3;
    }

    /**
     * 何も行わないConsumerを返します。
     *
     * @param <T1> 第一引数の型
     * @param <T2> 第二引数の型
     * @param <T3> 第三引数の型
     * @return 何も行わないConsumer
     */
    @NotNull
    static <T1, T2, T3> NC3<T1, T2, T3> doNothing() {
        return NFunctionSupport.doNothingC3();
    }
}
