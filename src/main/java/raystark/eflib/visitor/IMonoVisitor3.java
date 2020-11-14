package raystark.eflib.visitor;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.visitor.acceptor.Acceptor3;
import raystark.eflib.visitor.definition.MonoDefinition3;

public interface IMonoVisitor3<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
    default @NotNull R apply(@NotNull T arg1) {
        return arg1.accept(monoDefinition3());
    }
    MonoDefinition3<T, T1, T2, T3, R> monoDefinition3();

    static <T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R>
    @NotNull IMonoVisitor3<T, T1, T2, T3, R> of(@NotNull MonoDefinition3<T, T1, T2, T3, R> definition) {
        return () -> definition;
    }

    static <T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R>
    @NotNull IMonoVisitor3<T, T1, T2, T3, R> build(@NotNull NF1<MonoDefinition3.BuilderT1<T, T1, T2, T3, R>, MonoDefinition3<T, T1, T2, T3, R>> builder) {
        return of(builder.apply(MonoDefinition3.builder()));
    }
}
