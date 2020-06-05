package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface F4<T1, T2, T3, T4, R> extends F3<T1, T2, T3, F1<T4, R>> {
    R apply(T1 t1, T2 t2, T3 t3, T4 t4);

    @NotNull
    @Override
    default F1<T4, R> apply(T1 t1, T2 t2, T3 t3) {
        return t4 -> apply(t1, t2, t3, t4);
    }

    @NotNull
    default <V> F4<T1, T2, T3, T4, V> then4(@NotNull F1<? super R, ? extends V> after) {
        return (t1, t2, t3, t4) -> after.apply(apply(t1, t2, t3, t4));
    }

    @NotNull
    default S<R> asS(T1 t1, T2 t2, T3 t3, T4 t4) {
        return () -> apply(t1, t2, t3, t4);
    }

    @NotNull
    static <T1, T2, T3, T4, R> F4<T1, T2, T3, T4, R> of(@NotNull F4<T1, T2, T3, T4, R> f4) {
        return f4;
    }
}
