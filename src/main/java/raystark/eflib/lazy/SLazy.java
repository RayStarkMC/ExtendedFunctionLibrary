package raystark.eflib.lazy;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NS;

/**
 * シングルスレッド用遅延初期化型("S"ingle-Thread "Lazy")
 *
 * @param <T>
 */
public final class SLazy<T> {
    private T value;
    private NS<? extends T> initializer;

    private SLazy(NS<? extends T> initializer) {
        this.initializer = initializer;
    }

    @NotNull
    public static <T> SLazy<T> of(@NotNull NS<? extends T> initializer) {
        return new SLazy<>(initializer);
    }

    @NotNull
    public T get() {
        if(value == null) {
            value = initializer.get();
            initializer = null; //初期化以降不要なinitializerをGC対象にするためのnull代入
        }
        return value;
    }
}
