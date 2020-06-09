package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface S<R> {
    R get();

    @NotNull
    default <V> S<V> then(@NotNull F1<? super R, ? extends V> after) {
        return () -> after.apply(get());
    }

    @NotNull
    default A asAction(@NotNull C1<? super R> c1) {
        return () -> c1.accept(get());
    }

    @NotNull
    static <R> S<R> of(@NotNull S<R> s) {
        return s;
    }
}
