package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.function.F4;

public interface RF4<T1, T2, T3, T4, R> {
    @NotNull
    TailCall<R> apply(@Nullable T1 t1, @Nullable T2 t2, @Nullable T3 t3, @Nullable T4 t4, @NotNull RF4<T1, T2, T3, T4, R> self);

    @NotNull
    default TailCall<R> apply(@Nullable T1 t1, @Nullable T2 t2, @Nullable T3 t3, @Nullable T4 t4) {
        return apply(t1, t2, t3, t4, this);
    }

    @NotNull
    static <T1, T2, T3, T4, R> F4<T1, T2, T3, T4, R> of(@NotNull RF4<T1, T2, T3, T4, R> rf4) {
        return (t1, t2, t3, t4) -> rf4.apply(t1, t2, t3, t4).get();
    }

    @NotNull
    static <T1, T2, T3, T4, R> F4<T1, T2, T3, T4, R> of(@NotNull TailCallF4<T1, T2, T3, T4, R> rf4) {
        return (t1, t2, t3, t4) -> rf4.apply(t1, t2, t3, t4).get();
    }

    @FunctionalInterface
    interface TailCallF4<T1, T2, T3, T4, R> {
        @NotNull
        TailCall<R> apply(@Nullable T1 t1, @Nullable T2 t2, @Nullable T3 t3, @Nullable T4 t4);
    }
}
