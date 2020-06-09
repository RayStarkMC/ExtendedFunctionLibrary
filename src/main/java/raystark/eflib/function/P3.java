package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface P3<T1, T2, T3> {
    boolean test(T1 t1, T2 t2, T3 t3);

    @NotNull
    default P2<T2, T3> apply(T1 t1) {
        return (t2, t3) -> test(t1, t2, t3);
    }

    @NotNull
    default P1<T3> apply(T1 t1, T2 t2) {
        return t3 -> test(t1, t2, t3);
    }

    @NotNull
    default P3<T1, T2, T3> and(@NotNull P3<? super T1, ? super T2, ? super T3> other) {
        return (t1, t2, t3) -> test(t1, t2, t3) && other.test(t1, t2, t3);
    }

    @NotNull
    default P3<T1, T2, T3> or(@NotNull P3<? super T1, ? super T2, ? super T3> other) {
        return (t1, t2, t3) -> test(t1, t2, t3) || other.test(t1, t2, t3);
    }

    @NotNull
    default P3<T1, T2, T3> not() {
        return (t1, t2, t3) -> !test(t1, t2, t3);
    }

    @NotNull
    default <V1> P3<V1, T2, T3> compose1(@NotNull F1<? super V1, ? extends T1> before) {
        return (v1, t2, t3) -> test(before.apply(v1), t2, t3);
    }

    @NotNull
    default <V2> P3<T1, V2, T3> compose2(@NotNull F1<? super V2, ? extends T2> before) {
        return (t1, v2, t3) -> test(t1, before.apply(v2), t3);
    }

    @NotNull
    default <V3> P3<T1, T2, V3> compose3(@NotNull F1<? super V3, ? extends T3> before) {
        return (t1, t2, v3) -> test(t1, t2, before.apply(v3));
    }

    @NotNull
    default P3<T2, T1, T3> swap2() {
        return (t2, t1, t3) -> test(t1, t2, t3);
    }

    @NotNull
    default P3<T3, T2, T1> swap3() {
        return (t3, t2, t1) -> test(t1, t2, t3);
    }

    @NotNull
    static <T1, T2, T3> P3<T1, T2, T3> of(@NotNull P3<T1, T2, T3> p3) {
        return p3;
    }
}
