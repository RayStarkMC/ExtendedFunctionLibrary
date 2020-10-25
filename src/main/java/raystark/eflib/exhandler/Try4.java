package raystark.eflib.exhandler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.exhandler.function.STh4;
import raystark.eflib.function.A;
import raystark.eflib.function.F1;
import raystark.eflib.function.S;

public interface Try4<T, X1 extends Throwable, X2 extends Throwable, X3 extends Throwable, X4 extends Throwable> {
    @Nullable
    T rawGet() throws X1, X2, X3, X4;

    @NotNull
    Try3<T, X2, X3, X4> recover1(
        @NotNull F1<? super X1, ? extends T> handlerX1,
        @NotNull A handlerFinally
    );

    @NotNull
    Try2<T, X3, X4> recover2(
        @NotNull F1<? super X1, ? extends T> handlerX1,
        @NotNull F1<? super X2, ? extends T> handlerX2,
        @NotNull A handlerFinally
    );

    @NotNull
    Try1<T, X4> recover3(
        @NotNull F1<? super X1, ? extends T> handlerX1,
        @NotNull F1<? super X2, ? extends T> handlerX2,
        @NotNull F1<? super X3, ? extends T> handlerX3,
        @NotNull A handlerFinally
    );

    @NotNull
    S<T> recover4(
        @NotNull F1<? super X1, ? extends T> handlerX1,
        @NotNull F1<? super X2, ? extends T> handlerX2,
        @NotNull F1<? super X3, ? extends T> handlerX3,
        @NotNull F1<? super X4, ? extends T> handlerX4,
        @NotNull A handlerFinally
    );

    @NotNull
    Try4<T, X2, X1, X3, X4> swap2();

    @NotNull
    Try4<T, X3, X2, X1, X4> swap3();

    @NotNull
    Try4<T, X4, X2, X3, X1> swap4();

    @NotNull
    static <X1 extends Throwable, X2 extends Throwable, X3 extends Throwable, X4 extends Throwable> Builder<X1, X2, X3, X4> builder(
        @NotNull Class<X1> classX1,
        @NotNull Class<X2> classX2,
        @NotNull Class<X3> classX3,
        @NotNull Class<X4> classX4
    ) {
        return new Builder<>() {
            @Override
            @NotNull
            public <T> Try4<T, X1, X2, X3, X4> build(@NotNull STh4<T, X1, X2, X3, X4> s) {
                return new Try4Impl<>(classX1, classX2, classX3, classX4, s);
            }
        };
    }

    interface Builder<X1 extends Throwable, X2 extends Throwable, X3 extends Throwable, X4 extends Throwable> {
        @NotNull
        <T> Try4<T, X1, X2, X3, X4> build(@NotNull STh4<T, X1, X2, X3, X4> s);
    }
}
