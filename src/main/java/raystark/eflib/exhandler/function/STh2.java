package raystark.eflib.exhandler.function;

public interface STh2<T, X1 extends Throwable, X2 extends Throwable> {
    T get() throws X1, X2;
}