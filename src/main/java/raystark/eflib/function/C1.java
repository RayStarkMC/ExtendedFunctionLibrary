package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface C1<T1> {
    void accept(T1 t1);

    @NotNull
    default C1<T1> next(@NotNull C1<? super T1> after) {
        return t1 -> {
            accept(t1);
            after.accept(t1);
        };
    }

    @NotNull
    default <V1> C1<V1> compose1(@NotNull F1<? super V1, ? extends T1> before) {
        return v1 -> accept(before.apply(v1));
    }

    @NotNull
    default A asA(T1 t1) {
        return () -> accept(t1);
    }

    @NotNull
    static <T1> C1<T1> of(@NotNull C1<T1> c1) {
        return c1;
    }
}
