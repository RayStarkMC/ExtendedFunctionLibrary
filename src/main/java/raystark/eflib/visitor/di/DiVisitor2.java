package raystark.eflib.visitor.di;

import raystark.eflib.visitor.acceptor.Acceptor2;
import raystark.eflib.visitor.definition.di.DiDefinition2;
import raystark.eflib.visitor.definition.mono.MonoDefinition2;
import raystark.eflib.visitor.mono.IMonoVisitor2;

public interface DiVisitor2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> extends IMonoVisitor2<T, T1, T2, IMonoVisitor2<T, T1, T2, R>> {
    default R apply(T arg1, T arg2) {
        return apply(arg1).apply(arg2);
    }

    @Override
    default MonoDefinition2<T, T1, T2, IMonoVisitor2<T, T1, T2, R>> monoDefinition2() {
        return MonoDefinition2.<T, T1, T2, IMonoVisitor2<T, T1, T2, R>>builder()
            .type1(arg1 -> IMonoVisitor2.of(MonoDefinition2.<T, T1, T2, R>builder()
                .type1(arg2 -> diDefinition2().dispatch(() -> arg1, () -> arg2))
                .type2(arg2 -> diDefinition2().dispatch(() -> arg1, () -> arg2))))
            .type2(arg1 -> IMonoVisitor2.of(MonoDefinition2.<T, T1, T2, R>builder()
                .type1(arg2 -> diDefinition2().dispatch(() -> arg1, () -> arg2))
                .type2(arg2 -> diDefinition2().dispatch(() -> arg1, () -> arg2))));
    }

    DiDefinition2<T, T1, T2, R> diDefinition2();

    static <T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R>
    DiVisitor2<T, T1, T2, R> of(DiDefinition2<T, T1, T2, R> definition) {
        return () -> definition;
    }
}
