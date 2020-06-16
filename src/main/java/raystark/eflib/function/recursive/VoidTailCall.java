package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.recursive.RA.TailCallA;

import static raystark.eflib.function.recursive.TailCallHelper.isCompleted;

@FunctionalInterface
public interface VoidTailCall {
    /**
     * このVoidTailCallの次に実行されるVoidTailCallを返します。
     *
     * @return 次に実行されるVoidTailCall
     */
    @NotNull
    VoidTailCall next();

    /**
     * このVoidTailCallを実行します。
     *
     * <p>メソッドの再起呼び出しはループに変換されます。
     * 再帰の終了条件が満たされない場合、このメソッドは無限ループに陥る可能性があります。
     *
     * <p>いずれかの関数の評価時にスローされた例外は呼び出し元に中継されます。
     */
    default void execute() {
        for(VoidTailCall tailCall = this; ; tailCall = tailCall.next())
            if(isCompleted(tailCall)) return;
    }

    /**
     * 再帰的にメソッドを呼び出すVoidTailCallを実装します。
     *
     * <p>引数のactionの中で関数を再帰的に呼び出してください。
     *
     * @param action 次に呼び出されるVoidTailCallのAction
     * @return このVoidTailCallの次に呼び出されるVoidTailCall
     */
    @NotNull
    static VoidTailCall call(@NotNull TailCallA action) {
        return action::run;
    }

    /**
     * 末尾再帰関数が終了したことを表すVoidTailCallを返します。
     *
     * @return 再帰が完了したVoidTailCall
     */
    @NotNull
    static VoidTailCall complete() {
        return TailCallHelper.complete();
    }
}
