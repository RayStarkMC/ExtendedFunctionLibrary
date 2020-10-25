package raystark.eflib.exhandler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.exhandler.function.STh2;
import raystark.eflib.exhandler.function.STh3;
import raystark.eflib.function.A;
import raystark.eflib.function.F1;
import raystark.eflib.function.S;

class Try3Impl<T, X1 extends Throwable, X2 extends Throwable, X3 extends Throwable> implements Try3<T, X1, X2, X3> {
    private final STh3<T, X1, X2, X3> s;
    private final Class<X1> classX1;
    private final Class<X2> classX2;
    private final Class<X3> classX3;

    Try3Impl(@NotNull STh3<T, X1, X2, X3> s, @NotNull Class<X1> classX1, @NotNull Class<X2> classX2, @NotNull Class<X3> classX3) {
        this.s = s;
        this.classX1 = classX1;
        this.classX2 = classX2;
        this.classX3 = classX3;
    }

    @Override
    @Nullable
    public  T rawGet() throws X1, X2, X3 {
        return s.get();
    }

    @Override
    @NotNull
    @SuppressWarnings("unchecked")
    public Try2<T, X2, X3> recover1(
        @NotNull F1<? super X1, ? extends T> handlerX1,
        @NotNull A handlerFinally
    ) {
        return Try2.builder(classX2, classX3).build(() -> {
            try {
                return rawGet();
            } catch (Throwable x) {
                if(classX1.isInstance(x)) return handlerX1.apply((X1)x);
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
    public Try1<T, X3> recover2(
        @NotNull F1<? super X1, ? extends T> handlerX1,
        @NotNull F1<? super X2, ? extends T> handlerX2,
        @NotNull A handlerFinally
    ) {
        return Try1.builder(classX3).build(() -> {
            try {
                return rawGet();
            } catch (Throwable x) {
                if(classX1.isInstance(x)) return handlerX1.apply((X1)x);
                if(classX2.isInstance(x)) return handlerX2.apply((X2)x);
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
    public S<T> recover3(
        @NotNull F1<? super X1, ? extends T> handlerX1,
        @NotNull F1<? super X2, ? extends T> handlerX2,
        @NotNull F1<? super X3, ? extends T> handlerX3,
        @NotNull A handlerFinally
    ) {
        return S.of(() -> {
            try {
                return rawGet();
            } catch (Throwable x) {
                if(classX1.isInstance(x)) return handlerX1.apply((X1)x);
                if(classX2.isInstance(x)) return handlerX2.apply((X2)x);
                if(classX3.isInstance(x)) return handlerX3.apply((X3)x);
                throw new AssertionError("All throwable must be handled.", x);
            } finally {
                handlerFinally.run();
            }
        });
    }

    @Override
    @NotNull
    public Try3<T, X2, X1, X3> swap2() {
        //例外型の宣言順は順不同なためキャストは安全
        //noinspection unchecked
        return new Try3Impl<>((STh3<T, X2, X1, X3>)s, classX2, classX1, classX3);
    }

    @Override
    @NotNull
    public Try3<T, X3, X2, X1> swap3() {
        //例外型の宣言順は順不同なためキャストは安全
        //noinspection unchecked
        return new Try3Impl<>((STh3<T, X3, X2, X1>)s, classX3, classX2, classX1);
    }
}
