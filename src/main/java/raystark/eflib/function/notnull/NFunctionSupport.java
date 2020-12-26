package raystark.eflib.function.notnull;

final class NFunctionSupport {
    private static final NF1<?, ?> IDENTITY = t -> t;
    private static final NC1<?> NOTHING_C1 = t1 -> {};
    private static final NC2<?, ?> NOTHING_C2 = (t1, t2) -> {};
    private static final NC3<?, ?, ?> NOTHING_C3 = (t1, t2, t3) -> {};
    private static final NC4<?, ?, ?, ?> NOTHING_C4 = (t1, t2, t3, t4) -> {};

    private NFunctionSupport() {}

    @SuppressWarnings("unchecked") static <T> NF1<T, T> identity() { return (NF1<T, T>) IDENTITY; }
    @SuppressWarnings("unchecked") static <T1> NC1<T1> doNothingC1() { return (NC1<T1>) NOTHING_C1; }
    @SuppressWarnings("unchecked") static <T1, T2> NC2<T1, T2> doNothingC2() { return (NC2<T1, T2>) NOTHING_C2; }
    @SuppressWarnings("unchecked") static <T1, T2, T3> NC3<T1, T2, T3> doNothingC3() { return (NC3<T1, T2, T3>) NOTHING_C3; }
    @SuppressWarnings("unchecked") static <T1, T2, T3, T4> NC4<T1, T2, T3, T4> doNothingC4() { return (NC4<T1, T2, T3, T4>) NOTHING_C4; }
}
