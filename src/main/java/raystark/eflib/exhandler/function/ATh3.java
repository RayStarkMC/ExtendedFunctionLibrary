package raystark.eflib.exhandler.function;

@FunctionalInterface
public interface ATh3<X1 extends Throwable, X2 extends Throwable, X3 extends Throwable> {
    void run() throws X1, X2, X3;
}
