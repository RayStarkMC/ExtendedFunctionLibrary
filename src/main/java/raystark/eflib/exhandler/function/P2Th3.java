package raystark.eflib.exhandler.function;

import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface P2Th3<T1, T2, X1 extends Throwable, X2 extends Throwable, X3 extends Throwable> {
    boolean test(@Nullable T1 t1, @Nullable T2 t2) throws X1, X2, X3;
}
