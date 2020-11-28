package raystark.eflib.util;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.A;
import raystark.eflib.function.notnull.NS;
import raystark.eflib.option.Option;
import raystark.eflib.option.Option.None;
import raystark.eflib.option.Option.Some;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 一度限りの状態遷移をするオートマトンを表すインターフェース。
 */
public interface OneShotState {
    default boolean isInit() {
        return !isFinished();
    }

    boolean isFinished();

    @NotNull
    <R> Option<R> transition(@NotNull NS<? extends R> ifInit);

    default void transition(@NotNull A ifInit) {
        transition(() -> ifInit).orElse(() -> {}).run();
    }

    @NotNull
    default <R> R transition(@NotNull NS<? extends R> ifInit, @NotNull NS<? extends R> ifFinished) {
        return this.<R>transition(ifInit).orElse(ifFinished);
    }

    default void transition(@NotNull A ifInit, @NotNull A ifFinished) {
        transition(() -> ifInit, () -> ifFinished).run();
    }

    default <R, X extends Exception>
    @NotNull R transitionThrowing(@NotNull NS<? extends R> ifInit, @NotNull NS<? extends X> throwing) throws X {
        return transition(ifInit).orElseThrow(throwing);
    }

    default <X extends Exception>
    void transitionThrowing(@NotNull A ifInit, @NotNull NS<? extends X> throwing) throws X {
        transition(() -> ifInit).orElseThrow(throwing).run();
    }

    static @NotNull OneShotState newInstance() {
        return new OneShotState() {
            private final AtomicBoolean isFinished = new AtomicBoolean(false);

            @Override
            public boolean isFinished() {
                return isFinished.get();
            }

            @Override
            public @NotNull <R> Option<R> transition(@NotNull NS<? extends R> ifInit) {
                return checkFinished() ? None.of() : Some.of(ifInit.get());
            }

            private boolean checkFinished() {
                return isFinished.getAndSet(true);
            }
        };
    }
}
