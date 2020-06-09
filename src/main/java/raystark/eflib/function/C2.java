package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface C2<T1, T2> {
    void accept(T1 t1, T2 t2);

    @NotNull
    default C1<T2> apply(T1 t1) {
        return t2 -> accept(t1, t2);
    }

    @NotNull
    default C2<T1, T2> next(@NotNull C2<? super T1, ? super T2> c2) {
        return (t1, t2) -> {
            accept(t1, t2);
            c2.accept(t1, t2);
        };
    }

    @NotNull
    default C2<T2, T1> swap2() {
        return (t2, t1) -> accept(t1, t2);
    }

    @NotNull
    default A asAction(T1 t1, T2 t2) {
        return () -> accept(t1, t2);
    }

    @NotNull
    static <T1, T2> C2<T1, T2> of(@NotNull C2<T1, T2> c2) {
        return c2;
    }
}
