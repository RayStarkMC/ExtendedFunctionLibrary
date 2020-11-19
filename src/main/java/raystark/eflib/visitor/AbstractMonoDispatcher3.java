package raystark.eflib.visitor;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.visitor.acceptor.Acceptor3;
import raystark.eflib.visitor.definition.IMonoDefinition3;

public abstract class AbstractMonoDispatcher3<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> implements IMonoDispatcher3<T, T1, T2, T3, R> {
    protected AbstractMonoDispatcher3() {}

    @Override
    public final @NotNull R apply(@NotNull T arg1) {
        return arg1.accept(definition());
    }

    @Override
    public final @NotNull NF1<T, R> asF1() {
        return IMonoDispatcher3.super.asF1();
    }

    protected abstract @NotNull IMonoDefinition3<T, T1, T2, T3, R> definition();
}
