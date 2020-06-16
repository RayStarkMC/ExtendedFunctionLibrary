package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.function.P3;

public interface RP3<T1, T2, T3> {
    @NotNull
    BooleanTailCall test(@Nullable T1 t1, @Nullable T2 t2, @Nullable T3 t3, @NotNull RP3<T1, T2, T3> self);

    @NotNull
    default BooleanTailCall test(@Nullable T1 t1, @Nullable T2 t2, @Nullable T3 t3) {
        return test(t1, t2, t3, this);
    }

    @NotNull
    static <T1, T2, T3> P3<T1, T2, T3> of(@NotNull RP3<T1, T2, T3> rp3) {
        return (t1, t2, t3) -> rp3.test(t1, t2, t3).evaluate();
    }

    @NotNull
    static <T1, T2, T3> P3<T1, T2, T3> of(@NotNull TailCallP3<T1, T2, T3> rp3) {
        return (t1, t2, t3) -> rp3.test(t1, t2, t3).evaluate();
    }

    @FunctionalInterface
    interface TailCallP3<T1, T2, T3> {
        @NotNull
        BooleanTailCall test(@Nullable T1 t1, @Nullable T2 t2, @Nullable T3 t3);
    }
}
