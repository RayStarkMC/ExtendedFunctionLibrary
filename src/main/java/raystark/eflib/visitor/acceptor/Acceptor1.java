package raystark.eflib.visitor.acceptor;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.visitor.definition.mono.MonoDefinition1;

public interface Acceptor1<T extends Acceptor1<T, T1>, T1 extends T> {
    @NotNull <R> R accept(@NotNull MonoDefinition1<T, T1, R> definition1);
}
