package raystark.eflib.function.notnull;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.A;

import java.util.function.Supplier;

/**
 * 型Tの値のSupplierです。
 *
 * <p>実行のたびに新たな結果が返される必要がありません。
 *
 * <p>このインターフェースは{@link NS#get}を関数メソッドに持つ関数型インターフェースです。
 * <p>この関数はnullを扱いません。
 *
 * <p>このインターフェースは{@link Supplier}に対応します。{@link Supplier}に変換する場合次のイディオムが使えます。
 *
 * <pre>{@code
 *  NS<T> s1 = SomeClass1::someMethod;
 *  Supplier<T> s2 = s1::get;
 * }</pre>
 *
 * <p>このSupplierには合成Supplierを作成するメソッド、自身の型を変換するメソッド、
 * ラムダやメソッド参照からインスタンスを作成するメソッドが定義されています。
 *
 * @param <T> 供給する値の型
 */
@FunctionalInterface
public interface NS<T> {
    /**
     * 型Tの値を取得します。
     *
     * <p>呼び出しのたびに新たな値が返される必要がありません。
     *
     * @return 値
     */
    @NotNull
    T get();

    /**
     * このSupplierの出力を関数afterに適用する合成Supplierを返します。
     *
     * <p>いずれかの関数の評価時に例外がスローされた場合、その例外は呼び出し元に中継されます。
     *
     * @param after このSupplierの出力が適用される関数
     * @param <R> 合成Supplierの出力の型
     * @return 合成Supplier
     */
    @NotNull
    default <R> NS<R> then(@NotNull NF1<? super T, ? extends R> after) {
        return () -> after.apply(get());
    }

    /**
     * このSupplierの出力をConsumer afterが消費する合成Actionを返します。
     *
     * <p>この関数の評価時に例外がスローされた場合、その例外は呼び出し元に中継されます。
     *
     * @param after このSupplierの出力が適用されるConsumer
     * @return 合成Action
     */
    @NotNull
    default A asA(@NotNull NC1<? super T> after) {
        return () -> after.accept(get());
    }

    /**
     * ラムダやメソッド参照から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数をそのまま返します。
     *
     * <p>このメソッドの呼び出しに対して様々なメソッドをチェインできます。
     *
     * @param s ラムダやメソッド参照で記述されたSupplier
     * @param <T> Supplierが供給する値の型
     * @return 引数に渡されたSupplier
     */
    @NotNull
    static <T> NS<T> of(@NotNull NS<T> s) {
        return s;
    }
}