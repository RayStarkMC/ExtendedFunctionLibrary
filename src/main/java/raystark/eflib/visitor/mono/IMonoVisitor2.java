package raystark.eflib.visitor.mono;

import raystark.eflib.visitor.acceptor.Acceptor2;
import raystark.eflib.visitor.definition.mono.MonoDefinition2;

public interface IMonoVisitor2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
    default R apply(T arg1) {
        return arg1.accept(monoDefinition2());
    }

    MonoDefinition2<T, T1, T2, R> monoDefinition2();

    static <T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R>
    IMonoVisitor2<T, T1, T2, R> of(MonoDefinition2<T, T1, T2, R> definition) {
        return () -> definition;
    }
}
