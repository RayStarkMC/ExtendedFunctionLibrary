package raystark.eflib.exhandler;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.exhandler.function.ATh1;
import raystark.eflib.function.A;
import raystark.eflib.function.notnull.NC1;

public interface VTry1<X1 extends Throwable> {
    void rawRun() throws X1;

    @NotNull
    A recover1(
        @NotNull NC1<? super X1> handlerX1,
        @NotNull A handlerFinally
    );

    @NotNull
    static <X1 extends Throwable> Builder<X1> builder(@NotNull Class<X1> classX1) {
        return a -> new VTry1Impl<>(classX1, a);
    }

    interface Builder<X1 extends Throwable> {
        @NotNull
        VTry1<X1> build(@NotNull ATh1<X1> a);
    }
}
