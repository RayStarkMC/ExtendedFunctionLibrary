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
    private volatile NS<? extends T> s;

    private MLazy(NS<? extends T> s) {
        this.s = s;
    }

    @NotNull
    public static <T> MLazy<T> of(@NotNull NS<? extends T> s) {
        return new MLazy<>(s);
    }

    @NotNull
    public T get() {
        //二重チェックイディオム
        T result = value;
        if(result == null) {
            synchronized (this) {
                result = value;
                if(result == null) {
                    value = result = s.get();
                    s = null; //初期化以降sは不要なためGC対象にするためnull代入
                }
            }
        }
        return result;
    }
}
