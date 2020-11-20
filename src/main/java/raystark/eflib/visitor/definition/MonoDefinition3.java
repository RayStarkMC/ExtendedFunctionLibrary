package raystark.eflib.visitor.definition;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.visitor.acceptor.Acceptor3;
import raystark.eflib.type.TypeVar1;
import raystark.eflib.type.TypeVar2;
import raystark.eflib.type.TypeVar3;

public final class MonoDefinition3<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> implements IMonoDefinition3<T, T1, T2, T3, R> {
    private final NF1<T1, R> f1;
    private final NF1<T2, R> f2;
    private final NF1<T3, R> f3;

    private MonoDefinition3(
        NF1<T1, R> f1,
        NF1<T2, R> f2,
        NF1<T3, R> f3
    ) {
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;
    }

    @Override
    public @NotNull R dispatch(@NotNull TypeVar1<T1> arg1) {
        return f1.apply(arg1.unwrap());
    }

    @Override
    public @NotNull R dispatch(@NotNull TypeVar2<T2> arg1) {
        return f2.apply(arg1.unwrap());
    }

    @Override
    public @NotNull R dispatch(@NotNull TypeVar3<T3> arg1) {
        return f3.apply(arg1.unwrap());
    }

    private static <T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R>
    BuilderT1<T, T1, T2, T3, R> builder() {
        return f1 -> f2 -> f3 -> new MonoDefinition3<>(f1, f2, f3);
    }

    public static <T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R>
    @NotNull MonoDefinition3<T, T1, T2, T3, R> build(@NotNull NF1<BuilderT1<T, T1, T2, T3, R>, MonoDefinition3<T, T1, T2, T3, R>> buildProcess) {
        return buildProcess.apply(builder());
    }

    @FunctionalInterface
    public interface BuilderT1<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        @NotNull BuilderT2<T, T1, T2, T3, R> type1(@NotNull NF1<T1, R> f1);
    }
    @FunctionalInterface
    public interface BuilderT2<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        @NotNull BuilderT3<T, T1, T2, T3, R> type2(@NotNull NF1<T2, R> f1);
    }
    @FunctionalInterface
    public interface BuilderT3<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        @NotNull MonoDefinition3<T, T1, T2, T3, R> type3(@NotNull NF1<T3, R> f1);
    }
}
