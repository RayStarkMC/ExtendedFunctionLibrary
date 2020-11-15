package raystark.eflib.visitor.definition;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.visitor.acceptor.Acceptor2;
import raystark.eflib.visitor.type.Type1;
import raystark.eflib.visitor.type.Type2;

public final class MonoDefinition2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> implements IMonoDefinition2<T, T1, T2, R> {
    private final NF1<T1, R> f1;
    private final NF1<T2, R> f2;

    private MonoDefinition2(
        NF1<T1, R> f1,
        NF1<T2, R> f2
    ) {
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public @NotNull R dispatch(@NotNull Type1<T1> arg1) {
        return f1.apply(arg1.unwrap());
    }

    @Override
    public @NotNull R dispatch(@NotNull Type2<T2> arg1) {
        return f2.apply(arg1.unwrap());
    }

    private static <T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R>
    BuilderT1<T, T1, T2, R> builder() {
        return f1 -> f2 -> new MonoDefinition2<>(f1, f2);
    }

    public static <T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R>
    @NotNull IMonoDefinition2<T, T1, T2, R> build(@NotNull NF1<BuilderT1<T, T1, T2, R>, MonoDefinition2<T, T1, T2, R>> buildProcess) {
        return buildProcess.apply(builder());
    }
    @FunctionalInterface
    interface BuilderT1<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
        @NotNull BuilderT2<T, T1, T2, R> type1(@NotNull NF1<T1, R> f1);
    }
    @FunctionalInterface
    interface BuilderT2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
        @NotNull MonoDefinition2<T, T1, T2, R> type2(@NotNull NF1<T2, R> f1);
    }
}
