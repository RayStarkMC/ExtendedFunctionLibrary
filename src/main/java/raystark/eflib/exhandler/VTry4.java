package raystark.eflib.exhandler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.exhandler.function.ATh4;
import raystark.eflib.exhandler.function.STh4;
import raystark.eflib.function.A;
import raystark.eflib.function.F1;
import raystark.eflib.function.S;
import raystark.eflib.function.notnull.NC1;

public interface VTry4<X1 extends Throwable, X2 extends Throwable, X3 extends Throwable, X4 extends Throwable> {
    void rawRun() throws X1, X2, X3, X4;

    @NotNull
    VTry3<X2, X3, X4> recover1(
        @NotNull NC1<? super X1> handlerX1,
        @NotNull A handlerFinally
    );

    @NotNull
    VTry2<X3, X4> recover2(
        @NotNull NC1<? super X1> handlerX1,
        @NotNull NC1<? super X2> handlerX2,
        @NotNull A handlerFinally
    );

    @NotNull
    VTry1<X4> recover3(
        @NotNull NC1<? super X1> handlerX1,
        @NotNull NC1<? super X2> handlerX2,
        @NotNull NC1<? super X3> handlerX3,
        @NotNull A handlerFinally
    );

    @NotNull
    A recover4(
        @NotNull NC1<? super X1> handlerX1,
        @NotNull NC1<? super X2> handlerX2,
        @NotNull NC1<? super X3> handlerX3,
        @NotNull NC1<? super X4> handlerX4,
        @NotNull A handlerFinally
    );

    @NotNull
    VTry4<X2, X1, X3, X4> swap2();

    @NotNull
    VTry4<X3, X2, X1, X4> swap3();

    @NotNull
    VTry4<X4, X2, X3, X1> swap4();

    @NotNull
    static <X1 extends Throwable, X2 extends Throwable, X3 extends Throwable, X4 extends Throwable> Builder<X1, X2, X3, X4> builder(
        @NotNull Class<X1> classX1,
        @NotNull Class<X2> classX2,
        @NotNull Class<X3> classX3,
        @NotNull Class<X4> classX4
    ) {
        return s -> new VTry4Impl<>(classX1, classX2, classX3, classX4, s);
    }

    interface Builder<X1 extends Throwable, X2 extends Throwable, X3 extends Throwable, X4 extends Throwable> {
        VTry4<X1, X2, X3, X4> build(@NotNull ATh4<X1, X2, X3, X4> s);
    }
}
