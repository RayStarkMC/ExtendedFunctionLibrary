package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.function.C2;

public interface RC2<T1, T2> {
    @NotNull
    VoidTailCall accept(@Nullable T1 t1, @Nullable T2 t2, @NotNull RC2<T1, T2> self);

    @NotNull
    default VoidTailCall accept(@Nullable T1 t1, @Nullable T2 t2) {
        return accept(t1, t2, this);
    }

    @NotNull
    static <T1, T2> C2<T1, T2> of(@NotNull RC2<T1, T2> rc2) {
        return (t1, t2) -> rc2.accept(t1, t2).execute();
    }

    @NotNull
    static <T1, T2> C2<T1, T2> of(@NotNull RC2.TailCallC2<T1, T2> rc2) {
        return (t1, t2) -> rc2.accept(t1, t2).execute();
    }

    @FunctionalInterface
    interface TailCallC2<T1, T2> {
        @NotNull
        VoidTailCall accept(@Nullable T1 t1, @Nullable T2 t2);
    }
}
