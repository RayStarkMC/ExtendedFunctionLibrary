package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;

public interface P4<T1, T2, T3, T4> {
    boolean test(T1 t1, T2 t2, T3 t3, T4 t4);

    @NotNull
    default P3<T2, T3, T4> apply(T1 t1) {
        return (t2, t3, t4) -> test(t1, t2, t3, t4);
    }

    @NotNull
    default P2<T3, T4> apply(T1 t1, T2 t2) {
        return (t3, t4) -> test(t1, t2, t3, t4);
    }

    @NotNull
    default P1<T4> apply(T1 t1, T2 t2, T3 t3) {
        return t4 -> test(t1, t2, t3,t4);
    }

    @NotNull
    default P4<T1, T2, T3, T4> and(@NotNull P4<? super T1, ? super T2, ? super T3, ? super T4> other) {
        return (t1, t2, t3, t4) -> test(t1, t2, t3, t4) && other.test(t1, t2, t3, t4);
    }

    @NotNull
    default P4<T1, T2, T3, T4> or(@NotNull P4<? super T1, ? super T2, ? super T3, ? super T4> other) {
        return (t1, t2, t3, t4) -> test(t1, t2, t3, t4) || other.test(t1, t2, t3, t4);
    }

    @NotNull
    default P4<T1, T2, T3, T4> not() {
        return (t1, t2, t3, t4) -> !test(t1, t2, t3, t4);
    }

    @NotNull
    default P4<T2, T1, T3, T4> swap2() {
        return (t2, t1, t3, t4) -> test(t1, t2, t3, t4);
    }

    @NotNull
    default P4<T3, T2, T1, T4> swap3() {
        return (t3, t2, t1, t4) -> test(t1, t2, t3, t4);
    }

    @NotNull
    default P4<T4, T2, T3, T1> swap4() {
        return (t4, t2, t3, t1) -> test(t1, t2, t3, t4);
    }

    @NotNull
    static <T1, T2, T3, T4> P4<T1, T2, T3, T4> of(@NotNull P4<T1, T2, T3, T4> p4) {
        return p4;
    }
}
