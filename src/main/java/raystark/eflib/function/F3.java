package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface F3<T1, T2, T3, R> extends F2<T1, T2, F1<T3, R>> {
    R apply(T1 t1, T2 t2, T3 t3);

    @NotNull
    @Override
    default F1<T3, R> apply(T1 t1, T2 t2) {
        return t3 -> apply(t1, t2, t3);
    }

    @NotNull
    default <V> F3<T1, T2, T3, V> then3(@NotNull F1<? super R, ? extends V> after) {
        return (t1, t2, t3) -> after.apply(apply(t1, t2, t3));
    }

    @Override
    @NotNull
    default F3<T2, T1, T3, R> swap2() {
        return (t2, t1, t3) -> apply(t1, t2, t3);
    }

    @NotNull
    default F3<T3, T2, T1, R> swap3() {
        return (t3, t2, t1) -> apply(t1, t2, t3);
    }

    @NotNull
    default S<R> asS(T1 t1, T2 t2, T3 t3) {
        return () -> apply(t1, t2, t3);
    }

    @NotNull
    static <T1, T2, T3, R> F3<T1, T2, T3, R> of(@NotNull F3<T1, T2, T3, R> f3) {
        return f3;
    }
}
