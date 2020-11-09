package raystark.eflib.visitor.definition.di;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.function.notnull.NF2;
import raystark.eflib.visitor.acceptor.Acceptor3;
import raystark.eflib.visitor.type.Type1;
import raystark.eflib.visitor.type.Type2;
import raystark.eflib.visitor.type.Type3;

public interface DiDefinition3<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
    @NotNull R dispatch(@NotNull Type1<T1> arg1, @NotNull Type1<T1> arg2);
    @NotNull R dispatch(@NotNull Type1<T1> arg1, @NotNull Type2<T2> arg2);
    @NotNull R dispatch(@NotNull Type1<T1> arg1, @NotNull Type3<T3> arg2);
    @NotNull R dispatch(@NotNull Type2<T2> arg1, @NotNull Type1<T1> arg2);
    @NotNull R dispatch(@NotNull Type2<T2> arg1, @NotNull Type2<T2> arg2);
    @NotNull R dispatch(@NotNull Type2<T2> arg1, @NotNull Type3<T3> arg2);
    @NotNull R dispatch(@NotNull Type3<T3> arg1, @NotNull Type1<T1> arg2);
    @NotNull R dispatch(@NotNull Type3<T3> arg1, @NotNull Type2<T2> arg2);
    @NotNull R dispatch(@NotNull Type3<T3> arg1, @NotNull Type3<T3> arg2);

    static <T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R>
    @NotNull BuilderT11<T, T1, T2, T3, R> builder() {
        return f11 -> f12 -> f13 ->
               f21 -> f22 -> f23 ->
               f31 -> f32 -> f33 -> new DiDefinition3<>() {
            @Override
            public @NotNull R dispatch(@NotNull Type1<T1> arg1, @NotNull Type1<T1> arg2) {
                return f11.apply(arg1.unwrap(), arg2.unwrap());
            }

            @Override
            public @NotNull R dispatch(@NotNull Type1<T1> arg1, @NotNull Type2<T2> arg2) {
                return f12.apply(arg1.unwrap(), arg2.unwrap());
            }

            @Override
            public @NotNull R dispatch(@NotNull Type1<T1> arg1, @NotNull Type3<T3> arg2) {
                return f13.apply(arg1.unwrap(), arg2.unwrap());
            }

            @Override
            public @NotNull R dispatch(@NotNull Type2<T2> arg1, @NotNull Type1<T1> arg2) {
                return f21.apply(arg1.unwrap(), arg2.unwrap());
            }

            @Override
            public @NotNull R dispatch(@NotNull Type2<T2> arg1, @NotNull Type2<T2> arg2) {
                return f22.apply(arg1.unwrap(), arg2.unwrap());
            }

            @Override
            public @NotNull R dispatch(@NotNull Type2<T2> arg1, @NotNull Type3<T3> arg2) {
                return f23.apply(arg1.unwrap(), arg2.unwrap());
            }

            @Override
            public @NotNull R dispatch(@NotNull Type3<T3> arg1, @NotNull Type1<T1> arg2) {
                return f31.apply(arg1.unwrap(), arg2.unwrap());
            }

            @Override
            public @NotNull R dispatch(@NotNull Type3<T3> arg1, @NotNull Type2<T2> arg2) {
                return f32.apply(arg1.unwrap(), arg2.unwrap());
            }

            @Override
            public @NotNull R dispatch(@NotNull Type3<T3> arg1, @NotNull Type3<T3> arg2) {
                return f33.apply(arg1.unwrap(), arg2.unwrap());
            }
        };
    }

    static <T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R>
    @NotNull DiDefinition3<T, T1, T2, T3, R> build(@NotNull NF1<BuilderT11<T, T1, T2, T3, R>, DiDefinition3<T, T1, T2, T3, R>> builder) {
        return builder.apply(builder());
    }

    @FunctionalInterface
    interface BuilderT11<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        @NotNull BuilderT12<T, T1, T2, T3, R> type11(@NotNull NF2<T1, T1, R> f2);
    }
    @FunctionalInterface
    interface BuilderT12<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        @NotNull BuilderT13<T, T1, T2, T3, R> type12(@NotNull NF2<T1, T2, R> f2);
    }
    @FunctionalInterface
    interface BuilderT13<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        @NotNull BuilderT21<T, T1, T2, T3, R> type21(@NotNull NF2<T1, T3, R> f2);
    }
    @FunctionalInterface
    interface BuilderT21<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        @NotNull BuilderT22<T, T1, T2, T3, R> type22(@NotNull NF2<T2, T1, R> f2);
    }
    @FunctionalInterface
    interface BuilderT22<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        @NotNull BuilderT23<T, T1, T2, T3, R> type11(@NotNull NF2<T2, T2, R> f2);
    }
    @FunctionalInterface
    interface BuilderT23<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        @NotNull BuilderT31<T, T1, T2, T3, R> type12(@NotNull NF2<T2, T3, R> f2);
    }
    @FunctionalInterface
    interface BuilderT31<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        @NotNull BuilderT32<T, T1, T2, T3, R> type21(@NotNull NF2<T3, T1, R> f2);
    }
    @FunctionalInterface
    interface BuilderT32<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        @NotNull BuilderT33<T, T1, T2, T3, R> type22(@NotNull NF2<T3, T2, R> f2);
    }
    @FunctionalInterface
    interface BuilderT33<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        @NotNull DiDefinition3<T, T1, T2, T3, R> type22(@NotNull NF2<T3, T3, R> f2);
    }
}
