package raystark.eflib.function;

class FunctionSupport {
    private static final F1<?, ?> IDENTITY = t -> t;
    private static final A DO_NOTHING = () -> {};

    @SuppressWarnings("unchecked")
    static <T> F1<T, T> identity() {
        return (F1<T, T>) IDENTITY;
    }

    static A doNothing() {return DO_NOTHING;}
}
