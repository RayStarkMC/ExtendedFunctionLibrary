package raystark.eflib.visitor.mono;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.visitor.acceptor.Acceptor1;
import raystark.eflib.visitor.definition.mono.MonoDefinition1;

public interface IMonoVisitor1<T extends Acceptor1<T, T1>, T1 extends T, R> {
    default @NotNull R apply(@NotNull T arg1) {
        return arg1.accept(monoDefinition1());
    }

    @NotNull MonoDefinition1<T, T1, R> monoDefinition1();

    static <T extends Acceptor1<T, T1>, T1 extends T, R>
    @NotNull IMonoVisitor1<T, T1, R> of(@NotNull MonoDefinition1<T, T1, R> definition) {
        return () -> definition;
    }

    static<T extends Acceptor1<T, T1>, T1 extends T, R>
    @NotNull IMonoVisitor1<T, T1, R> build(@NotNull NF1<MonoDefinition1.BuilderT1<T, T1, R>, MonoDefinition1<T, T1, R>> builder) {
        return of(builder.apply(MonoDefinition1.builder()));
    }
}
