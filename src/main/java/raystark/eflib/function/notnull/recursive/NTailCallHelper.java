package raystark.eflib.function.notnull.recursive;

import org.jetbrains.annotations.NotNull;

/**
 * NTailCallのHelperクラスです。
 */
final class NTailCallHelper {
    private NTailCallHelper() {
        throw new AssertionError();
    }

    /**
     * 与えられたTailCallが終了している場合trueを返します。
     *
     * @param tailCall 修了しているか検証するTailCall
     * @return TailCallが終了している場合true
     */
    static boolean isCompleted(@NotNull NTailCall<?> tailCall) {
        return tailCall instanceof NTailCallHelper.NCompleted;
    }

    /**
     * 再帰呼び出しが完了したTailCallを表すインターフェースです。
     *
     * <p>{@link NTailCallHelper.NCompleted#next}メソッドは自分自身を返し、{@link NTailCallHelper.NCompleted#evaluate}メソッドは値を返します。
     *
     * @param <T> 末尾再帰関数の戻り値の型
     */
    @FunctionalInterface
    interface NCompleted<T> extends NTailCall<T> {

        /**
         * このTailCallの次に評価されるTailCallを返します。
         *
         * <p>再帰が完了し、次に呼び出すべきTailCallが存在しないため、このメソッドは自身の参照を返します。
         *
         * @return 自身の参照
         */
        @Override
        @NotNull
        default NTailCall<T> next() {
            return this;
        }

        /**
         * このTailCallの評価値を取得します。
         *
         * @return 末尾再帰関数の評価値
         */
        @Override
        @NotNull
        T evaluate();
    }
}