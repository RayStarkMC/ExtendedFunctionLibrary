package raystark.eflib.visitor.definition;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.function.notnull.NF2;
import raystark.eflib.visitor.acceptor.Acceptor2;
import raystark.eflib.visitor.type.Type1;
import raystark.eflib.visitor.type.Type2;

public interface DiDefinition2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
    @NotNull R dispatch(@NotNull Type1<T1> arg1, @NotNull Type1<T1> arg2);
    @NotNull R dispatch(@NotNull Type1<T1> arg1, @NotNull Type2<T2> arg2);
    @NotNull R dispatch(@NotNull Type2<T2> arg1, @NotNull Type1<T1> arg2);
    @NotNull R dispatch(@NotNull Type2<T2> arg1, @NotNull Type2<T2> arg2);

    static <T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R>
    @NotNull BuilderT11<T, T1, T2, R> builder() {
        return f11 -> f12 ->
               f21 -> f22 -> new DiDefinition2<>() {
            @Override
            public @NotNull R dispatch(@NotNull Type1<T1> arg1, @NotNull Type1<T1> arg2) {
                return f11.apply(arg1.unwrap(), arg2.unwrap());
            }

            @Override
            public @NotNull R dispatch(@NotNull Type1<T1> arg1, @NotNull Type2<T2> arg2) {
                return f12.apply(arg1.unwrap(), arg2.unwrap());
            }

            @Override
            public @NotNull R dispatch(@NotNull Type2<T2> arg1, @NotNull Type1<T1> arg2) {
                return f21.apply(arg1.unwrap(), arg2.unwrap());
            }

            @Override
            public @NotNull R dispatch(@NotNull Type2<T2> arg1, @NotNull Type2<T2> arg2) {
                return f22.apply(arg1.unwrap(), arg2.unwrap());
            }
        };
    }

    static <T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R>
    @NotNull DiDefinition2<T, T1, T2, R> build(@NotNull NF1<BuilderT11<T, T1, T2, R>, DiDefinition2<T, T1, T2, R>> builder) {
        return builder.apply(builder());
    }
    @FunctionalInterface
    interface BuilderT11<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
        @NotNull BuilderT12<T, T1, T2, R> type11(@NotNull NF2<T1, T1, R> f2);
    }
    @FunctionalInterface
    interface BuilderT12<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
        @NotNull BuilderT21<T, T1, T2, R> type12(@NotNull NF2<T1, T2, R> f2);
    }
    @FunctionalInterface
    interface BuilderT21<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
        @NotNull BuilderT22<T, T1, T2, R> type21(@NotNull NF2<T2, T1, R> f2);
    }
    @FunctionalInterface
    interface BuilderT22<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
        @NotNull DiDefinition2<T, T1, T2, R> type22(@NotNull NF2<T2, T2, R> f2);
    }
}
