package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.function.P2;

@FunctionalInterface
public interface RP2<T1, T2> {
    @NotNull
    BooleanTailCall test(@Nullable T1 t1, @Nullable T2 t2, @NotNull RP2<T1, T2> self);

    @NotNull
    default BooleanTailCall test(@Nullable T1 t1, @Nullable T2 t2) {
        return test(t1, t2, this);
    }

    @NotNull
    static <T1, T2> P2<T1, T2> of(@NotNull RP2<T1, T2> rp2) {
        return (t1, t2) -> rp2.test(t1, t2).evaluate();
    }

    @NotNull
    static <T1, T2> P2<T1, T2> of(@NotNull TailCallP2<T1, T2> rp2) {
        return (t1, t2) -> rp2.test(t1, t2).evaluate();
    }

    @FunctionalInterface
    interface TailCallP2<T1, T2> {
        @NotNull
        BooleanTailCall test(@Nullable T1 t1, @Nullable T2 t2);
    }
}
