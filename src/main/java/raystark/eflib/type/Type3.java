package raystark.eflib.type;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface Type3<T> {
    @NotNull T unwrap();
}
