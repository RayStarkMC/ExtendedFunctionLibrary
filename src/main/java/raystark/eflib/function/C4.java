package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface C4<T1, T2, T3, T4> {
    void accept(T1 t1, T2 t2, T3 t3, T4 t4);

    @NotNull
    default C3<T2, T3, T4> apply(T1 t1) {
        return (t2, t3, t4) -> accept(t1, t2, t3, t4);
    }

    @NotNull
    default C2<T3, T4> apply(T1 t1, T2 t2) {
        return (t3, t4) -> accept(t1, t2, t3, t4);
    }

    @NotNull
    default C1<T4> apply(T1 t1, T2 t2, T3 t3) {
        return t4 -> accept(t1, t2, t3, t4);
    }

    @NotNull
    default C4<T1, T2, T3, T4> next(C4<? super T1, ? super T2, ? super T3, ? super T4> after) {
        return (t1, t2, t3, t4) -> {
            accept(t1, t2, t3, t4);
            after.accept(t1, t2, t3, t4);
        };
    }

    @NotNull
    default Action asAction(T1 t1, T2 t2, T3 t3, T4 t4) {
        return () -> accept(t1, t2, t3, t4);
    }

    @NotNull
    static <T1, T2, T3, T4> C4<T1, T2, T3, T4> of(C4<T1, T2, T3, T4> c4) {
        return c4;
    }
}
