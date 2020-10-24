package raystark.eflib.exhandler.function;

import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface F2Th3<T1, T2, R, X1 extends Throwable, X2 extends Throwable, X3 extends Throwable> {
    R apply(@Nullable T1 t1, @Nullable T2 t2) throws X1, X2, X3;
}
