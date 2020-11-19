package raystark.eflib.visitor;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF2;
import raystark.eflib.visitor.acceptor.Acceptor2;

public interface IDiDispatcher2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
    default @NotNull R apply(@NotNull T arg1, @NotNull T arg2) {
        return apply(arg1).apply(arg2);
    }

    default @NotNull NF2<T, T, R> asF2() {
        return NF2.of(this::apply);
    }

    @NotNull IMonoDispatcher2<T, T1, T2, R> apply(@NotNull T arg1);
}
