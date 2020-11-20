package raystark.eflib.visitor.definition;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.visitor.acceptor.Acceptor3;
import raystark.eflib.type.TypeVar1;
import raystark.eflib.type.TypeVar2;
import raystark.eflib.type.TypeVar3;

public interface IDiDefinition3<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
    @NotNull R dispatch(@NotNull TypeVar1<T1> arg1, @NotNull TypeVar1<T1> arg2);
    @NotNull R dispatch(@NotNull TypeVar1<T1> arg1, @NotNull TypeVar2<T2> arg2);
    @NotNull R dispatch(@NotNull TypeVar1<T1> arg1, @NotNull TypeVar3<T3> arg2);
    @NotNull R dispatch(@NotNull TypeVar2<T2> arg1, @NotNull TypeVar1<T1> arg2);
    @NotNull R dispatch(@NotNull TypeVar2<T2> arg1, @NotNull TypeVar2<T2> arg2);
    @NotNull R dispatch(@NotNull TypeVar2<T2> arg1, @NotNull TypeVar3<T3> arg2);
    @NotNull R dispatch(@NotNull TypeVar3<T3> arg1, @NotNull TypeVar1<T1> arg2);
    @NotNull R dispatch(@NotNull TypeVar3<T3> arg1, @NotNull TypeVar2<T2> arg2);
    @NotNull R dispatch(@NotNull TypeVar3<T3> arg1, @NotNull TypeVar3<T3> arg2);
}
