package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NNS;

import java.util.stream.Stream;

import static raystark.eflib.function.recursive.TailCallUtil.VOID_COMPLETED;

/**
 * 末尾再帰関数を最適化するためのユーティリティインターフェースです。
 *
 * @param <T>
 */
@FunctionalInterface
public interface TailCall<T> {
    @NotNull
    TailCall<T> next();

    default T get() {
        return Stream.iterate(this, TailCall::next)
                .filter(TailCall::isCompleted)
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .get();
    }

    @FunctionalInterface
    interface Completed<T> extends TailCall<T> {

        @Override
        @NotNull
        default TailCall<T> next() {
            return this;
        }

        @Override
        T get();
    }

    static boolean isCompleted(TailCall<?> tailCall) {
        return tailCall instanceof Completed;
    }

    @NotNull
    static <T> TailCall<T> call(@NotNull NNS<TailCall<T>> tailCall) {
        return tailCall::get;
    }

    @NotNull
    static <T> Completed<T> complete(T value) {
        return () -> value;
    }

    @NotNull
    static Completed<Void> complete() {
        return VOID_COMPLETED;
    }
}
