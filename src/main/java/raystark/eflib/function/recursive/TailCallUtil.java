package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;

class TailCallUtil {
    static final TailCall.Completed<?> VOID_COMPLETED = () -> null;

    /**
     * 与えられたTailCallが終了している場合trueを返します。
     *
     * @param tailCall 修了しているか検証するTailCall
     * @return TailCallが終了している場合true
     */
    static boolean isCompleted(@NotNull TailCall<?> tailCall) {
        return tailCall instanceof TailCall.Completed;
    }
}
