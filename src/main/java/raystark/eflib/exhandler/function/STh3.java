package raystark.eflib.exhandler.function;

import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface STh3<T, X1 extends Exception, X2 extends Exception, X3 extends Exception> {
    @Nullable
    T get() throws X1, X2, X3;
}
