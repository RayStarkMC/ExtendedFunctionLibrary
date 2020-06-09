package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface F2<T1, T2, R> extends F1<T1, F1<T2, R>> {
    R apply(T1 t1, T2 t2);

    @NotNull
    @Override
    default F1<T2, R> apply(T1 t1) {
        return t2 -> apply(t1, t2);
    }

    @NotNull
    default <V> F2<T1, T2, V> then2(@NotNull F1<? super R, ? extends V> after) {
        return (t1, t2) -> after.apply(apply(t1, t2));
    }

    @Override
    @NotNull
    default <V1> F2<V1, T2, R> compose1(@NotNull F1<? super V1, ? extends T1> before) {
        return (v1, t2) -> apply(before.apply(v1), t2);
    }

    @NotNull
    default <V2> F2<T1, V2, R> compose2(@NotNull F1<? super V2, ? extends T2> before) {
        return (t1, v2) -> apply(t1, before.apply(v2));
    }

    @NotNull
    default F2<T2, T1, R> swap2() {
        return (t2, t1) -> apply(t1, t2);
    }

    @NotNull
    default S<R> asS(T1 t1, T2 t2) {
        return () -> apply(t1, t2);
    }

    @NotNull
    default C2<T1, T2> asC2(@NotNull C1<? super R> after) {
        return (t1, t2) -> after.accept(apply(t1, t2));
    }

    @NotNull
    static <T1, T2, R> F2<T1, T2, R> of(@NotNull F2<T1, T2, R> f2) {
        return f2;
    }
}
