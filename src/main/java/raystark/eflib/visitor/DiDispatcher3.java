package raystark.eflib.visitor;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.visitor.acceptor.Acceptor3;
import raystark.eflib.visitor.definition.DiDefinition3;

public class DiDispatcher3<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> extends AbstractDiDispatcher3<T, T1, T2, T3, R> {
    private final DiDefinition3<T, T1, T2, T3, R> definition3;

    private DiDispatcher3(DiDefinition3<T, T1, T2, T3, R> definition3) {
        this.definition3 = definition3;
    }

    public static <T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R>
    @NotNull DiDispatcher3<T, T1, T2, T3, R> build(@NotNull NF1<DiDefinition3.BuilderT11<T, T1, T2, T3, R>, DiDefinition3<T, T1, T2, T3, R>> buildProcess) {
        return new DiDispatcher3<>(DiDefinition3.build(buildProcess));
    }

    @Override
    protected DiDefinition3<T, T1, T2, T3, R> diDefinition() {
        return definition3;
    }
}
