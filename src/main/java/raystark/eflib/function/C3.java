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
    default C3<T1, T2, T3> next(@NotNull A after) {
        return (t1, t2, t3) -> {
            this.accept(t1, t2, t3);
            after.run();
        };
    }

    @NotNull
    default C3<T1, T2, T3> prev(@NotNull C3<? super T1, ? super T2, ? super T3> before) {
        return (t1, t2, t3) -> {
            before.accept(t1, t2, t3);
            this.accept(t1, t2, t3);
        };
    }

    @NotNull
    default C3<T1, T2, T3> prev(@NotNull A before) {
        return (t1, t2, t3) -> {
            before.run();
            this.accept(t1, t2, t3);
        };
    }

    @NotNull
    default <V1> C3<V1, T2, T3> compose1(@NotNull F1<? super V1, ? extends T1> before) {
        return (v1, t2, t3) -> accept(before.apply(v1), t2, t3);
    }

    @NotNull
    default <V2> C3<T1, V2, T3> compose2(@NotNull F1<? super V2, ? extends T2> before) {
        return (t1, v2, t3) -> accept(t1, before.apply(v2), t3);
    }

    @NotNull
    default <V3> C3<T1, T2, V3> compose3(@NotNull F1<? super V3, ? extends T3> before) {
        return (t1, t2, v3) -> accept(t1, t2, before.apply(v3));
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
    default A asA(T1 t1, T2 t2, T3 t3) {
        return () -> accept(t1, t2, t3);
    }

    @NotNull
    static <T1, T2, T3> C3<T1, T2, T3> of(@NotNull C3<T1, T2, T3> c3) {
        return c3;
    }
}
