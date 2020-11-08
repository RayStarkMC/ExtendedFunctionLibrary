package raystark.eflib.visitor.mono;

import raystark.eflib.visitor.acceptor.Acceptor1;
import raystark.eflib.visitor.definition.MonoDefinition1;

public interface IMonoVisitor1<T extends Acceptor1<T, T1>, T1 extends T, R> {
    default R apply(T arg1) {
        return arg1.accept(monoDefinition1());
    }
    MonoDefinition1<T, T1, R> monoDefinition1();

    static <T extends Acceptor1<T, T1>, T1 extends T, R>
    IMonoVisitor1<T, T1, R> of(MonoDefinition1<T, T1, R> definition) {
        return () -> definition;
    }
}
