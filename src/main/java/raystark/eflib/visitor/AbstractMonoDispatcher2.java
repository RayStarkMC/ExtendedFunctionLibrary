package raystark.eflib.visitor;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.visitor.acceptor.Acceptor2;
import raystark.eflib.visitor.definition.IMonoDefinition2;

public abstract class AbstractMonoDispatcher2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> implements IMonoDispatcher2<T, T1, T2, R> {
    protected AbstractMonoDispatcher2() {}

    @Override
    public final @NotNull R apply(@NotNull T arg1) {
        return arg1.accept(definition());
    }

    @Override
    public final @NotNull NF1<T, R> asF1() {
        return IMonoDispatcher2.super.asF1();
    }

    protected abstract @NotNull IMonoDefinition2<T, T1, T2, R> definition();
}