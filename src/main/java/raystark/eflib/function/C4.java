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
    default C4<T1, T2, T3, T4> next(@NotNull A after) {
        return (t1, t2, t3, t4) -> {
            this.accept(t1, t2, t3, t4);
            after.run();
        };
    }

    @NotNull
    default C4<T1, T2, T3, T4> prev(@NotNull C4<? super T1, ? super T2, ? super T3, ? super T4> before) {
        return (t1, t2, t3, t4) -> {
            before.accept(t1, t2, t3, t4);
            this.accept(t1, t2, t3, t4);
        };
    }

    @NotNull
    default C4<T1, T2, T3, T4> prev(@NotNull A before) {
        return (t1, t2, t3, t4) -> {
            before.run();
            this.accept(t1, t2, t3, t4);
        };
    }

    @NotNull
    default <V1> C4<V1, T2, T3, T4> compose1(@NotNull F1<? super V1, ? extends T1> before) {
        return (v1, t2, t3, t4) -> accept(before.apply(v1), t2, t3, t4);
    }

    @NotNull
    default <V2> C4<T1, V2, T3, T4> compose2(@NotNull F1<? super V2, ? extends T2> before) {
        return (t1, v2, t3, t4) -> accept(t1, before.apply(v2), t3, t4);
    }

    @NotNull
    default <V3> C4<T1, T2, V3, T4> compose3(@NotNull F1<? super V3, ? extends T3> before) {
        return (t1, t2, v3, t4) -> accept(t1, t2, before.apply(v3), t4);
    }

    @NotNull
    default <V4> C4<T1, T2, T3, V4> compose4(@NotNull F1<? super V4, ? extends T4> before) {
        return (t1, t2, t3, v4) -> accept(t1, t2, t3, before.apply(v4));
    }

    @NotNull
    default C4<T2, T1, T3, T4> swap2() {
        return (t2, t1, t3, t4) -> accept(t1, t2, t3, t4);
    }

    @NotNull
    default C4<T3, T2, T1, T4> swap3() {
        return (t3, t2, t1, t4) -> accept(t1, t2, t3, t4);
    }

    @NotNull
    default C4<T4, T2, T3, T1> swap4() {
        return (t4, t2, t3, t1) -> accept(t1, t2, t3, t4);
    }

    @NotNull
    default A asA(T1 t1, T2 t2, T3 t3, T4 t4) {
        return () -> accept(t1, t2, t3, t4);
    }

    @NotNull
    static <T1, T2, T3, T4> C4<T1, T2, T3, T4> of(C4<T1, T2, T3, T4> c4) {
        return c4;
    }
}
