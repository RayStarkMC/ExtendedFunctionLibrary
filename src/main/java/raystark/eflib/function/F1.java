package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface F1<T1, R> {
    R apply(T1 t1);

    @NotNull
    default <V> F1<T1, V> then1(@NotNull F1<? super R, ? extends V> after) {
        return t1 -> after.apply(this.apply(t1));
    }

    @NotNull
    default S<R> asS(T1 t1) {
        return () -> apply(t1);
    }

    @NotNull
    static <T1, R> F1<T1, R> of(@NotNull F1<T1, R> f1) {
        return f1;
    }
}
