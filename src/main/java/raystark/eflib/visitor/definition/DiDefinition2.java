package raystark.eflib.visitor.definition;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.function.notnull.NF2;
import raystark.eflib.visitor.acceptor.Acceptor2;
import raystark.eflib.type.Type1;
import raystark.eflib.type.Type2;

public final class DiDefinition2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> implements IDiDefinition2<T, T1, T2, R> {
    private final NF2<T1, T1, R> f11;
    private final NF2<T1, T2, R> f12;
    private final NF2<T2, T1, R> f21;
    private final NF2<T2, T2, R> f22;

    private DiDefinition2(
        NF2<T1, T1, R> f11,
        NF2<T1, T2, R> f12,
        NF2<T2, T1, R> f21,
        NF2<T2, T2, R> f22
    ) {
        this.f11 = f11;
        this.f12 = f12;
        this.f21 = f21;
        this.f22 = f22;
    }

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

    private static <T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R>
    @NotNull BuilderT11<T, T1, T2, R> builder() {
        return f11 -> f12 ->
            f21 -> f22 -> new DiDefinition2<>(f11, f12, f21, f22);
    }

    public static <T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R>
    @NotNull DiDefinition2<T, T1, T2, R> build(@NotNull NF1<BuilderT11<T, T1, T2, R>, DiDefinition2<T, T1, T2, R>> buildProcess) {
        return buildProcess.apply(builder());
    }
    @FunctionalInterface
    public interface BuilderT11<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
        @NotNull BuilderT12<T, T1, T2, R> type11(@NotNull NF2<T1, T1, R> f2);
    }
    @FunctionalInterface
    public interface BuilderT12<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
        @NotNull BuilderT21<T, T1, T2, R> type12(@NotNull NF2<T1, T2, R> f2);
    }
    @FunctionalInterface
    public interface BuilderT21<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
        @NotNull BuilderT22<T, T1, T2, R> type21(@NotNull NF2<T2, T1, R> f2);
    }
    @FunctionalInterface
    public interface BuilderT22<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
        @NotNull DiDefinition2<T, T1, T2, R> type22(@NotNull NF2<T2, T2, R> f2);
    }
}
