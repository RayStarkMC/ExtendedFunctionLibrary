package raystark.eflib.visitor.definition;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.visitor.acceptor.Acceptor2;
import raystark.eflib.type.TypeVar1;
import raystark.eflib.type.TypeVar2;

public interface IMonoDefinition2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
    @NotNull R dispatch(@NotNull TypeVar1<T1> arg1);
    @NotNull R dispatch(@NotNull TypeVar2<T2> arg1);
}
