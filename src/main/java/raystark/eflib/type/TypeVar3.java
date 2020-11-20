package raystark.eflib.type;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface TypeVar3<T> {
    @NotNull T unwrap();
}
