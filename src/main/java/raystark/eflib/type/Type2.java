package raystark.eflib.type;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface Type2<T> {
    @NotNull T unwrap();
}
