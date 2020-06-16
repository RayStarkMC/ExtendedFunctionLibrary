package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * TailCall及びVoidTailCallのHelperクラスです。
 */
final class TailCallHelper {
    private TailCallHelper() {
        throw new AssertionError();
    }

    /**
     * 再帰が完了したことを表すVoidTailCallです。
     *
     * <p>このインスタンスのnextメソッドは自身を返し、executeメソッドは何も行いません。
     */
    private static final VoidTailCall VOID_COMPLETED = new VoidTailCall() {
        @Override
        public @NotNull VoidTailCall next() {
            return this;
        }

        @Override
        public void execute() {}
    };

    /**
     * 与えられたTailCallが終了している場合trueを返します。
     *
     * @param tailCall 修了しているか検証するTailCall
     * @return TailCallが終了している場合true
     */
    static boolean isCompleted(@NotNull TailCall<?> tailCall) {
        return tailCall instanceof Completed;
    }

    /**
     * 与えられたTailCallが修了している場合trueを返します。
     *
     * @param tailCall 修了しているか検証するTailCall
     * @return TailCallが終了している場合true
     */
    static boolean isCompleted(@NotNull VoidTailCall tailCall) {
        return tailCall == VOID_COMPLETED;
    }

    /**
     * 末尾再帰関数の評価値を返すTailCallを実装します。
     *
     * @param value 末尾再帰関数の戻り値
     * @param <T> 末尾再帰関数の戻り値の型
     * @return 再帰が完了したTailCall
     */
    @NotNull
    static <T> Completed<T> complete(@Nullable T value) {
        return () -> value;
    }

    /**
     * 末尾再帰関数が終了したことを表すVoidTailCallを返します。
     *
     * @return 再帰が完了したVoidTailCall
     */
    @NotNull
    static VoidTailCall complete() {
        return VOID_COMPLETED;
    }

    /**
     * 再帰呼び出しが修了したTailCallを表すインターフェースです。
     *
     * <p>{@link Completed#next}メソッドは自分自身を返し、{@link Completed#evaluate}メソッドは値を返します。
     *
     * @param <T> 末尾再帰関数の戻り値の型
     */
    @FunctionalInterface
    interface Completed<T> extends TailCall<T> {

        /**
         * このTailCallの次に評価されるTailCallを返します。
         *
         * <p>再帰が完了し、次に呼び出すべきTailCallが存在しないため、このメソッドは自身の参照を返します。
         *
         * @return 自身の参照
         */
        @Override
        @NotNull
        default TailCall<T> next() {
            return this;
        }

        /**
         * このTailCallの値を取得します。
         *
         * @return 末尾再帰関数の戻り値
         */
        @Override
        @Nullable
        T evaluate();
    }
}
