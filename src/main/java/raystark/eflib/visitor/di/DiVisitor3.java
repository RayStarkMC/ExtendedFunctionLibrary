package raystark.eflib.visitor.di;

import raystark.eflib.function.notnull.NF1;
import raystark.eflib.visitor.acceptor.Acceptor3;
import raystark.eflib.visitor.definition.di.DiDefinition3;
import raystark.eflib.visitor.definition.mono.MonoDefinition3;
import raystark.eflib.visitor.mono.IMonoVisitor3;

public interface DiVisitor3<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> extends IMonoVisitor3<T, T1, T2, T3, IMonoVisitor3<T, T1, T2, T3, R>> {
    default R apply(T arg1, T arg2) {
        return apply(arg1).apply(arg2);
    }

    @Override
    default MonoDefinition3<T, T1, T2, T3, IMonoVisitor3<T, T1, T2, T3, R>> monoDefinition3() {
        return MonoDefinition3.build(builder1 -> builder1
            .type1(arg1 -> IMonoVisitor3.build(builder2 -> builder2
                .type1(arg2 -> diDefinition3().dispatch(() -> arg1, () -> arg2))
                .type2(arg2 -> diDefinition3().dispatch(() -> arg1, () -> arg2))
                .type3(arg2 -> diDefinition3().dispatch(() -> arg1, () -> arg2))))
            .type2(arg1 -> IMonoVisitor3.build(builder2 -> builder2
                .type1(arg2 -> diDefinition3().dispatch(() -> arg1, () -> arg2))
                .type2(arg2 -> diDefinition3().dispatch(() -> arg1, () -> arg2))
                .type3(arg2 -> diDefinition3().dispatch(() -> arg1, () -> arg2))))
            .type3(arg1 -> IMonoVisitor3.build(builder2 -> builder2
                .type1(arg2 -> diDefinition3().dispatch(() -> arg1, () -> arg2))
                .type2(arg2 -> diDefinition3().dispatch(() -> arg1, () -> arg2))
                .type3(arg2 -> diDefinition3().dispatch(() -> arg1, () -> arg2)))));
    }

    DiDefinition3<T, T1, T2, T3, R> diDefinition3();

    static <T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R>
    DiVisitor3<T, T1, T2, T3, R> of(DiDefinition3<T, T1, T2, T3, R> definition) {
        return () -> definition;
    }

    static <T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R>
    DiVisitor3<T, T1, T2, T3, R> build(NF1<DiDefinition3.BuilderT11<T, T1, T2, T3, R>, DiDefinition3<T, T1, T2, T3, R>> builder) {
        return of(builder.apply(DiDefinition3.builder()));
    }
}
