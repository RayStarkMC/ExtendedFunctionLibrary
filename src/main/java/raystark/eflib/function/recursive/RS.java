package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.S;

@FunctionalInterface
public interface RS<T> {
    @NotNull
    TailCall<T> get(@NotNull RS<T> self);

    @NotNull
    default TailCall<T> get() {
        return get(this);
    }

    @NotNull
    static <T> S<T> of(@NotNull RS<T> rs) {
        return () -> rs.get().evaluate();
    }

    @NotNull
    static <T> S<T> of(@NotNull TailCallS<T> rs) {
        return () -> rs.get().evaluate();
    }

    @FunctionalInterface
    interface TailCallS<T> {
        @NotNull
        TailCall<T> get();
    }
}
