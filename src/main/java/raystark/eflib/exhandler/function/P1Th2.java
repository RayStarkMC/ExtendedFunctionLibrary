package raystark.eflib.exhandler.function;

import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface P1Th2<T1, X1 extends Throwable, X2 extends Throwable> {
    boolean test(@Nullable T1 t1) throws X1, X2;
}
