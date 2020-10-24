package raystark.eflib.exhandler.function;

import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface P2Th1<T1, T2, X1 extends Throwable> {
    boolean test(@Nullable T1 t1, @Nullable T2 t2) throws X1;
}
