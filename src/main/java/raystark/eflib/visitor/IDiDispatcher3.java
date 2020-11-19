package raystark.eflib.visitor;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF2;
import raystark.eflib.visitor.acceptor.Acceptor3;

public interface IDiDispatcher3<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
    default @NotNull R apply(@NotNull T arg1, @NotNull T arg2) {
        return apply(arg1).apply(arg2);
    }

    default @NotNull NF2<T, T, R> asF2() {
        return NF2.of(this::apply);
    }

    @NotNull IMonoDispatcher3<T, T1, T2, T3, R> apply(@NotNull T arg1);
}
