package raystark.eflib.visitor;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.visitor.acceptor.Acceptor2;

public interface IDefinedDiVisitor2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
    @NotNull R apply(@NotNull T arg1, @NotNull T arg2);

    @NotNull IDefinedVisitor2<T, T1, T2, R> apply(@NotNull T arg1);
}
