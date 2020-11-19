package raystark.eflib.visitor;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.visitor.acceptor.Acceptor3;
import raystark.eflib.visitor.definition.IMonoDefinition3;
import raystark.eflib.visitor.definition.MonoDefinition3;

public final class MonoDispatcher3<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> extends AbstractMonoDispatcher3<T, T1, T2, T3, R> {
    private final MonoDefinition3<T, T1, T2, T3, R> definition3;

    private MonoDispatcher3(MonoDefinition3<T, T1, T2, T3, R> definition3) {
        this.definition3 = definition3;
    }

    @Override
    protected IMonoDefinition3<T, T1, T2, T3, R> definition3() {
        return definition3;
    }

    public static <T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R>
    @NotNull MonoDispatcher3<T, T1, T2, T3, R> build(@NotNull NF1<MonoDefinition3.BuilderT1<T, T1, T2, T3, R>, MonoDefinition3<T, T1, T2, T3, R>> builder) {
        return new MonoDispatcher3<>(MonoDefinition3.build(builder));
    }
}
