package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * 型T1の値を受け取り値を返さない手続きです。
 *
 * <p>このインターフェースは{@link C1#accept}を関数メソッドに持つ関数型インターフェースです。
 *
 * <p>このインターフェースは{@link Consumer}に対応します。{@link Consumer}に変換する場合次のイディオムが使えます。
 *
 * <pre>{@code
 *  C1<T1> c1 = SomeClass1::someMethod;
 *  Consumer c2 = c1::accept;
 * }</pre>
 *
 * <p>このConsumerには合成Consumerを作成するメソッド、自身を他の関数に変換するメソッド、ラムダやメソッド参照からインスタンスを作成するメソッドが定義されています。
 *
 * @param <T1> 第一引数の型
 */
@FunctionalInterface
public interface C1<T1> {
    /**
     * 引数をこのConsumerに適用します。
     *
     * @param t1 第一引数
     */
    void accept(@Nullable T1 t1);

    /**
     * このConsumerを実行した後にConsumer afterを実行する合成Consumerを返します。
     *
     * <p>いずれかの関数の実行時にスローされた例外は呼び出し元に中継されます。
     *
     * @param after このConsumerの後に実行されるConsumer
     * @return 合成Consumer
     */
    @NotNull
    default C1<T1> next(@NotNull C1<? super T1> after) {
        return t1 -> {
            this.accept(t1);
            after.accept(t1);
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
    default C1<T1> next(@NotNull A after) {
        return t1 -> {
            this.accept(t1);
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
    default C1<T1> prev(@NotNull C1<? super T1> before) {
        return t1 -> {
            before.accept(t1);
            this.accept(t1);
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
    default C1<T1> prev(@NotNull A before) {
        return t1 -> {
            before.run();
            this.accept(t1);
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
    default <V1> C1<V1> compose1(@NotNull F1<? super V1, ? extends T1> before) {
        return v1 -> accept(before.apply(v1));
    }

    /**
     * このConsumerに引数を適用するActionを返します。
     *
     * <p>このconsumerの実行時にスローされた例外は呼び出し元に中継されます。
     *
     * @param t1 第一引数
     * @return Action
     */
    @NotNull
    default A asA(@Nullable T1 t1) {
        return () -> accept(t1);
    }

    /**
     * このConsumerに引数を適用するActionを返します。
     *
     * <p>このconsumerの実行時にスローされた例外は呼び出し元に中継されます。
     * 引数は遅延評価されます。
     *
     * @param t1 第一引数
     * @return Action
     */
    @NotNull
    default A asA(@NotNull S<? extends T1> t1) {
        return () -> accept(t1.get());
    }

    /**
     * ラムダやメソッド参照から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数をそのまま返します。
     *
     * <p>このメソッドの呼び出しに対して様々なメソッドをチェインできます。
     *
     * @param c1 ラムダやメソッド参照で記述されたConsumer
     * @param <T1> 第一引数の型
     * @return 引数に渡されたConsumer
     */
    @NotNull
    static <T1> C1<T1> of(@NotNull C1<T1> c1) {
        return c1;
    }
}