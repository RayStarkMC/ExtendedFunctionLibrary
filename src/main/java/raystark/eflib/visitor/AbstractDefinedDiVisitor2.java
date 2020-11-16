package raystark.eflib.visitor;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.visitor.acceptor.Acceptor2;
import raystark.eflib.visitor.definition.IDiDefinition2;

public abstract class AbstractDefinedDiVisitor2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> implements IDefinedDiVisitor2<T, T1, T2, R> {
    private final IDefinedVisitor2<T, T1, T2, IDefinedVisitor2<T, T1, T2, R>> visitor2;

    protected AbstractDefinedDiVisitor2() {
        this.visitor2 = DefinedVisitor2.build(builder1 -> builder1
            .type1(arg1 -> DefinedVisitor2.build(builder2 -> builder2
                .type1(arg2 -> diDefinition2().dispatch(() -> arg1, () -> arg2))
                .type2(arg2 -> diDefinition2().dispatch(() -> arg1, () -> arg2))))
            .type2(arg1 -> DefinedVisitor2.build(builder2 -> builder2
                .type1(arg2 -> diDefinition2().dispatch(() -> arg1, () -> arg2))
                .type2(arg2 -> diDefinition2().dispatch(() -> arg1, () -> arg2))))
        );
    }

    @Override
    public final @NotNull R apply(@NotNull T arg1, @NotNull T arg2) {
        return apply(arg1).apply(arg2);
    }

    @Override
    public final @NotNull IDefinedVisitor2<T, T1, T2, R> apply(@NotNull T arg1) {
        return visitor2.apply(arg1);
    }

    protected abstract @NotNull IDiDefinition2<T, T1, T2, R> diDefinition2();
}
