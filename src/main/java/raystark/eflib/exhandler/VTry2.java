package raystark.eflib.exhandler;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.exhandler.function.ATh2;
import raystark.eflib.function.A;
import raystark.eflib.function.notnull.NC1;

public interface VTry2<X1 extends Throwable, X2 extends Throwable> {
    void rawRun() throws X1, X2;

    @NotNull
    VTry1<X2> recover1(
        @NotNull NC1<? super X1> handlerX1,
        @NotNull A handlerFinally
    );

    @NotNull
    A recover2(
        @NotNull NC1<? super X1> handlerX1,
        @NotNull NC1<? super X2> handlerX2,
        @NotNull A handlerFinally
    );

    @NotNull
    VTry2<X2, X1> swap2();

    @NotNull
    static <X1 extends Throwable, X2 extends Throwable> Builder<X1, X2> builder(
        @NotNull Class<X1> classX1,
        @NotNull Class<X2> classX2
    ) {
        return a -> new VTry2Impl<>( classX1, classX2, a);
    }

    @FunctionalInterface
    interface Builder<X1 extends Throwable, X2 extends Throwable> {
        @NotNull
        VTry2<X1, X2> build(@NotNull ATh2<X1, X2> s);
    }
}
