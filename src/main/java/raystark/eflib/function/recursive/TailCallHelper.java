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
     */
    static final VoidCompleted VOID_COMPLETED = new VoidCompleted();

    /**
     * 評価値trueで再帰が完了したことを表すBooleanTailCallです。
     */
    static final BooleanCompleted TRUE_COMPLETED = () -> true;

    /**
     * 評価値falseで再帰が完了したことを表すBooleanTailCallです。
     */
    static final BooleanCompleted FALSE_COMPLETED = () -> false;

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
        return tailCall instanceof VoidCompleted;
    }

    /**
     * 与えられたTailCallが修了している場合trueを返します。
     *
     * @param tailCall 修了しているか検証するTailCall
     * @return TailCallが終了している場合true
     */
    static boolean isCompleted(@NotNull BooleanTailCall tailCall) {
        return tailCall instanceof BooleanCompleted;
    }

    /**
     * 再帰呼び出しが完了したTailCallを表すインターフェースです。
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
         * このTailCallの評価値を取得します。
         *
         * @return 末尾再帰関数の評価値
         */
        @Override
        @Nullable
        T evaluate();
    }

    /**
     * 再帰呼び出しが完了したVoidTailCallを表すインターフェースです。
     *
     * <p>{@link VoidCompleted#next}メソッドは自分自身を返し、{@link VoidCompleted#execute}メソッドは何も行いません。
     */
    static final class VoidCompleted implements VoidTailCall {

        /**
         * このTailCallの次に評価されるTailCallを返します。
         *
         * <p>再帰が完了し、次に呼び出すべきTailCallが存在しないため、このメソッドは自身の参照を返します。
         *
         * @return 自身の参照
         */
        @Override
        @NotNull
        public VoidTailCall next() {
            return this;
        }

        /**
         * このTailCallを実行します。
         *
         * <p>再帰が完了しているため、このメソッドは何も行いません。
         */
        @Override
        public void execute() {}
    }

    /**
     * 再帰呼び出しが完了したBooleanTailCallを表すインターフェースです。
     *
     * <p>{@link BooleanCompleted#next}メソッドは自分自身を返し、{@link BooleanCompleted#evaluate()}メソッドは評価値を返します。
     */
    @FunctionalInterface
    interface BooleanCompleted extends BooleanTailCall {

        /**
         * このTailCallの次に評価されるTailCallを返します。
         *
         * <p>再帰が完了し、次に呼び出すべきTailCallが存在しないため、このメソッドは自身の参照を返します。
         *
         * @return 自身の参照
         */
        @Override
        @NotNull
        default BooleanTailCall next() {
            return this;
        }

        /**
         * このTailCallの評価値を取得します。
         *
         * @return 末尾再帰関数の評価値
         */
        @Override
        boolean evaluate();
    }
}