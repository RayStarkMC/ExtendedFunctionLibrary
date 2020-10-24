package raystark.eflib.exhandler;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.exhandler.function.STh1;
import raystark.eflib.function.A;
import raystark.eflib.function.F1;
import raystark.eflib.function.S;

class Try1Impl<T, X1 extends Throwable> implements Try1<T, X1> {
    private final STh1<T, X1> s;
    private final Class<X1> classX1;

    Try1Impl(@NotNull STh1<T, X1> s, @NotNull Class<X1> classX1) {
        this.s = s;
        this.classX1 = classX1;
    }

    @Override
    public T rawGet() throws X1 {
        return s.get();
    }

    @Override
    @NotNull
    public S<T> recover1(
        @NotNull F1<? super X1, ? extends T> handlerX1,
        @NotNull A handlerFinally
    ) {
        return S.of(() -> {
            try {
                return rawGet();
            } catch (Throwable x) {
                return handlerX1.apply(classX1.cast(x));
            } finally {
                handlerFinally.run();
            }
        });
    }
}
