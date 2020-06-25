package raystark.eflib.function.notnull;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.A;

import java.util.function.BiConsumer;

/**
 * 型T1の値を受け取り値を返さない手続きです。
 *
 * <p>このインターフェースは{@link NC2#accept}を関数メソッドに持つ関数型インターフェースです。
 * <p>この関数はnullを扱いません。
 *
 * <p>このインターフェースは{@link BiConsumer}に対応します。{@link BiConsumer}に変換する場合次のイディオムが使えます。
 *
 * <pre>{@code
 *  NC2<T1, T2> c1 = SomeClass1::someMethod;
 *  BiConsumer c2 = c1::accept;
 * }</pre>
 *
 * <p>このConsumerには部分適用を行うメソッド、合成Consumerを作成するメソッド、引数の順序を変えるメソッド、
 * 自身を他の関数に変換するメソッド、ラムダやメソッド参照からインスタンスを作成するメソッドが定義されています。
 *
 * @param <T1> 第一引数の型
 * @param <T2> 第二引数の型
 */
@FunctionalInterface
public interface NC2<T1, T2> {

    /**
     * 引数をこのConsumerに適用します。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     */
    void accept(@NotNull T1 t1, @NotNull T2 t2);

    /**
     * 第一引数までをこのConsumerに部分適用します。
     *
     * @param t1 第一引数
     * @return 引数が部分適用されたConsumer
     */
    @NotNull
    default NC1<T2> apply(@NotNull T1 t1) {
        return t2 -> accept(t1, t2);
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
    default NC2<T1, T2> next(@NotNull NC2<? super T1, ? super T2> after) {
        return (t1, t2) -> {
            accept(t1, t2);
            after.accept(t1, t2);
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
    default NC2<T1, T2> next(@NotNull A after) {
        return (t1, t2) -> {
            this.accept(t1, t2);
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
    default NC2<T1, T2> prev(@NotNull NC2<? super T1, ? super T2> before) {
        return (t1, t2) -> {
            before.accept(t1, t2);
            this.accept(t1, t2);
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
    default NC2<T1, T2> prev(@NotNull A before) {
        return (t1, t2) -> {
            before.run();
            this.accept(t1, t2);
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
    default <V1> NC2<V1, T2> compose1(@NotNull NF1<? super V1, ? extends T1> before) {
        return (v1, t2) -> accept(before.apply(v1), t2);
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
    default <V2> NC2<T1, V2> compose2(@NotNull NF1<? super V2, ? extends T2> before) {
        return (t1, v2) -> accept(t1, before.apply(v2));
    }

    /**
     * このConsumerの第一引数と第二引数を入れ替えたConsumerを返します。
     *
     * @return 第一引数と第二引数を入れ替えたConsumer
     */
    @NotNull
    default NC2<T2, T1> swap2() {
        return (t2, t1) -> accept(t1, t2);
    }

    /**
     * このConsumerに引数を適用するActionを返します。
     *
     * <p>このconsumerの実行時にスローされた例外は呼び出し元に中継されます。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @return Action
     */
    @NotNull
    default A asA(@NotNull T1 t1, @NotNull T2 t2) {
        return () -> accept(t1, t2);
    }

    /**
     * ラムダやメソッド参照から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数をそのまま返します。
     *
     * <p>このメソッドの呼び出しに対して様々なメソッドをチェインできます。
     *
     * @param c2 ラムダやメソッド参照で記述されたConsumer
     * @param <T1> 第一引数の型
     * @param <T2> 第二引数の型
     * @return 引数に渡されたConsumer
     */
    @NotNull
    static <T1, T2> NC2<T1, T2> of(@NotNull NC2<T1, T2> c2) {
        return c2;
    }
}
