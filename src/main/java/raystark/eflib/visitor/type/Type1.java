package raystark.eflib.visitor.type;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface Type1<T> {
    @NotNull T unwrap();
}
