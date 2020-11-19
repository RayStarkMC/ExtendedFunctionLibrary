package raystark.eflib.visitor;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.visitor.acceptor.Acceptor2;
import raystark.eflib.visitor.definition.IMonoDefinition2;

public abstract class AbstractMonoDispatcher2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> implements IMonoDispatcher2<T, T1, T2, R> {
    protected AbstractMonoDispatcher2() {}

    @Override
    public final @NotNull R apply(@NotNull T arg1) {
        return arg1.accept(definition());
    }

    protected abstract @NotNull IMonoDefinition2<T, T1, T2, R> definition();
}