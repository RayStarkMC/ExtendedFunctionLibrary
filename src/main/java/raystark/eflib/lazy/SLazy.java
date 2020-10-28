package raystark.eflib.lazy;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NS;

/**
 * シングルスレッド用遅延初期化型("S"ingle-Thread "Lazy")
 *
 * @param <T> 遅延初期化される値の型
 */
public final class SLazy<T> {
    private T value;
    private NS<? extends T> initializer;

    private SLazy(NS<? extends T> initializer) {
        this.initializer = initializer;
    }

    /**
     * 値を算出するSupplierを指定してLazyを生成します。
     *
     * initializerとしてnullを返す関数を渡す事は禁止されています。そのような場合はOptionalを始めとした空コンテナの使用を検討してください。
     *
     * <p>渡されたinitializerの評価はLazy生成後に初めて{@link SLazy#get()}が呼ばれるまで遅延されます。
     * 値の初期化後はinitializerへの参照は破棄されます。
     *
     * @param initializer 値を算出するSupplier
     * @param <T> 生成する値の型
     * @return initializerにより遅延初期化される値を表すSLazyのインスタンス
     */
    @NotNull
    public static <T> SLazy<T> of(@NotNull NS<? extends T> initializer) {
        return new SLazy<>(initializer);
    }

    /**
     * initializerによって生成された値を取得します。
     *
     * <p>複数のスレッドからこのメソッドを呼び出す事は禁止されています。そのような呼び出しが行われる場合は{@link MLazy}の使用を検討してください。
     *
     * <p>値は同期されていない単一チェックイディオムにより遅延初期化されます。また、initializerへの参照はこのメソッドの初回実行後に破棄されます。
     * 複数のスレッドからこのメソッドが呼び出された場合、initializerが複数回呼ばれたり、予期せぬ例外が発生する可能性があります。
     *
     * @return 遅延初期化された値
     */
    @NotNull
    public T get() {
        //同期無しの単一チェックイディオム
        var result = value;
        if(result == null) {
            value = result = initializer.get();
            initializer = null; //初期化以降不要なinitializerをGC対象にするためのnull代入
        }
        return result;
    }
}
