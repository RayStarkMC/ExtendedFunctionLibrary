package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.function.F1;

@FunctionalInterface
public interface RF1<T1, R> {
    @NotNull
    TailCall<R> apply(@Nullable T1 t1, @NotNull RF1<T1, R> self);

    @NotNull
    default TailCall<R> apply(@Nullable T1 t1) {
        return apply(t1, this);
    }

    @NotNull
    static <T1, R> F1<T1, R> of(@NotNull RF1<T1, R> rf1) {
        return t1 -> rf1.apply(t1).evaluate();
    }

    @NotNull
    static <T1, R> F1<T1, R> of(@NotNull TailCallF1<T1, R> f1) {
        return t1 -> f1.apply(t1).evaluate();
    }

    @FunctionalInterface
    interface TailCallF1<T1, R> {
        @NotNull
        TailCall<R> apply(@Nullable T1 t1);
    }
}
