package raystark.eflib.visitor.definition;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.function.notnull.NF2;
import raystark.eflib.visitor.acceptor.Acceptor3;
import raystark.eflib.type.TypeVar1;
import raystark.eflib.type.TypeVar2;
import raystark.eflib.type.TypeVar3;

public class DiDefinition3<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> implements IDiDefinition3<T, T1, T2, T3, R> {
    private final NF2<T1, T1, R> f11;
    private final NF2<T1, T2, R> f12;
    private final NF2<T1, T3, R> f13;
    private final NF2<T2, T1, R> f21;
    private final NF2<T2, T2, R> f22;
    private final NF2<T2, T3, R> f23;
    private final NF2<T3, T1, R> f31;
    private final NF2<T3, T2, R> f32;
    private final NF2<T3, T3, R> f33;

    private DiDefinition3(
        NF2<T1, T1, R> f11,
        NF2<T1, T2, R> f12,
        NF2<T1, T3, R> f13,
        NF2<T2, T1, R> f21,
        NF2<T2, T2, R> f22,
        NF2<T2, T3, R> f23,
        NF2<T3, T1, R> f31,
        NF2<T3, T2, R> f32,
        NF2<T3, T3, R> f33
    ) {
        this.f11 = f11;
        this.f12 = f12;
        this.f13 = f13;
        this.f21 = f21;
        this.f22 = f22;
        this.f23 = f23;
        this.f31 = f31;
        this.f32 = f32;
        this.f33 = f33;
    }

    @Override
    public @NotNull R dispatch(@NotNull TypeVar1<T1> arg1, @NotNull TypeVar1<T1> arg2) {
        return f11.apply(arg1.unwrap(), arg2.unwrap());
    }

    @Override
    public @NotNull R dispatch(@NotNull TypeVar1<T1> arg1, @NotNull TypeVar2<T2> arg2) {
        return f12.apply(arg1.unwrap(), arg2.unwrap());
    }

    @Override
    public @NotNull R dispatch(@NotNull TypeVar1<T1> arg1, @NotNull TypeVar3<T3> arg2) {
        return f13.apply(arg1.unwrap(), arg2.unwrap());
    }

    @Override
    public @NotNull R dispatch(@NotNull TypeVar2<T2> arg1, @NotNull TypeVar1<T1> arg2) {
        return f21.apply(arg1.unwrap(), arg2.unwrap());
    }

    @Override
    public @NotNull R dispatch(@NotNull TypeVar2<T2> arg1, @NotNull TypeVar2<T2> arg2) {
        return f22.apply(arg1.unwrap(), arg2.unwrap());
    }

    @Override
    public @NotNull R dispatch(@NotNull TypeVar2<T2> arg1, @NotNull TypeVar3<T3> arg2) {
        return f23.apply(arg1.unwrap(), arg2.unwrap());
    }

    @Override
    public @NotNull R dispatch(@NotNull TypeVar3<T3> arg1, @NotNull TypeVar1<T1> arg2) {
        return f31.apply(arg1.unwrap(), arg2.unwrap());
    }

    @Override
    public @NotNull R dispatch(@NotNull TypeVar3<T3> arg1, @NotNull TypeVar2<T2> arg2) {
        return f32.apply(arg1.unwrap(), arg2.unwrap());
    }

    @Override
    public @NotNull R dispatch(@NotNull TypeVar3<T3> arg1, @NotNull TypeVar3<T3> arg2) {
        return f33.apply(arg1.unwrap(), arg2.unwrap());
    }


    private static <T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R>
    @NotNull BuilderT11<T, T1, T2, T3, R> builder() {
        return f11 -> f12 -> f13 ->
               f21 -> f22 -> f23 ->
               f31 -> f32 -> f33 ->
               new DiDefinition3<>(
               f11, f12, f13,
               f21, f22, f23,
               f31, f32, f33
        );
    }

    public static <T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R>
    @NotNull DiDefinition3<T, T1, T2, T3, R> build(@NotNull NF1<BuilderT11<T, T1, T2, T3, R>, DiDefinition3<T, T1, T2, T3, R>> buildProcess) {
        return buildProcess.apply(builder());
    }

    @FunctionalInterface
    public interface BuilderT11<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        @NotNull BuilderT12<T, T1, T2, T3, R> type11(@NotNull NF2<T1, T1, R> f2);
    }
    @FunctionalInterface
    public interface BuilderT12<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        @NotNull BuilderT13<T, T1, T2, T3, R> type12(@NotNull NF2<T1, T2, R> f2);
    }
    @FunctionalInterface
    public interface BuilderT13<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        @NotNull BuilderT21<T, T1, T2, T3, R> type21(@NotNull NF2<T1, T3, R> f2);
    }
    @FunctionalInterface
    public interface BuilderT21<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        @NotNull BuilderT22<T, T1, T2, T3, R> type22(@NotNull NF2<T2, T1, R> f2);
    }
    @FunctionalInterface
    public interface BuilderT22<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        @NotNull BuilderT23<T, T1, T2, T3, R> type11(@NotNull NF2<T2, T2, R> f2);
    }
    @FunctionalInterface
    public interface BuilderT23<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        @NotNull BuilderT31<T, T1, T2, T3, R> type12(@NotNull NF2<T2, T3, R> f2);
    }
    @FunctionalInterface
    public interface BuilderT31<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        @NotNull BuilderT32<T, T1, T2, T3, R> type21(@NotNull NF2<T3, T1, R> f2);
    }
    @FunctionalInterface
    public interface BuilderT32<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        @NotNull BuilderT33<T, T1, T2, T3, R> type22(@NotNull NF2<T3, T2, R> f2);
    }
    @FunctionalInterface
    public interface BuilderT33<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        @NotNull DiDefinition3<T, T1, T2, T3, R> type22(@NotNull NF2<T3, T3, R> f2);
    }
}
