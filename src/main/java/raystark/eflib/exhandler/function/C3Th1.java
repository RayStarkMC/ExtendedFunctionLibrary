package raystark.eflib.exhandler.function;

import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface C3Th1<T1, T2, T3, X1 extends Throwable> {
    void accept(@Nullable T1 t1, @Nullable T2 t2, @Nullable T3 t3) throws X1;
}
