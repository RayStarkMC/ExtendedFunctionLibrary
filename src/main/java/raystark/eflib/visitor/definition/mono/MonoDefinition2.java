package raystark.eflib.visitor.definition.mono;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.visitor.acceptor.Acceptor2;
import raystark.eflib.visitor.type.Type1;
import raystark.eflib.visitor.type.Type2;

public interface MonoDefinition2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
    @NotNull R dispatch(@NotNull Type1<T1> arg1);
    @NotNull R dispatch(@NotNull Type2<T2> arg1);

    static <T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R>
    BuilderT1<T, T1, T2, R> builder() {
        return f1 -> f2 -> new MonoDefinition2<>() {
            @Override
            public @NotNull R dispatch(@NotNull Type1<T1> arg1) {
                return f1.apply(arg1.get());
            }

            @Override
            public @NotNull R dispatch(@NotNull Type2<T2> arg1) {
                return f2.apply(arg1.get());
            }
        };
    }

    static <T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R>
    @NotNull MonoDefinition2<T, T1, T2, R> build(@NotNull NF1<BuilderT1<T, T1, T2, R>, MonoDefinition2<T, T1, T2, R>> builder) {
        return builder.apply(builder());
    }

    interface BuilderT1<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
        @NotNull BuilderT2<T, T1, T2, R> type1(@NotNull NF1<T1, R> f1);
    }

    interface BuilderT2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
        @NotNull MonoDefinition2<T, T1, T2, R> type2(@NotNull NF1<T2, R> f1);
    }
}
