package raystark.eflib.exhandler;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.exhandler.function.ATh3;
import raystark.eflib.function.A;
import raystark.eflib.function.notnull.NC1;

public interface VTry3<X1 extends Throwable, X2 extends Throwable, X3 extends Throwable> {
    void rawRun() throws X1, X2, X3;

    @NotNull
    VTry2<X2, X3> recover1(
        @NotNull NC1<? super X1> handlerX1,
        @NotNull A handlerFinally
    );

    @NotNull
    VTry1<X3> recover2(
        @NotNull NC1<? super X1> handlerX1,
        @NotNull NC1<? super X2> handlerX2,
        @NotNull A handlerFinally
    );

    @NotNull
    A recover3(
        @NotNull NC1<? super X1> handlerX1,
        @NotNull NC1<? super X2> handlerX2,
        @NotNull NC1<? super X3> handlerX3,
        @NotNull A handlerFinally
    );

    @NotNull
    VTry3<X2, X1, X3> swap2();

    @NotNull
    VTry3<X3, X2, X1> swap3();

    @NotNull
    static <X1 extends Throwable, X2 extends Throwable, X3 extends Throwable> Builder<X1, X2, X3> builder(
        @NotNull Class<X1> classX1,
        @NotNull Class<X2> classX2,
        @NotNull Class<X3> classX3
    ) {
        return a -> new VTry3Impl<>( classX1, classX2, classX3, a);
    }

    @FunctionalInterface
    interface Builder<X1 extends Throwable, X2 extends Throwable, X3 extends Throwable> {
        @NotNull
        VTry3<X1, X2, X3> build(@NotNull ATh3<X1, X2, X3> s);
    }
}
