package raystark.eflib.exhandler.function;

import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface F1Th2<T1, R, X1 extends Throwable, X2 extends Throwable> {
    R apply(@Nullable T1 t1) throws X1, X2;
}
