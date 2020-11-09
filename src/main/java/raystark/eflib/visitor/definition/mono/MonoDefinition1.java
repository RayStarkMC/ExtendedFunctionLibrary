package raystark.eflib.visitor.definition.mono;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.visitor.acceptor.Acceptor1;
import raystark.eflib.visitor.type.Type1;

public interface MonoDefinition1<T extends Acceptor1<T, T1>, T1 extends T, R> {
    @NotNull R dispatch(@NotNull Type1<T1> arg1);

    static <T extends Acceptor1<T, T1>, T1 extends T, R>
    @NotNull BuilderT1<T, T1, R> builder() {
        //noinspection Convert2Lambda
        return f1 -> new MonoDefinition1<>() {
            @Override
            public @NotNull R dispatch(@NotNull Type1<T1> arg1) {
                return f1.apply(arg1.unwrap());
            }
        };
    }

    static <T extends Acceptor1<T, T1>, T1 extends T, R>
    @NotNull MonoDefinition1<T, T1, R> build(@NotNull NF1<BuilderT1<T, T1, R>, MonoDefinition1<T, T1, R>> builder) {
        return builder.apply(builder());
    }

    @FunctionalInterface
    interface BuilderT1<T extends Acceptor1<T, T1>, T1 extends T, R> {
        @NotNull MonoDefinition1<T, T1, R> type1(@NotNull NF1<T1, R> f1);
    }
}
