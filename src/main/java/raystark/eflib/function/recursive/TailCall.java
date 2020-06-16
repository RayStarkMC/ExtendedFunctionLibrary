package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    @Nullable
    default T get() {
        for(TailCall<T> tailCall = this; ; tailCall = tailCall.next())
            if(isCompleted(tailCall)) return tailCall.get();
    }

    @FunctionalInterface
    interface Completed<T> extends TailCall<T> {

        @Override
        @NotNull
        default TailCall<T> next() {
            return this;
        }

        @Override
        @Nullable
        T get();
    }

    static boolean isCompleted(@NotNull TailCall<?> tailCall) {
        return tailCall instanceof Completed;
    }

    @NotNull
    static <T> TailCall<T> call(@NotNull RS.TailCallS<T> tailCall) {
        return tailCall::get;
    }

    @NotNull
    static <T> Completed<T> complete(@Nullable T value) {
        return () -> value;
    }

    @NotNull
    static Completed<Void> complete() {
        return VOID_COMPLETED;
    }
}
