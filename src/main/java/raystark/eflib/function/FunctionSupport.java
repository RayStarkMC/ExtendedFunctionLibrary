package raystark.eflib.function;

final class FunctionSupport {
    private static final A NOTHING_A = () -> {};
    private static final C1<?> NOTHING_C1 = t1 -> {};
    private static final C2<?, ?> NOTHING_C2 = (t1, t2) -> {};
    private static final C3<?, ?, ?> NOTHING_C3 = (t1, t2, t3) -> {};
    private static final C4<?, ?, ?, ?> NOTHING_C4 = (t1, t2, t3, t4) -> {};
    private static final F1<?, ?> IDENTITY = t1 -> t1;

    private FunctionSupport() {}

    @SuppressWarnings("unchecked") static <T> F1<T, T> identity() { return (F1<T, T>) IDENTITY; }
    static A doNothing() {return NOTHING_A;}
    @SuppressWarnings("unchecked") static <T1> C1<T1> doNothingC1() { return (C1<T1>) NOTHING_C1; }
    @SuppressWarnings("unchecked") static <T1, T2> C2<T1, T2> doNothingC2() { return (C2<T1, T2>) NOTHING_C2; }
    @SuppressWarnings("unchecked") static <T1, T2, T3> C3<T1, T2, T3> doNothingC3() { return (C3<T1, T2, T3>) NOTHING_C3; }
    @SuppressWarnings("unchecked") static <T1, T2, T3, T4> C4<T1, T2, T3, T4> doNothingC4() { return (C4<T1, T2, T3, T4>) NOTHING_C4; }
}
