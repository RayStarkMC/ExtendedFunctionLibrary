package raystark.eflib.exhandler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.exhandler.function.STh3;
import raystark.eflib.function.A;
import raystark.eflib.function.F1;
import raystark.eflib.function.S;

public interface Try3<T, X1 extends Throwable, X2 extends Throwable, X3 extends Throwable> {
    @Nullable
    T rawGet() throws X1, X2, X3;

    @NotNull
    Try2<T, X2, X3> recover1(
        @NotNull F1<? super X1, ? extends T> handlerX1,
        @NotNull A handlerFinally
    );

    @NotNull
    Try1<T, X3> recover2(
        @NotNull F1<? super X1, ? extends T> handlerX1,
        @NotNull F1<? super X2, ? extends T> handlerX2,
        @NotNull A handlerFinally
    );

    @NotNull
    S<T> recover3(
        @NotNull F1<? super X1, ? extends T> handlerX1,
        @NotNull F1<? super X2, ? extends T> handlerX2,
        @NotNull F1<? super X3, ? extends T> handlerX3,
        @NotNull A handlerFinally
    );


    @NotNull
    Try3<T, X2, X1, X3> swap2();

    @NotNull
    Try3<T, X3, X2, X1> swap3();

    @NotNull
    static <X1 extends Throwable, X2 extends Throwable, X3 extends Throwable> Builder<X1, X2, X3> builder(
        @NotNull Class<X1> classX1,
        @NotNull Class<X2> classX2,
        @NotNull Class<X3> classX3
    ) {
        return new Builder<>() {
            @Override
            @NotNull
            public <T> Try3<T, X1, X2, X3> build(@NotNull STh3<T, X1, X2, X3> s) {
                return new Try3Impl<>(s, classX1, classX2, classX3);
            }
        };
    }

    interface Builder<X1 extends Throwable, X2 extends Throwable, X3 extends Throwable> {
        @NotNull
        <T> Try3<T, X1, X2, X3> build(@NotNull STh3<T, X1, X2, X3> s);
    }
}
