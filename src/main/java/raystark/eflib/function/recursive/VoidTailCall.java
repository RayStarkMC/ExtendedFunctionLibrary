package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;

import static raystark.eflib.function.recursive.TailCallHelper.VOID_COMPLETED;
import static raystark.eflib.function.recursive.TailCallHelper.isCompleted;

/**
 * 引数を返さない末尾再帰呼び出しを表すインターフェースです。
 *
 * <p>TailCallと同様に{@link VoidTailCall#call}と{@link VoidTailCall#complete}を使うことで末尾再帰関数を最適化出来ます。
 *
 * @see TailCall
 */
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
     * <p>引数のsupplierの中で関数を再帰的に呼び出してください。
     *
     * @param supplier 次に呼び出されるVoidTailCallのSupplier
     * @return このVoidTailCallの次に呼び出されるVoidTailCall
     */
    @NotNull
    static VoidTailCall call(@NotNull VoidTailCallS supplier) {
        return supplier::get;
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

    @FunctionalInterface
    interface VoidTailCallS {
        @NotNull
        VoidTailCall get();
    }
}
