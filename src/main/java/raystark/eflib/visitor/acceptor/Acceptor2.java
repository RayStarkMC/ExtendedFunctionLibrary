package raystark.eflib.visitor.acceptor;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.visitor.DiDispathcer2;
import raystark.eflib.visitor.MonoDispatcher2;
import raystark.eflib.visitor.definition.DiDefinition2;
import raystark.eflib.visitor.definition.IMonoDefinition2;
import raystark.eflib.visitor.definition.MonoDefinition2;

public interface Acceptor2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T> {
    @NotNull <R> R accept(@NotNull IMonoDefinition2<T, T1, T2, R> definition2);

    static <T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R>
    MonoDispatcher2<T, T1, T2, R> monoDispatcher2(@NotNull NF1<MonoDefinition2.BuilderT1<T, T1, T2, R>, MonoDefinition2<T, T1, T2, R>> buildProcess) {
        return MonoDispatcher2.build(buildProcess);
    }

    static <T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R>
    DiDispathcer2<T, T1, T2, R> diDispatcher2(@NotNull NF1<DiDefinition2.BuilderT11<T, T1, T2, R>, DiDefinition2<T, T1, T2, R>> buildProcess) {
        return DiDispathcer2.build(buildProcess);
    }
}
