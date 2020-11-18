package raystark.eflib.visitor;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.visitor.acceptor.Acceptor3;
import raystark.eflib.visitor.definition.DiDefinition3;

public class DefinedDiVisitor3<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> extends AbstractDefinedDiVisitor3<T, T1, T2, T3, R> {
    private final DiDefinition3<T, T1, T2, T3, R> definition3;

    private DefinedDiVisitor3(DiDefinition3<T, T1, T2, T3, R> definition3) {
        this.definition3 = definition3;
    }

    static <T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R>
    @NotNull DefinedDiVisitor3<T, T1, T2, T3, R> build(@NotNull NF1<DiDefinition3.BuilderT11<T, T1, T2, T3, R>, DiDefinition3<T, T1, T2, T3, R>> buildProcess) {
        return new DefinedDiVisitor3<>(DiDefinition3.build(buildProcess));
    }

    @Override
    protected DiDefinition3<T, T1, T2, T3, R> diDefinition3() {
        return definition3;
    }
}
