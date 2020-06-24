package raystark.eflib.function.notnull;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface NS<T> {
    @NotNull
    T get();

    @NotNull
    default <R> NS<R> then(@NotNull NF1<? super T, ? extends R> after) {
        return () -> after.apply(this.get());
    }
}
