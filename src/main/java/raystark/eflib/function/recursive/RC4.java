package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.function.C4;

@FunctionalInterface
public interface RC4<T1, T2, T3, T4> {
    @NotNull
    VoidTailCall accept(@Nullable T1 t1, @Nullable T2 t2, @Nullable T3 t3, @Nullable T4 t4, @NotNull RC4<T1, T2, T3, T4> self);

    @NotNull
    default VoidTailCall accept(@Nullable T1 t1, @Nullable T2 t2, @Nullable T3 t3, @Nullable T4 t4) {
        return accept(t1, t2, t3, t4, this);
    }

    @NotNull
    static <T1, T2, T3, T4> C4<T1, T2, T3, T4> of(@NotNull RC4<T1, T2, T3, T4> rc4) {
        return (t1, t2, t3, t4) -> rc4.accept(t1, t2, t3, t4).execute();
    }
}
