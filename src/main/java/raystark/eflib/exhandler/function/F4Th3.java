package raystark.eflib.exhandler.function;

import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface F4Th3<T1, T2, T3, T4, R, X1 extends Throwable, X2 extends Throwable, X3 extends Throwable> {
    R apply(@Nullable T1 t1, @Nullable T2 t2, @Nullable T3 t3, @Nullable T4 t4) throws X1, X2, X3;
}
