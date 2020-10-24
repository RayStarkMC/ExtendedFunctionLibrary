package raystark.eflib.exhandler.function;

import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface F1Th4<T1, R, X1 extends Throwable, X2 extends Throwable, X3 extends Throwable, X4 extends Throwable> {
    R apply(@Nullable T1 t1) throws X1, X2, X3, X4;
}
