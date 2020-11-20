package raystark.eflib.type;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface TypeVar1<T> {
    @NotNull T unwrap();
}
