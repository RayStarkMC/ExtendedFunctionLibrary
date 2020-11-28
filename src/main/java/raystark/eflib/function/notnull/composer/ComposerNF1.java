package raystark.eflib.function.notnull.composer;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.util.OneShotState;

import java.util.Deque;
import java.util.LinkedList;

public final class ComposerNF1<T1, R> {
    private final Deque<NF1<?, ?>> functions;
    private final OneShotState state;

    private ComposerNF1(NF1<? super T1, ? extends R> init) {
        var functions = new LinkedList<NF1<?, ?>>();
        functions.add(init);
        this.functions = functions;
        this.state = OneShotState.newInstance();
    }

    private ComposerNF1(Deque<NF1<?, ?>> functions) {
        this.functions = functions;
        this.state = OneShotState.newInstance();
    }

    public <V> @NotNull ComposerNF1<T1, V> then(@NotNull NF1<? super R, ? extends V> after) {
        return state.transitionThrowing(
            () -> { functions.offerLast(after); return new ComposerNF1<>(functions); },
            () -> new IllegalStateException("This composer has already been operated.")
        );
    }

    public <V1> @NotNull ComposerNF1<V1, R> compose1(@NotNull NF1<? super V1, ? extends T1> before) {
        return state.transitionThrowing(
            () -> { functions.offerFirst(before); return new ComposerNF1<>(functions); },
            () -> new IllegalStateException("This composer has already been operated.")
        );
    }

    public @NotNull NF1<T1, R> compound() {
        return state.transitionThrowing(
            () -> new CompoundNF1<>(functions),
            () -> new IllegalStateException("This composer has already been operated.")
        );
    }

    public static <T1, R> @NotNull ComposerNF1<T1, R> of(@NotNull NF1<? super T1, ? extends R> nf1) {
        return new ComposerNF1<>(nf1);
    }

    public static <T1, R, V>
    @NotNull ComposerNF1<T1, V> concat(ComposerNF1<? super T1, ? extends R> left, ComposerNF1<? super R, ? extends V> right) {
        var lefts = left.state.transitionThrowing(
            () -> left.functions,
            () -> new IllegalStateException("This composer has already been operated.")
        );

        var rights = right.state.transitionThrowing(
            () -> right.functions,
            () -> new IllegalStateException("This composer has already been operated.")
        );
        lefts.addAll(rights);
        return new ComposerNF1<>(lefts);
    }

    public static class Mutable<T> {
        private ComposerNF1<T, T> composer;

        private Mutable(ComposerNF1<T, T> composer) {
            this.composer = composer;
        }

        public void then(@NotNull NF1<? super T, ? extends T> after) {
            composer = composer.then(after);
        }

        public void compose1(@NotNull NF1<? super T, ? extends T> before) {
            composer = composer.compose1(before);
        }

        public @NotNull NF1<T, T> compound() {
            return composer.compound();
        }

        public static <T> Mutable<T> newInstance(ComposerNF1<T, T> composer) {
            return new Mutable<>(composer);
        }

        public static <T> Mutable<T> concat(Mutable<T> t1, Mutable<T> t2) {
            return newInstance(ComposerNF1.concat(t1.composer, t2.composer));
        }
    }
}
