package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;

import static raystark.eflib.function.recursive.TailCallHelper.isCompleted;

public interface BooleanTailCall {

    /**
     * このTailCallの次に評価されるTailCallを返します。
     *
     * @return 次に呼び出されるTailCall
     */
    @NotNull
    BooleanTailCall next();

    /**
     * このTailCallを評価し、値を取得します。
     *
     * <p>メソッドの再起呼び出しはループに変換されます。
     * 再帰の終了条件が満たされない場合、このメソッドは無限ループに陥る可能性があります。
     *
     * <p>いずれかの関数の評価時にスローされた例外は呼び出し元に中継されます。
     *
     * @return 末尾再帰関数の戻り値
     */
    default boolean evaluate() {
        for(BooleanTailCall tailCall = this; ; tailCall = tailCall.next())
            if(isCompleted(tailCall)) return tailCall.evaluate();
    }

    /**
     * 再帰的にメソッドを呼び出すTailCallを実装します。
     *
     * <p>引数のsupplierの中で関数を再帰的に呼び出してください。
     *
     * @param supplier 次に呼び出されるTailCallのSupplier
     * @return このTailCallの次に呼び出されるTailCall
     */
    @NotNull
    static BooleanTailCall call(@NotNull BooleanTailCallS supplier) {
        return supplier::get;
    }

    /**
     * 末尾再帰関数の評価値を返すTailCallを実装します。
     *
     * <p>引数には末尾再帰関数の戻り値を渡してください。
     *
     * @param value 末尾再帰関数の戻り値
     * @return 再帰が完了したTailCall
     */
    @NotNull
    static BooleanTailCall complete(boolean value) {
        return TailCallHelper.complete(value);
    }

    @FunctionalInterface
    interface BooleanTailCallS {
        @NotNull
        BooleanTailCall get();
    }
}
