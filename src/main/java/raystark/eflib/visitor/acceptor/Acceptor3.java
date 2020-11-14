package raystark.eflib.visitor.acceptor;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.visitor.definition.MonoDefinition3;

public interface Acceptor3<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T> {
    @NotNull <R> R accept(@NotNull MonoDefinition3<T, T1, T2, T3, R> definition3);
}
