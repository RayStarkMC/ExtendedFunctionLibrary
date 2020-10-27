package raystark.eflib.exhandler;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.exhandler.function.ATh2;
import raystark.eflib.function.A;
import raystark.eflib.function.notnull.NC1;

class VTry2Impl<X1 extends Throwable, X2 extends Throwable> implements VTry2<X1, X2> {
    private final Class<X1> classX1;
    private final Class<X2> classX2;
    private final ATh2<X1, X2> a;

    VTry2Impl(@NotNull Class<X1> classX1, @NotNull Class<X2> classX2, @NotNull ATh2<X1, X2> a) {
        this.classX1 = classX1;
        this.classX2 = classX2;
        this.a = a;
    }

    @Override
    public void rawRun() throws X1, X2 {
        a.run();
    }

    @Override
    @NotNull
    @SuppressWarnings("unchecked")
    public VTry1<X2> recover1(
        @NotNull NC1<? super X1> handlerX1,
        @NotNull A handlerFinally
    ) {
        return VTry1.builder(classX2).build(() -> {
            try {
                rawRun();
            } catch (Throwable x) {
                if(classX1.isInstance(x)) handlerX1.accept((X1)x);
                if(classX2.isInstance(x)) throw (X2)x;
                throw new AssertionError("All throwable must be handled.", x);
            } finally {
                handlerFinally.run();
            }
        });
    }


    @Override
    @NotNull
    @SuppressWarnings("unchecked")
    public A recover2(
        @NotNull NC1<? super X1> handlerX1,
        @NotNull NC1<? super X2> handlerX2,
        @NotNull A handlerFinally)
    {
        return A.of(() -> {
            try {
                rawRun();
            } catch (Throwable x) {
                if(classX1.isInstance(x)) handlerX1.accept((X1)x);
                if(classX2.isInstance(x)) handlerX2.accept((X2)x);
                throw new AssertionError("All throwable must be handled.", x);
            } finally {
                handlerFinally.run();
            }
        });
    }

    @Override
    @NotNull
    @SuppressWarnings("unchecked")
    public VTry2<X2, X1> swap2() {
        //例外型の宣言順は順不同なためキャストは安全
        return new VTry2Impl<>(classX2, classX1, (ATh2<X2, X1>)a);
    }
}
