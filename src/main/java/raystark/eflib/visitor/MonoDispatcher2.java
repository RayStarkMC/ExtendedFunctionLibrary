package raystark.eflib.visitor;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.visitor.acceptor.Acceptor2;
import raystark.eflib.visitor.definition.MonoDefinition2;

public final class MonoDispatcher2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> extends AbstractMonoDispatcher2<T, T1, T2, R> {
    private final MonoDefinition2<T, T1, T2, R> definition2;

    private MonoDispatcher2(MonoDefinition2<T, T1, T2, R> definition2) {
        this.definition2 = definition2;
    }

    public static <T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R>
    @NotNull MonoDispatcher2<T, T1, T2, R> build(@NotNull NF1<MonoDefinition2.BuilderT1<T, T1, T2, R>, MonoDefinition2<T, T1, T2, R>> buildProcess) {
        return new MonoDispatcher2<>(MonoDefinition2.build(buildProcess));
    }

    @Override
    protected @NotNull MonoDefinition2<T, T1, T2, R> definition2() {
        return definition2;
    }
}
