package raystark.eflib.visitor.acceptor;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.visitor.definition.mono.MonoDefinition2;

public interface Acceptor2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T> {
    @NotNull  <R> R accept(@NotNull MonoDefinition2<T, T1, T2, R> monoDefinition2);
}
