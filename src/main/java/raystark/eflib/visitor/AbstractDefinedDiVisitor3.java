package raystark.eflib.visitor;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.visitor.acceptor.Acceptor3;
import raystark.eflib.visitor.definition.IDiDefinition3;

public abstract class AbstractDefinedDiVisitor3<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> implements IDefinedDiVisitor3<T, T1, T2, T3, R> {
    private final IDefinedVisitor3<T, T1, T2, T3, IDefinedVisitor3<T, T1, T2, T3, R>> visitor3;

    protected AbstractDefinedDiVisitor3() {
        visitor3 = DefinedVisitor3.build(builder1 -> builder1
            .type1(arg1 -> DefinedVisitor3.build(builder2 -> builder2
                .type1(arg2 -> diDefinition3().dispatch(() -> arg1, () -> arg2))
                .type2(arg2 -> diDefinition3().dispatch(() -> arg1, () -> arg2))
                .type3(arg2 -> diDefinition3().dispatch(() -> arg1, () -> arg2))))
            .type2(arg1 -> DefinedVisitor3.build(builder2 -> builder2
                .type1(arg2 -> diDefinition3().dispatch(() -> arg1, () -> arg2))
                .type2(arg2 -> diDefinition3().dispatch(() -> arg1, () -> arg2))
                .type3(arg2 -> diDefinition3().dispatch(() -> arg1, () -> arg2))))
            .type3(arg1 -> DefinedVisitor3.build(builder2 -> builder2
                .type1(arg2 -> diDefinition3().dispatch(() -> arg1, () -> arg2))
                .type2(arg2 -> diDefinition3().dispatch(() -> arg1, () -> arg2))
                .type3(arg2 -> diDefinition3().dispatch(() -> arg1, () -> arg2))))
        );
    }

    @Override
    public final @NotNull R apply(@NotNull T arg1, @NotNull T arg2) {
        return IDefinedDiVisitor3.super.apply(arg1, arg2);
    }

    @Override
    public final @NotNull IDefinedVisitor3<T, T1, T2, T3, R> apply(@NotNull T arg1) {
        return visitor3.apply(arg1);
    }

    protected abstract IDiDefinition3<T, T1, T2, T3, R> diDefinition3();
}
