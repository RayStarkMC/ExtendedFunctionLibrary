package raystark.eflib.visitor.di;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.visitor.acceptor.Acceptor2;
import raystark.eflib.visitor.definition.di.DiDefinition2;
import raystark.eflib.visitor.definition.mono.MonoDefinition2;
import raystark.eflib.visitor.mono.IMonoVisitor2;

public interface DiVisitor2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> extends IMonoVisitor2<T, T1, T2, IMonoVisitor2<T, T1, T2, R>> {
    default @NotNull R apply(@NotNull T arg1, @NotNull T arg2) {
        return apply(arg1).apply(arg2);
    }

    @Override
    default @NotNull MonoDefinition2<T, T1, T2, IMonoVisitor2<T, T1, T2, R>> monoDefinition2() {
        return MonoDefinition2.build(builder1 -> builder1
            .type1(arg1 -> IMonoVisitor2.build(builder2 -> builder2
                .type1(arg2 -> diDefinition2().dispatch(() -> arg1, () -> arg2))
                .type2(arg2 -> diDefinition2().dispatch(() -> arg1, () -> arg2))))
            .type2(arg1 -> IMonoVisitor2.build(builder2 -> builder2
                .type1(arg2 -> diDefinition2().dispatch(() -> arg1, () -> arg2))
                .type2(arg2 -> diDefinition2().dispatch(() -> arg1, () -> arg2)))));
    }

    @NotNull DiDefinition2<T, T1, T2, R> diDefinition2();

    static <T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R>
    @NotNull DiVisitor2<T, T1, T2, R> of(@NotNull DiDefinition2<T, T1, T2, R> definition) {
        return () -> definition;
    }

    static <T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R>
    @NotNull DiVisitor2<T, T1, T2, R> build(@NotNull NF1<DiDefinition2.BuilderT11<T, T1, T2, R>, DiDefinition2<T, T1, T2, R>> builder) {
        return of(builder.apply(DiDefinition2.builder()));
    }
}
