package raystark.eflib.visitor;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.visitor.acceptor.Acceptor2;

public interface IMonoDispatcher2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
    @NotNull R apply(@NotNull T arg1);

    default @NotNull NF1<T, R> asF1() {
        return NF1.of(this::apply);
    }
}
