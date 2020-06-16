package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.function.F3;

public interface RF3<T1, T2, T3, R> {
    @NotNull
    TailCall<R> apply(@Nullable T1 t1, @Nullable T2 t2, @Nullable T3 t3, @NotNull RF3<T1, T2, T3, R> self);

    @NotNull
    default TailCall<R> apply(@Nullable T1 t1, @Nullable T2 t2, @Nullable T3 t3) {
        return apply(t1, t2, t3, this);
    }

    @NotNull
    static <T1, T2, T3, R> F3<T1, T2, T3, R> of(@NotNull RF3<T1, T2, T3, R> rf3) {
        return (t1, t2, t3) -> rf3.apply(t1, t2, t3).get();
    }

    @NotNull
    static <T1, T2, T3, R> F3<T1, T2, T3, R> of(@NotNull TailCallF3<T1, T2, T3, R> rf3) {
        return (t1, t2, t3) -> rf3.apply(t1, t2, t3).get();
    }

    @FunctionalInterface
    interface TailCallF3<T1, T2, T3, R> {
        @NotNull
        TailCall<R> apply(@Nullable T1 t1, @Nullable T2 t2, @Nullable T3 t3);
    }
}
