package raystark.eflib.exhandler;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.exhandler.function.ATh1;
import raystark.eflib.function.A;
import raystark.eflib.function.notnull.NC1;

class VTry1Impl<X1 extends Throwable> implements VTry1<X1> {
    private final Class<X1> classX1;
    private final ATh1<X1> a;

    VTry1Impl(@NotNull Class<X1> classX1, @NotNull ATh1<X1> a) {
        this.classX1 = classX1;
        this.a = a;
    }

    @Override
    public void rawRun() throws X1 {
        a.run();
    }


    @Override
    @NotNull
    @SuppressWarnings("unchecked")
    public A recover1(
        @NotNull NC1<? super X1> handlerX1,
        @NotNull A handlerFinally
    ) {
        return A.of(() -> {
            try {
                rawRun();
            } catch (Throwable x) {
                if(classX1.isInstance(x)) handlerX1.accept((X1)x);
                throw new AssertionError("All throwable must be handled.", x);
            } finally {
                handlerFinally.run();
            }
        });
    }
}
