package raystark.eflib.exhandler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.exhandler.function.STh1;
import raystark.eflib.function.A;
import raystark.eflib.function.F1;
import raystark.eflib.function.S;

public interface Try1<T, X1 extends Throwable> {
    @Nullable
    T rawGet() throws X1;

    @NotNull
    S<T> recover1(
        @NotNull F1<? super X1, ? extends T> handlerX1,
        @NotNull A handlerFinally
    );

    @NotNull
    static <X1 extends Throwable> Builder<X1> builder(@NotNull Class<X1> classX1) {
        return new Builder<>() {
            @Override
            @NotNull
            public <T> Try1<T, X1> build(@NotNull STh1<T, X1> s) {
                return new Try1Impl<>(classX1, s);
            }
        };
    }

    interface Builder<X1 extends Throwable> {
        @NotNull
        <T> Try1<T, X1> build(@NotNull STh1<T, X1> s);
    }
}
