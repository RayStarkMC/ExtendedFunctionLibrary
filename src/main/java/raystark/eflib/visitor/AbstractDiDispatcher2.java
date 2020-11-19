package raystark.eflib.visitor;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF2;
import raystark.eflib.visitor.acceptor.Acceptor2;
import raystark.eflib.visitor.definition.IDiDefinition2;

public abstract class AbstractDiDispatcher2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> implements IDiDispatcher2<T, T1, T2, R> {
    private final IMonoDispatcher2<T, T1, T2, IMonoDispatcher2<T, T1, T2, R>> visitor2;

    protected AbstractDiDispatcher2() {
        this.visitor2 = MonoDispatcher2.build(builder1 -> builder1
            .type1(arg1 -> MonoDispatcher2.build(builder2 -> builder2
                .type1(arg2 -> definition().dispatch(() -> arg1, () -> arg2))
                .type2(arg2 -> definition().dispatch(() -> arg1, () -> arg2))))
            .type2(arg1 -> MonoDispatcher2.build(builder2 -> builder2
                .type1(arg2 -> definition().dispatch(() -> arg1, () -> arg2))
                .type2(arg2 -> definition().dispatch(() -> arg1, () -> arg2))))
        );
    }

    @Override
    public final @NotNull R apply(@NotNull T arg1, @NotNull T arg2) {
        return IDiDispatcher2.super.apply(arg1, arg2);
    }

    @Override
    public final @NotNull NF2<T, T, R> asF2() {
        return IDiDispatcher2.super.asF2();
    }

    @Override
    public final @NotNull IMonoDispatcher2<T, T1, T2, R> apply(@NotNull T arg1) {
        return visitor2.apply(arg1);
    }

    protected abstract @NotNull IDiDefinition2<T, T1, T2, R> definition();
}
