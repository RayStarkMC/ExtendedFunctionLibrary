package raystark.eflib.visitor.acceptor;

import raystark.eflib.visitor.definition.MonoDefinition1;

public interface Acceptor1<T extends Acceptor1<T, T1>, T1 extends T> {
    <R> R accept(MonoDefinition1<T, T1, R> monoDefinition1);
}
