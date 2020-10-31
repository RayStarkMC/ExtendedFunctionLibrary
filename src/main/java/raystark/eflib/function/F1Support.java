package raystark.eflib.function;

class F1Support {
    private static final F1<?, ?> IDENTITY = t -> t;

    @SuppressWarnings("unchecked")
    static <T> F1<T, T> identity() {
        return (F1<T, T>) IDENTITY;
    }
}
