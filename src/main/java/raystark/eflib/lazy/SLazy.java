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
    private NS<? extends T> s;

    private SLazy(NS<? extends T> s) {
        this.s = s;
    }

    @NotNull
    public static <T> SLazy<T> of(@NotNull NS<? extends T> s) {
        return new SLazy<>(s);
    }

    @NotNull
    public T get() {
        if(value == null) {
            value = s.get();
            s = null; //初期化以降sは不要なためGC対象にするためnull代入
        }
        return value;
    }
}
