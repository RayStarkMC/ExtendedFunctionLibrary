package raystark.eflib.function.notnull;

final class NFunctionSupport {
    private static final NF1<?, ?> IDENTITY = t -> t;

    private NFunctionSupport() {}

    @SuppressWarnings("unchecked")
    static <T> NF1<T, T> identity() {
        return (NF1<T, T>) IDENTITY;
    }
}
