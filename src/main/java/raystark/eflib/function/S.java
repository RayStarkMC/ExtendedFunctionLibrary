package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface S<T> {
    T get();

    @NotNull
    default <R> S<R> then(@NotNull F1<? super T, ? extends R> after) {
        return () -> after.apply(get());
    }

    @NotNull
    default A asAction(@NotNull C1<? super T> c1) {
        return () -> c1.accept(get());
    }

    @NotNull
    static <T> S<T> of(@NotNull S<T> s) {
        return s;
    }
}
