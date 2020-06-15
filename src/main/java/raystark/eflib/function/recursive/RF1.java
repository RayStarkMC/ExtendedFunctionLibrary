package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.F1;

@FunctionalInterface
public interface RF1<T1, R> {
    @NotNull
    TailCall<R> apply(T1 t1, RF1<T1, R> self);

    default TailCall<R> apply(T1 t1) {
        return apply(t1, this);
    }

    @NotNull
    static <T1, R> F1<T1, R> of(@NotNull RF1<T1, R> rf1) {
        return t1 -> rf1.apply(t1).get();
    }
}
