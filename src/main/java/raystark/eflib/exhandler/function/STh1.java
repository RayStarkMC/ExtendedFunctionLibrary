package raystark.eflib.exhandler.function;

@FunctionalInterface
public interface STh1<T, X1 extends Throwable> {
    T get() throws X1;
}
