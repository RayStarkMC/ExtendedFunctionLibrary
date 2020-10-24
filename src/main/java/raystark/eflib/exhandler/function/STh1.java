package raystark.eflib.exhandler.function;

import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface STh1<T, X1 extends Throwable> {
    @Nullable
    T get() throws X1;
}
