package raystark.eflib.exhandler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.exhandler.function.ATh4;
import raystark.eflib.exhandler.function.STh4;
import raystark.eflib.function.A;
import raystark.eflib.function.F1;
import raystark.eflib.function.S;
import raystark.eflib.function.notnull.NC1;

class VTry4Impl<X1 extends Throwable, X2 extends Throwable, X3 extends Throwable, X4 extends Throwable> implements VTry4<X1, X2, X3, X4> {
    private final Class<X1> classX1;
    private final Class<X2> classX2;
    private final Class<X3> classX3;
    private final Class<X4> classX4;
    private final ATh4<X1, X2, X3, X4> a;

    VTry4Impl(@NotNull Class<X1> classX1, @NotNull Class<X2> classX2, @NotNull Class<X3> classX3, @NotNull Class<X4> classX4, @NotNull ATh4<X1, X2, X3, X4> a) {
        this.classX1 = classX1;
        this.classX2 = classX2;
        this.classX3 = classX3;
        this.classX4 = classX4;
        this.a = a;
    }

    @Override
    public void rawRun() throws X1, X2, X3, X4 {
        a.run();
    }

    @Override
    @NotNull
    @SuppressWarnings("unchecked")
    public VTry3<X2, X3, X4> recover1(
        @NotNull NC1<? super X1> handlerX1,
        @NotNull A handlerFinally
    ) {
        return VTry3.builder(classX2, classX3, classX4).build(() -> {
            try {
                rawRun();
            } catch (Throwable x) {
                if(classX1.isInstance(x)) handlerX1.accept((X1)x);
                if(classX2.isInstance(x)) throw (X2)x;
                if(classX3.isInstance(x)) throw (X3)x;
                if(classX4.isInstance(x)) throw (X4)x;
                throw new AssertionError("All throwable must be handled.", x);
            } finally {
                handlerFinally.run();
            }
        });
    }

    @Override
    @NotNull
    @SuppressWarnings("unchecked")
    public VTry2<X3, X4> recover2(
        @NotNull NC1<? super X1> handlerX1,
        @NotNull NC1<? super X2> handlerX2,
        @NotNull A handlerFinally
    ) {
        return VTry2.builder(classX3, classX4).build(() -> {
            try {
                rawRun();
            } catch (Throwable x) {
                if(classX1.isInstance(x)) handlerX1.accept((X1)x);
                if(classX2.isInstance(x)) handlerX2.accept((X2)x);
                if(classX3.isInstance(x)) throw (X3)x;
                if(classX4.isInstance(x)) throw (X4)x;
                throw new AssertionError("All throwable must be handled.", x);
            } finally {
                handlerFinally.run();
            }
        });
    }

    @Override
    @NotNull
    @SuppressWarnings("unchecked")
    public VTry1<X4> recover3(
        @NotNull NC1<? super X1> handlerX1,
        @NotNull NC1<? super X2> handlerX2,
        @NotNull NC1<? super X3> handlerX3,
        @NotNull A handlerFinally
    ) {
        return VTry1.builder(classX4).build(() -> {
            try {
                 rawRun();
            } catch (Throwable x) {
                if(classX1.isInstance(x)) handlerX1.accept((X1)x);
                if(classX2.isInstance(x)) handlerX2.accept((X2)x);
                if(classX3.isInstance(x)) handlerX3.accept((X3)x);
                if(classX4.isInstance(x)) throw (X4)x;
                throw new AssertionError("All throwable must be handled.", x);
            } finally {
                handlerFinally.run();
            }
        });
    }

    @Override
    @NotNull
    @SuppressWarnings("unchecked")
    public A recover4(
        @NotNull NC1<? super X1> handlerX1,
        @NotNull NC1<? super X2> handlerX2,
        @NotNull NC1<? super X3> handlerX3,
        @NotNull NC1<? super X4> handlerX4,
        @NotNull A handlerFinally
    ) {
        return A.of(() -> {
            try {
                rawRun();
            } catch (Throwable x) {
                if(classX1.isInstance(x)) handlerX1.accept((X1)x);
                if(classX2.isInstance(x)) handlerX2.accept((X2)x);
                if(classX3.isInstance(x)) handlerX3.accept((X3)x);
                if(classX4.isInstance(x)) handlerX4.accept((X4)x);
                throw new AssertionError("All throwable must be handled.", x);
            } finally {
                handlerFinally.run();
            }
        });
    }

    @Override
    @NotNull
    @SuppressWarnings("unchecked")
    public VTry4<X2, X1, X3, X4> swap2() {
        //例外型の宣言順は順不同なためキャストは安全
        return new VTry4Impl<>(classX2, classX1, classX3, classX4, (ATh4<X2, X1, X3, X4>) a);
    }

    @Override
    @NotNull
    @SuppressWarnings("unchecked")
    public VTry4<X3, X2, X1, X4> swap3() {
        //例外型の宣言順は順不同なためキャストは安全
        return new VTry4Impl<>(classX3, classX2, classX1, classX4, (ATh4<X3, X2, X1, X4>) a);
    }

    @Override
    @NotNull
    @SuppressWarnings("unchecked")
    public VTry4<X4, X2, X3, X1> swap4() {
        //例外型の宣言順は順不同なためキャストは安全
        return new VTry4Impl<>(classX4, classX2, classX3, classX1, (ATh4<X4, X2, X3, X1>) a);
    }
}
