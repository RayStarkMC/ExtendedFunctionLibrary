package raystark.eflib.visitor;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.visitor.acceptor.Acceptor3;
import raystark.eflib.visitor.definition.IDiDefinition3;

public abstract class AbstractDiDispatcher3<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> implements IDiDispatcher3<T, T1, T2, T3, R> {
    private final IMonoDispatcher3<T, T1, T2, T3, IMonoDispatcher3<T, T1, T2, T3, R>> visitor3;

    protected AbstractDiDispatcher3() {
        visitor3 = MonoDispatcher3.build(builder1 -> builder1
            .type1(arg1 -> MonoDispatcher3.build(builder2 -> builder2
                .type1(arg2 -> diDefinition().dispatch(() -> arg1, () -> arg2))
                .type2(arg2 -> diDefinition().dispatch(() -> arg1, () -> arg2))
                .type3(arg2 -> diDefinition().dispatch(() -> arg1, () -> arg2))))
            .type2(arg1 -> MonoDispatcher3.build(builder2 -> builder2
                .type1(arg2 -> diDefinition().dispatch(() -> arg1, () -> arg2))
                .type2(arg2 -> diDefinition().dispatch(() -> arg1, () -> arg2))
                .type3(arg2 -> diDefinition().dispatch(() -> arg1, () -> arg2))))
            .type3(arg1 -> MonoDispatcher3.build(builder2 -> builder2
                .type1(arg2 -> diDefinition().dispatch(() -> arg1, () -> arg2))
                .type2(arg2 -> diDefinition().dispatch(() -> arg1, () -> arg2))
                .type3(arg2 -> diDefinition().dispatch(() -> arg1, () -> arg2))))
        );
    }

    @Override
    public final @NotNull R apply(@NotNull T arg1, @NotNull T arg2) {
        return IDiDispatcher3.super.apply(arg1, arg2);
    }

    @Override
    public final @NotNull IMonoDispatcher3<T, T1, T2, T3, R> apply(@NotNull T arg1) {
        return visitor3.apply(arg1);
    }

    protected abstract IDiDefinition3<T, T1, T2, T3, R> diDefinition();
}
