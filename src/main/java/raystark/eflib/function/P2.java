package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface P2<T1, T2> {
    boolean test(T1 t1, T2 t2);

    @NotNull
    default P1<T2> apply(T1 t1) {
        return t2 -> test(t1, t2);
    }

    @NotNull
    default P2<T1, T2> and(@NotNull P2<? super T1, ? super T2> other) {
        return (t1, t2) -> test(t1, t2) && other.test(t1, t2);
    }

    @NotNull
    default P2<T1, T2> or(@NotNull P2<? super T1, ? super T2> other) {
        return (t1, t2) -> test(t1, t2) || other.test(t1, t2);
    }

    @NotNull
    default P2<T1, T2> not() {
        return (t1, t2) -> !test(t1, t2);
    }

    @NotNull
    default <V1> P2<V1, T2> compose1(@NotNull F1<? super V1, ? extends T1> before) {
        return (v1, t2) -> test(before.apply(v1), t2);
    }

    @NotNull
    default <V2> P2<T1, V2> compose2(@NotNull F1<? super V2, ? extends T2> before) {
        return (t1, v2) -> test(t1, before.apply(v2));
    }

    @NotNull
    default P2<T2, T1> swap2() {
        return (t2, t1) -> test(t1, t2);
    }

    @NotNull
    static <T1, T2> P2<T1, T2> of(@NotNull P2<T1, T2> p2) {
        return p2;
    }
}
