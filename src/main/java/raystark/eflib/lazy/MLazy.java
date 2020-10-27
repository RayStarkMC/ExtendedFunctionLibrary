package raystark.eflib.lazy;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NS;

/**
 * マルチスレッド用遅延初期化型("M"ulti-Thread "Lazy")
 *
 * @param <T>
 */
public final class MLazy<T> {
    private volatile T value;
    private volatile NS<? extends T> initializer;

    private MLazy(NS<? extends T> initializer) {
        this.initializer = initializer;
    }

    @NotNull
    public static <T> MLazy<T> of(@NotNull NS<? extends T> initializer) {
        return new MLazy<>(initializer);
    }

    @NotNull
    public T get() {
        //二重チェックイディオム
        T result = value;
        if(result == null) {
            synchronized (this) {
                result = value;
                if(result == null) {
                    value = result = initializer.get();
                    initializer = null; //初期化以降不要なinitializerをGC対象にするためのnull代入
                }
            }
        }
        return result;
    }
}
