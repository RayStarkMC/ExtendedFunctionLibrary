package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.function.C3;

@FunctionalInterface
public interface RC3<T1, T2, T3> {
    @NotNull
    VoidTailCall accept(@Nullable T1 t1, @Nullable T2 t2, @Nullable T3 t3, @NotNull RC3<T1, T2, T3> self);

    @NotNull
    default VoidTailCall accept(@Nullable T1 t1, @Nullable T2 t2, @Nullable T3 t3) {
        return accept(t1, t2, t3, this);
    }

    @NotNull
    static <T1, T2, T3> C3<T1, T2, T3> of(@NotNull RC3<T1, T2, T3> rc3) {
        return (t1, t2, t3) -> rc3.accept(t1, t2, t3).execute();
    }
}
