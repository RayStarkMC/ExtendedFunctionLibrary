package raystark.eflib.function.notnull;

import org.jetbrains.annotations.NotNull;

public interface NNF1<T, R> {
    @NotNull
    R apply(@NotNull T t);
}
