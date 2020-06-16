package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.function.C1;

@FunctionalInterface
public interface RC1<T1> {
    @NotNull
    VoidTailCall accept(@Nullable T1 t1, @NotNull RC1<T1> self);

    @NotNull
    default VoidTailCall accept(@Nullable T1 t1) {
        return accept(t1, this);
    }

    @NotNull
    static <T1> C1<T1> of(@NotNull RC1<T1> rc1) {
        return t1 -> rc1.accept(t1).execute();
    }

    @NotNull
    static <T1> C1<T1> of(@NotNull TailCallC1<T1> rc1) {
        return t1 -> rc1.accept(t1).execute();
    }

    @FunctionalInterface
    interface TailCallC1<T1> {
        @NotNull
        VoidTailCall accept(@Nullable T1 t1);
    }
}
