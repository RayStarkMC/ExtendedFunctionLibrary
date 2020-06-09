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
    default <V1> C2<V1, T2> compose1(@NotNull F1<? super V1, ? extends T1> before) {
        return (v1, t2) -> accept(before.apply(v1), t2);
    }

    @NotNull
    default <V2> C2<T1, V2> compose2(@NotNull F1<? super V2, ? extends T2> before) {
        return (t1, v2) -> accept(t1, before.apply(v2));
    }

    @NotNull
    default C2<T2, T1> swap2() {
        return (t2, t1) -> accept(t1, t2);
    }

    @NotNull
    default A asA(T1 t1, T2 t2) {
        return () -> accept(t1, t2);
    }

    @NotNull
    static <T1, T2> C2<T1, T2> of(@NotNull C2<T1, T2> c2) {
        return c2;
    }
}
