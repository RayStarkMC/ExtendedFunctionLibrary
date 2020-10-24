package raystark.eflib.exhandler.function;

import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface C1Th3<T1, X1 extends Throwable, X2 extends Throwable, X3 extends Throwable> {
    void accept(@Nullable T1 t1) throws X1, X2, X3;
}
