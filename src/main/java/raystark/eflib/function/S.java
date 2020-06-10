package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * 型Tの値の供給元です。
 *
 * <p>実行のたびに新たな結果が返される必要がありません。
 *
 * <p>このインターフェースは{@link S#get}を関数メソッドに持つ関数型インターフェースです。
 *
 * <p>このインターフェースは{@link Supplier}に対応します。{@link Supplier}に変換する場合次のイディオムが使えます。
 *
 * <pre>{@code
 *  S<T> s1 = SomeClass1::someMethod;
 *  Supplier<T> s2 = s1::get;
 * }</pre>
 * @param <T> 供給する値の型
 */
@FunctionalInterface
public interface S<T> {
    /**
     * 型Tの値を取得します。
     *
     * <p>呼び出しのたびに新たな値が返される必要がありません。
     *
     * @return 値
     */
    T get();

    /**
     * このサプライヤの出力を関数afterに適用する合成サプライヤを返します。
     *
     * <p>いずれかの関数の評価時に例外がスローされた場合、その例外は呼び出し元に中継されます。
     *
     * @param after このサプライヤの出力が適用される関数
     * @param <R> 合成サプライヤの出力の型
     * @return 合成サプライヤ
     */
    @NotNull
    default <R> S<R> then(@NotNull F1<? super T, ? extends R> after) {
        return () -> after.apply(get());
    }

    /**
     * このサプライヤの出力をConsumer afterが消費するActionを返します。
     *
     * <p>この関数の評価時に例外がスローされた場合、その例外は呼び出し元に中継されます。
     *
     * @param after このサプライヤの出力が適用されるConsumer
     * @return Action
     */
    @NotNull
    default A asA(@NotNull C1<? super T> after) {
        return () -> after.accept(get());
    }

    /**
     * ラムダやメソッド参照から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数をそのまま返します。
     *
     * @param s ラムダやメソッド参照で記述されたSupplier
     * @param <T> Supplierが供給する値の型
     * @return 引数に渡されたSupplier
     */
    @NotNull
    static <T> S<T> of(@NotNull S<T> s) {
        return s;
    }
}
