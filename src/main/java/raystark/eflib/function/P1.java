package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface P1<T1> {
    boolean test(T1 t1);

    @NotNull
    default P1<T1> and(@NotNull P1<? super T1> other) {
        return t1 -> test(t1) && other.test(t1);
    }

    @NotNull
    default P1<T1> or(@NotNull P1<? super T1> other) {
        return t1 -> test(t1) && other.test(t1);
    }

    @NotNull
    default P1<T1> not() {
        return t1 -> !test(t1);
    }

    @NotNull
    static <T1> P1<T1> of(@NotNull P1<T1> p1) {
        return p1;
    }
}
