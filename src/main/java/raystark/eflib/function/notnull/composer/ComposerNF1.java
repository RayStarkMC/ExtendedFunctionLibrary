package raystark.eflib.function.notnull.composer;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.function.notnull.NS;
import raystark.eflib.util.Chain;
import raystark.eflib.util.OneShotState;

public final class ComposerNF1<T1, R> {
    private final Chain<NF1<?, ?>> functions;
    private final OneShotState state;

    private static final NS<IllegalStateException> EXCEPTION_SUPPLIER = () -> new IllegalStateException("This composer has already been operated.");

    private ComposerNF1(NF1<? super T1, ? extends R> init) {
        var functions = new Chain<NF1<?, ?>>();
        functions.addFirst(init);
        this.functions = functions;
        this.state = OneShotState.newInstance();
    }

    private ComposerNF1(Chain<NF1<?, ?>> functions) {
        this.functions = functions;
        this.state = OneShotState.newInstance();
    }

    public <V> @NotNull ComposerNF1<T1, V> then(@NotNull NF1<? super R, ? extends V> after) {
        return state.transitionThrowing(
            () -> { functions.addLast(after); return new ComposerNF1<>(functions); },
            EXCEPTION_SUPPLIER
        );
    }

    public <V1> @NotNull ComposerNF1<V1, R> compose1(@NotNull NF1<? super V1, ? extends T1> before) {
        return state.transitionThrowing(
            () -> { functions.addFirst(before); return new ComposerNF1<>(functions); },
            EXCEPTION_SUPPLIER
        );
    }

    public @NotNull NF1<T1, R> compound() {
        return state.transitionThrowing(
            () -> new CompoundNF1<>(functions),
            EXCEPTION_SUPPLIER
        );
    }

    public static <T1, R> @NotNull ComposerNF1<T1, R> of(@NotNull NF1<? super T1, ? extends R> nf1) {
        return new ComposerNF1<>(nf1);
    }

    public static <T1, R, V>
    @NotNull ComposerNF1<T1, V> concat(ComposerNF1<? super T1, ? extends R> left, ComposerNF1<? super R, ? extends V> right) {
        var lefts = left.state.transitionThrowing(
            () -> left.functions,
            EXCEPTION_SUPPLIER
        );

        var rights = right.state.transitionThrowing(
            () -> right.functions,
            EXCEPTION_SUPPLIER
        );
        return new ComposerNF1<>(Chain.concat(lefts, rights));
    }
}
