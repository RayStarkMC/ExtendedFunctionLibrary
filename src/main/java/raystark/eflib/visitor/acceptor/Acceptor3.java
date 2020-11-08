package raystark.eflib.visitor.acceptor;

import raystark.eflib.visitor.definition.MonoDefinition3;

public interface Acceptor3<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T> {
    <R> R accept(MonoDefinition3<T, T1, T2, T3, R> monoDefinition3);
}
