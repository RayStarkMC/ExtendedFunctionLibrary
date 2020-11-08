package raystark.eflib.visitor.mono;

import raystark.eflib.function.notnull.NF1;
import raystark.eflib.visitor.acceptor.Acceptor3;
import raystark.eflib.visitor.definition.mono.MonoDefinition3;

public interface IMonoVisitor3<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
    default R apply(T arg1) {
        return arg1.accept(monoDefinition3());
    }
    MonoDefinition3<T, T1, T2, T3, R> monoDefinition3();

    static <T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R>
    IMonoVisitor3<T, T1, T2, T3, R> of(MonoDefinition3<T, T1, T2, T3, R> definition) {
        return () -> definition;
    }

    static <T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R>
    IMonoVisitor3<T, T1, T2, T3, R> build(NF1<MonoDefinition3.BuilderT1<T, T1, T2, T3, R>, MonoDefinition3<T, T1, T2, T3, R>> builder) {
        return of(builder.apply(MonoDefinition3.builder()));
    }
}
