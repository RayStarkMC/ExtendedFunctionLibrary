package raystark.eflib.visitor.mono;


import raystark.eflib.visitor.acceptor.Acceptor1;
import raystark.eflib.visitor.definition.DiDefinition1;
import raystark.eflib.visitor.definition.MonoDefinition1;

public interface DiVisitor1<T extends Acceptor1<T, T1>, T1 extends T, R> extends IMonoVisitor1<T, T1, IMonoVisitor1<T, T1, R>> {
    default R apply(T arg1, T arg2) {
        return apply(arg1).apply(arg2);
    }

    @Override
    default MonoDefinition1<T, T1, IMonoVisitor1<T, T1, R>> monoDefinition1() {
        return MonoDefinition1.<T, T1, IMonoVisitor1<T, T1, R>>builder()
            .type1(arg1 -> IMonoVisitor1.of(MonoDefinition1.<T, T1, R>builder()
                .type1(arg2 -> diDefinition1().dispatch(() -> arg1, () -> arg2))));
    }

    DiDefinition1<T, T1, R> diDefinition1();

    static <T extends Acceptor1<T, T1>, T1 extends T, R>
    DiVisitor1<T, T1, R> of(DiDefinition1<T, T1, R> definition) {
        return () -> definition;
    }
}
