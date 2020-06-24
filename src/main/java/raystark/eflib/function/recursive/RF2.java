package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.function.F2;

@FunctionalInterface
public interface RF2<T1, T2, R> {
    @NotNull
    TailCall<R> apply(@Nullable T1 t1, @Nullable T2 t2, @NotNull RF2<T1, T2, R> self);

    @NotNull
    default TailCall<R> apply(@Nullable T1 t1, @Nullable T2 t2) {
        return apply(t1, t2, this);
    }

    @NotNull
    static <T1, T2, R> F2<T1, T2, R> of(@NotNull RF2<T1, T2, R> rf2) {
        return (t1, t2) -> rf2.apply(t1, t2).evaluate();
    }
}
