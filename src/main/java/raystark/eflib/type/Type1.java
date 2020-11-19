package raystark.eflib.type;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface Type1<T> {
    @NotNull T unwrap();
}
