package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;

/**
 * 引数を受け取らず値を返さない手続きです。
 *
 * <p>このインターフェースは{@link A#run}を関数メソッドに持つ関数型インターフェースです。
 *
 * <p>このインターフェースは{@link Runnable}に対応します。{@link Runnable}に変換する場合次のイディオムが使えます。
 *
 * <pre>{@code
 *  A a = SomeClass1::someMethod;
 *  Runnable runnable = a::run;
 * }</pre>
 *
 * <p>このActionには合成Actionを作成するメソッド、ラムダやメソッド参照からインスタンスを作成するメソッドが定義されています。
 */
@FunctionalInterface
public interface A {
    /**
     * 手続きを実行します。
     */
    void run();

    /**
     * このActionを実行した後にAction afterを実行する合成Actionを返します。
     *
     * <p>いずれかの関数の実行時にスローされた例外は呼び出し元に中継されます。
     *
     * @param after このActionの後に実行されるAction
     * @return 合成Action
     */
    @NotNull
    default A next(@NotNull A after) {
        return () -> {
            this.run(); after.run();
        };
    }

    /**
     * このActionを実行する前にAction beforeを実行する合成Actionを返します。
     *
     * <p>いずれかの関数の実行時にスローされた例外は呼び出し元に中継されます。
     *
     * @param before このActionの前に実行されるAction
     * @return 合成Action
     */
    @NotNull
    default A prev(@NotNull A before) {
        return () -> {
            before.run(); this.run();
        };
    }

    /**
     * ラムダやメソッド参照から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数をそのまま返します。
     *
     * <p>このメソッドの呼び出しに対して様々なメソッドをチェインできます。
     *
     * @param a ラムダやメソッド参照で記述されたAction
     * @return 引数に渡されたAction
     */
    @NotNull
    static A of(@NotNull A a) {
        return a;
    }

    static A doNothing() {
        return FunctionSupport.doNothing();
    }
}