package raystark.eflib.function.notnull;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface NNS<T> {
    @NotNull
    T get();

    @NotNull
    default <R> NNS<R> then(@NotNull NNF1<? super T, ? extends R> after) {
        return () -> after.apply(this.get());
    }
}
