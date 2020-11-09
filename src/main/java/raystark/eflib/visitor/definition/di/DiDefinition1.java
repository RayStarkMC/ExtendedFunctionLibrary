package raystark.eflib.visitor.definition.di;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.function.notnull.NF2;
import raystark.eflib.visitor.acceptor.Acceptor1;
import raystark.eflib.visitor.type.Type1;

public interface DiDefinition1<T extends Acceptor1<T, T1>, T1 extends T, R> {
    @NotNull R dispatch(@NotNull Type1<T1> arg1, @NotNull Type1<T1> arg2);

    static <T extends Acceptor1<T, T1>, T1 extends T, R>
    @NotNull BuilderT11<T, T1, R> builder() {
        //noinspection Convert2Lambda
        return f11 -> new DiDefinition1<>() {
            @Override
            public @NotNull R dispatch(@NotNull Type1<T1> arg1, @NotNull Type1<T1> arg2) {
                return f11.apply(arg1.unwrap(), arg2.unwrap());
            }
        };
    }

    static <T extends Acceptor1<T, T1>, T1 extends T, R>
    @NotNull DiDefinition1<T, T1, R> build(@NotNull NF1<BuilderT11<T, T1, R>, DiDefinition1<T, T1, R>> builder) {
        return builder.apply(builder());
    }

    @FunctionalInterface
    interface BuilderT11<T extends Acceptor1<T, T1>, T1 extends T, R> {
        @NotNull DiDefinition1<T, T1, R> type11(@NotNull NF2<T1, T1, R> f11);
    }
}
