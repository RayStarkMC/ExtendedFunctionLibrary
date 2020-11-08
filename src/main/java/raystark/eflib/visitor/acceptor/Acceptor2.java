package raystark.eflib.visitor.acceptor;

import raystark.eflib.visitor.definition.MonoDefinition2;

public interface Acceptor2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T> {
    <R> R accept(MonoDefinition2<T, T1, T2, R> monoDefinition2);
}
