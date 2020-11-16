package raystark.eflib.visitor.definition;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.visitor.acceptor.Acceptor3;
import raystark.eflib.visitor.type.Type1;
import raystark.eflib.visitor.type.Type2;
import raystark.eflib.visitor.type.Type3;

public interface MonoDefinition3<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
    @NotNull R dispatch(@NotNull Type1<T1> arg1);
    @NotNull R dispatch(@NotNull Type2<T2> arg1);
    @NotNull R dispatch(@NotNull Type3<T3> arg1);

    static <T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R>
    BuilderT1<T, T1, T2, T3, R> builder() {
        return f1 -> f2 -> f3 -> new MonoDefinition3<>() {
            @Override
            public @NotNull R dispatch(@NotNull Type1<T1> arg1) {
                return f1.apply(arg1.unwrap());
            }

            @Override
            public @NotNull R dispatch(@NotNull Type2<T2> arg1) {
                return f2.apply(arg1.unwrap());
            }

            @Override
            public @NotNull R dispatch(@NotNull Type3<T3> arg1) {
                return f3.apply(arg1.unwrap());
            }
        };
    }

    static <T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R>
    @NotNull MonoDefinition3<T, T1, T2, T3, R> build(@NotNull NF1<BuilderT1<T, T1, T2, T3, R>, MonoDefinition3<T, T1, T2, T3, R>> builder) {
        return builder.apply(builder());
    }

    @FunctionalInterface
    interface BuilderT1<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        @NotNull BuilderT2<T, T1, T2, T3, R> type1(@NotNull NF1<T1, R> f1);
    }
    @FunctionalInterface
    interface BuilderT2<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        @NotNull BuilderT3<T, T1, T2, T3, R> type2(@NotNull NF1<T2, R> f1);
    }
    @FunctionalInterface
    interface BuilderT3<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        @NotNull MonoDefinition3<T, T1, T2, T3, R> type3(@NotNull NF1<T3, R> f1);
    }
}