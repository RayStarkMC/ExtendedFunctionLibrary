package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;

import static raystark.eflib.function.recursive.TailCallUtil.isCompleted;

@FunctionalInterface
public interface VoidTailCall {
    /**
     * このTailCallの次に実行されるVoidTailCallを返します。
     *
     * @return 次に実行されるVoidTailCall
     */
    @NotNull
    VoidTailCall next();

    /**
     * このTailCallを実行します。
     *
     * <p>メソッドの再起呼び出しはループに変換されます。
     * 再帰の終了条件が満たされない場合、このメソッドは無限ループに陥る可能性があります。
     *
     * <p>いずれかの関数の評価時にスローされた例外は呼び出し元に中継されます。
     */
    default void evaluate() {
        for(VoidTailCall tailCall = this; ; tailCall = tailCall.next())
            if(isCompleted(tailCall)) return;
    }

    /**
     * 再帰的にメソッドを呼び出すTailCallを実装します。
     *
     * <p>引数のactionの中で関数を再帰的に呼び出してください。
     *
     * @param action 次に呼び出されるTailCallのAction
     * @return このTailCallの次に呼び出されるTailCall
     */
    @NotNull
    static VoidTailCall call(@NotNull RA.TailCallA action) {
        return action::run;
    }

    /**
     * 末尾再帰関数が終了したことを表すTailCallを返します。
     *
     * @return 再帰が完了したTailCall
     */
    @NotNull
    static VoidTailCall complete() {
        return TailCallUtil.complete();
    }
}
