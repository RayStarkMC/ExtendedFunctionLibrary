package raystark.eflib.exhandler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.exhandler.function.STh2;
import raystark.eflib.function.A;
import raystark.eflib.function.F1;
import raystark.eflib.function.S;

public interface Try2<T, X1 extends Throwable, X2 extends Throwable> {
    @Nullable
    T rawGet() throws X1, X2;

    @NotNull
    Try1<T, X2> recover1(
        @NotNull F1<? super X1, ? extends T> handlerX1,
        @NotNull A handlerFinally
    );

    @NotNull
    S<T> recover2(
        @NotNull F1<? super X1, ? extends T> handlerX1,
        @NotNull F1<? super X2, ? extends T> handlerX2,
        @NotNull A handlerFinally
    );

    @NotNull
    Try2<T, X2, X1> swap2();

    @NotNull
    static <X1 extends Throwable, X2 extends Throwable> Builder<X1, X2> builder(
        @NotNull Class<X1> classX1,
        @NotNull Class<X2> classX2
    ) {
        return new Builder<>() {
            @Override
            @NotNull
            public <T> Try2<T, X1, X2> build(@NotNull STh2<T, X1, X2> s) {
                return new Try2Impl<>( classX1, classX2, s);
            }
        };
    }

    interface Builder<X1 extends Throwable, X2 extends Throwable> {
        @NotNull
        <T> Try2<T, X1, X2> build(@NotNull STh2<T, X1, X2> s);
    }
}
