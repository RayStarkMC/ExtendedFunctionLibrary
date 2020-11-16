package raystark.eflib.visitor;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.visitor.acceptor.Acceptor2;
import raystark.eflib.visitor.definition.DiDefinition2;

public final class DefinedDiVisitor2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> extends AbstractDefinedDiVisitor2<T, T1, T2, R> {
    private final DiDefinition2<T, T1, T2, R> diDefinition2;

    protected DefinedDiVisitor2(DiDefinition2<T, T1, T2, R> diDefinition2) {
        this.diDefinition2 = diDefinition2;
    }

    static <T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R>
    @NotNull DefinedDiVisitor2<T, T1, T2, R> build(@NotNull NF1<DiDefinition2.BuilderT11<T, T1, T2, R>, DiDefinition2<T, T1, T2, R>> buildProcess) {
        return new DefinedDiVisitor2<>(DiDefinition2.build(buildProcess));
    }

    @Override
    protected @NotNull DiDefinition2<T, T1, T2, R> diDefinition2() {
        return diDefinition2;
    }
}
