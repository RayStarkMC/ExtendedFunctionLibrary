package raystark.eflib.visitor.di;


import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.visitor.acceptor.Acceptor1;
import raystark.eflib.visitor.definition.di.DiDefinition1;
import raystark.eflib.visitor.definition.mono.MonoDefinition1;
import raystark.eflib.visitor.mono.IMonoVisitor1;

public interface DiVisitor1<T extends Acceptor1<T, T1>, T1 extends T, R> extends IMonoVisitor1<T, T1, IMonoVisitor1<T, T1, R>> {
    default @NotNull R apply(@NotNull T arg1, @NotNull T arg2) {
        return apply(arg1).apply(arg2);
    }

    @Override
    default @NotNull MonoDefinition1<T, T1, IMonoVisitor1<T, T1, R>> monoDefinition1() {
        return MonoDefinition1.build(builder1 -> builder1
            .type1(arg1 -> IMonoVisitor1.build(builder2 -> builder2
                .type1(arg2 -> diDefinition1().dispatch(() -> arg1, () -> arg2)))));
    }

    @NotNull DiDefinition1<T, T1, R> diDefinition1();

    static <T extends Acceptor1<T, T1>, T1 extends T, R>
    @NotNull DiVisitor1<T, T1, R> of(@NotNull DiDefinition1<T, T1, R> definition) {
        return () -> definition;
    }

    static<T extends Acceptor1<T, T1>, T1 extends T, R>
    @NotNull DiVisitor1<T, T1, R> build(@NotNull NF1<DiDefinition1.BuilderT11<T, T1, R>, DiDefinition1<T, T1, R>> builder) {
        return of(builder.apply(DiDefinition1.builder()));
    }
}
