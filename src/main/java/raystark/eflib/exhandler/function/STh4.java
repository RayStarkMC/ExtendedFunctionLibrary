package raystark.eflib.exhandler.function;

@FunctionalInterface
public interface STh4<T, X1 extends Exception, X2 extends Exception, X3 extends Exception, X4 extends Exception> {
    T get() throws X1, X2, X3, X4;
}
