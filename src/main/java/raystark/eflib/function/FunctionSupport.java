package raystark.eflib.function;

final class FunctionSupport {
    private static final F1<?, ?> IDENTITY = t -> t;
    private static final A DO_NOTHING = () -> {};

    private FunctionSupport() {}

    @SuppressWarnings("unchecked")
    static <T> F1<T, T> identity() {
        return (F1<T, T>) IDENTITY;
    }

    static A doNothing() {return DO_NOTHING;}
}
