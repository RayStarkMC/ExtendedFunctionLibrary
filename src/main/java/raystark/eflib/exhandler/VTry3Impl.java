package raystark.eflib.exhandler;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.exhandler.function.ATh3;
import raystark.eflib.function.A;
import raystark.eflib.function.notnull.NC1;

class VTry3Impl<X1 extends Throwable, X2 extends Throwable, X3 extends Throwable> implements VTry3<X1, X2, X3> {
    private final Class<X1> classX1;
    private final Class<X2> classX2;
    private final Class<X3> classX3;
    private final ATh3<X1, X2, X3> a;

    VTry3Impl(@NotNull Class<X1> classX1, @NotNull Class<X2> classX2, @NotNull Class<X3> classX3, @NotNull ATh3<X1, X2, X3> a) {
        this.classX1 = classX1;
        this.classX2 = classX2;
        this.classX3 = classX3;
        this.a = a;
    }

    @Override
    public void rawRun() throws X1, X2, X3 {
        a.run();
    }

    @Override
    @NotNull
    @SuppressWarnings("unchecked")
    public VTry2<X2, X3> recover1(
        @NotNull NC1<? super X1> handlerX1,
        @NotNull A handlerFinally
    ) {
        return VTry2.builder(classX2, classX3).build(() -> {
            try {
                rawRun();
            } catch (Throwable x) {
                if(classX1.isInstance(x)) handlerX1.accept((X1)x);
                if(classX2.isInstance(x)) throw (X2)x;
                if(classX3.isInstance(x)) throw (X3)x;
                throw new AssertionError("All throwable must be handled.", x);
            } finally {
                handlerFinally.run();
            }
        });
    }

    @Override
    @NotNull
    @SuppressWarnings("unchecked")
    public VTry1<X3> recover2(
        @NotNull NC1<? super X1> handlerX1,
        @NotNull NC1<? super X2> handlerX2,
        @NotNull A handlerFinally
    ) {
        return VTry1.builder(classX3).build(() -> {
            try {
                rawRun();
            } catch (Throwable x) {
                if(classX1.isInstance(x)) handlerX1.accept((X1)x);
                if(classX2.isInstance(x)) handlerX2.accept((X2)x);
                if(classX3.isInstance(x)) throw (X3)x;
                throw new AssertionError("All throwable must be handled.", x);
            } finally {
                handlerFinally.run();
            }
        });
    }

    @Override
    @NotNull
    @SuppressWarnings("unchecked")
    public A recover3(
        @NotNull NC1<? super X1> handlerX1,
        @NotNull NC1<? super X2> handlerX2,
        @NotNull NC1<? super X3> handlerX3,
        @NotNull A handlerFinally
    ) {
        return A.of(() -> {
            try {
                rawRun();
            } catch (Throwable x) {
                if(classX1.isInstance(x)) handlerX1.accept((X1)x);
                if(classX2.isInstance(x)) handlerX2.accept((X2)x);
                if(classX3.isInstance(x)) handlerX3.accept((X3)x);
                throw new AssertionError("All throwable must be handled.", x);
            } finally {
                handlerFinally.run();
            }
        });
    }

    @Override
    @NotNull
    @SuppressWarnings("unchecked")
    public VTry3<X2, X1, X3> swap2() {
        //例外型の宣言順は順不同なためキャストは安全
        return new VTry3Impl<>(classX2, classX1, classX3, (ATh3<X2, X1, X3>)a);
    }

    @Override
    @NotNull
    @SuppressWarnings("unchecked")
    public VTry3<X3, X2, X1> swap3() {
        return new VTry3Impl<>(classX3, classX2, classX1, (ATh3<X3, X2, X1>)a);
    }
}
