package raystark.eflib.exhandler.function;

import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface C2Th2<T1, T2, X1 extends Throwable, X2 extends Throwable> {
    void accept(@Nullable T1 t1, @Nullable T2 t2) throws X1, X2;
}
