package raystark.eflib.exhandler.function;

import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface C2Th1<T1, T2, X1 extends Throwable> {
    void accept(@Nullable T1 t1, @Nullable T2 t2) throws X1;
}
