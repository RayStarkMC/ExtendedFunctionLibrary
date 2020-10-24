package raystark.eflib.exhandler.function;

import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface C1Th1<T1, X1 extends Throwable> {
    void accept(@Nullable T1 t1) throws X1;
}
