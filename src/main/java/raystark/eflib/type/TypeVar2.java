package raystark.eflib.type;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface TypeVar2<T> {
    @NotNull T unwrap();
}
