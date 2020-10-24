package raystark.eflib.exhandler.function;

import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface STh3<T, X1 extends Throwable, X2 extends Throwable, X3 extends Throwable> {
    @Nullable
    T get() throws X1, X2, X3;
}
