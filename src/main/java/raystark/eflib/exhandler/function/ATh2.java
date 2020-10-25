package raystark.eflib.exhandler.function;

@FunctionalInterface
public interface ATh2<X1 extends Throwable, X2 extends Throwable> {
    void run() throws X1, X2;
}
