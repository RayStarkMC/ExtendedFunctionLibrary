package raystark.eflib.visitor.acceptor;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.visitor.DefinedDiVisitor3;
import raystark.eflib.visitor.DefinedVisitor3;
import raystark.eflib.visitor.definition.DiDefinition3;
import raystark.eflib.visitor.definition.IMonoDefinition3;
import raystark.eflib.visitor.definition.MonoDefinition3;

public interface Acceptor3<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T> {
    @NotNull <R> R accept(@NotNull IMonoDefinition3<T, T1, T2, T3, R> definition3);

    static <T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R>
    DefinedVisitor3<T, T1, T2, T3, R> definedVisitor3(@NotNull NF1<MonoDefinition3.BuilderT1<T, T1, T2, T3, R>, MonoDefinition3<T, T1, T2, T3, R>> builderProcess) {
        return DefinedVisitor3.build(builderProcess);
    }

    static <T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R>
    DefinedDiVisitor3<T, T1, T2, T3, R> definedDiVisitor3(@NotNull NF1<DiDefinition3.BuilderT11<T, T1, T2, T3, R>, DiDefinition3<T, T1, T2, T3, R>> builderProcess) {
        return DefinedDiVisitor3.build(builderProcess);
    }
}
