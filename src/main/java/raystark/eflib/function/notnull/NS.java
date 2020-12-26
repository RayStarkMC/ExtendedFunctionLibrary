package raystark.eflib.function.notnull;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.A;
import raystark.eflib.lazy.MLazy;
import raystark.eflib.lazy.SLazy;
import raystark.eflib.option.Option;
import raystark.eflib.option.Option.Some;

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
     * このSupplierの供給する値をOptionでラップするSupplierを返します。
     *
     * @return OptionにリフトされたSupplier
     */
    @NotNull
    default NS<Option<T>> liftOption() {
        return liftOption(this);
    }

    /**
     * このSupplierの供給する値をSLazyでラップするSupplierを返します。
     *
     * @return SLazyにリフトされたSupplier
     */
    @NotNull
    default NS<SLazy<T>> liftSLazy() {
        return liftSLazy(this);
    }

    /**
     * このSupplierの供給する値をMLazyでラップするSupplierを返します。
     *
     * @return MLazyにリフトされたSupplier
     */
    @NotNull
    default NS<MLazy<T>> liftMLazy() {
        return liftMLazy(this);
    }

    /**
     * supplierの供給する値をOptionでラップするSupplierを返します。
     *
     * @param supplier サプライヤ
     * @param <T> サプライヤの値の型
     * @return OptionにリフトされたSupplier
     */
    @NotNull
    static <T> NS<Option<T>> liftOption(@NotNull NS<? extends T> supplier) {
        return () -> Some.of(supplier.get());
    }

    /**
     * supplierの供給する値をSLazyでラップするSupplierを返します。
     *
     * @param supplier サプライヤ
     * @param <T> サプライヤの値の型
     * @return SLazyにリフトされたSupplier
     */
    @NotNull
    static <T> NS<SLazy<T>> liftSLazy(@NotNull NS<? extends T> supplier) {
        return () -> SLazy.of(supplier);
    }

    /**
     * supplierの供給する値をMLazyでラップするSupplierを返します。
     *
     * @param supplier サプライヤ
     * @param <T> サプライヤの値の型
     * @return MLazyにリフトされたSupplier
     */
    @NotNull
    static <T> NS<MLazy<T>> liftMLazy(@NotNull NS<? extends T> supplier) {
        return () -> MLazy.of(supplier);
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

    /**
     * 型変数の変性を表すキャストメソッド。
     *
     * @param s キャスト対象
     * @param <T> キャスト後のT型
     * @return キャスト対象の参照
     */
    @SuppressWarnings("unchecked")
    @NotNull
    static <T> NS<T> cast(@NotNull NS<? extends T> s) {
        return (NS<T>) s;
    }
}