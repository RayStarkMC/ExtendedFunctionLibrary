package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface C3<T1, T2, T3> {
    void accept(T1 t1, T2 t2, T3 t3);

    @NotNull
    default C2<T2, T3> apply(T1 t1) {
        return (t2, t3) -> accept(t1, t2, t3);
    }

    @NotNull
    default C1<T3> apply(T1 t1, T2 t2) {
        return t3 -> accept(t1, t2, t3);
    }

    @NotNull
    default C3<T1, T2, T3> next(@NotNull C3<? super T1, ? super T2, ? super T3> after) {
        return (t1, t2, t3) -> {
            accept(t1, t2, t3);
            after.accept(t1, t2, t3);
        };
    }

    @NotNull
    default C3<T2, T1, T3> swap2() {
        return (t2, t1, t3) -> accept(t1, t2, t3);
    }

    @NotNull
    default C3<T3, T2, T1> swap3() {
        return (t3, t2, t1) -> accept(t1, t2, t3);
    }

    @NotNull
    default Action asAction(T1 t1, T2 t2, T3 t3) {
        return () -> accept(t1, t2, t3);
    }

    @NotNull
    static <T1, T2, T3> C3<T1, T2, T3> of(@NotNull C3<T1, T2, T3> c3) {
        return c3;
    }
}
