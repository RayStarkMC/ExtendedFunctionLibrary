package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.function.P4;

@FunctionalInterface
public interface RP4<T1, T2, T3, T4> {
    @NotNull
    BooleanTailCall test(@Nullable T1 t1, @Nullable T2 t2, @Nullable T3 t3, @Nullable T4 t4, @NotNull RP4<T1, T2, T3, T4> self);

    @NotNull
    default BooleanTailCall test(@Nullable T1 t1, @Nullable T2 t2, @Nullable T3 t3, @Nullable T4 t4) {
        return test(t1, t2, t3, t4, this);
    }

    @NotNull
    static <T1, T2, T3, T4> P4<T1, T2, T3, T4> of(@NotNull RP4<T1, T2, T3, T4> rp4) {
        return (t1, t2, t3, t4) -> rp4.test(t1, t2, t3, t4).evaluate();
    }
}
