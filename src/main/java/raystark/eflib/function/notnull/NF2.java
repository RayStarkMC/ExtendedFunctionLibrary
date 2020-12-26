package raystark.eflib.function.notnull;

import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

/**
 * 型T1, 型T2から型Rへの二変数関数です。
 *
 * <p>このインターフェースは{@link NF2#apply}を関数メソッドに持つ関数型インターフェースです。
 * <p>この関数はnullを扱いません。
 *
 * <p>このインターフェースは{@link BiFunction}に対応します。{@link BiFunction}に変換する場合次のイディオムが使えます。
 *
 * <pre>{@code
 *  NF2<T1, T2, R> f2 = (t1, t2) -> SomeClass::someMethod;
 *  BiFunction<T1, T2, R> f2 = f::apply;
 * }</pre>
 *
 * <p>この関数には部分適用を行うメソッド、合成関数を作成するメソッド、引数の順序を変えるメソッド、
 * 自身を他の関数に変換するメソッド、ラムダやメソッド参照からインスタンスを作成するメソッドが定義されています。
 *
 * @param <T1> 第一引数の型
 * @param <T2> 第二引数の型
 * @param <R> 戻り値の型
 */
@FunctionalInterface
public interface NF2<T1, T2, R> extends NF1<T1, NF1<T2, R>> {
    /**
     * 二変数関数として、引数をこの関数に適用します。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @return 適用結果
     */
    @NotNull
    R apply(@NotNull T1 t1, @NotNull T2 t2);

    /**
     * 第一引数までをこの関数に部分適用します。
     *
     * @param t1 第一引数
     * @return 引数が部分適用された関数
     */
    @NotNull
    @Override
    default NF1<T2, R> apply(@NotNull T1 t1) {
        return t2 -> apply(t1, t2);
    }

    /**
     * 第一引数までをこの関数に部分適用します。
     *
     * <p>引数は遅延評価されます。
     *
     * @param t1 第一引数
     * @return 引数が部分適用された関数
     */
    @NotNull
    default NF1<T2, R> apply(@NotNull NS<? extends T1> t1) {
        return t2 -> apply(t1.get(), t2);
    }

    /**
     * 入力をこの関数を適用し、二変数関数としての結果を関数afterに適用する合成関数を返します。
     *
     * <p>いずれかの関数の評価時にスローされた例外は呼び出し元に中継されます。
     *
     * @param after この関数が適用された後に適用される関数
     * @param <V> 合成関数の出力の型
     * @return 合成関数
     */
    @NotNull
    default <V> NF2<T1, T2, V> then2(@NotNull NF1<? super R, ? extends V> after) {
        return (t1, t2) -> after.apply(apply(t1, t2));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default <V1> NF2<V1, T2, R> compose1(@NotNull NF1<? super V1, ? extends T1> before) {
        return (v1, t2) -> apply(before.apply(v1), t2);
    }

    /**
     * 第二引数への入力を関数beforeに適用し、その結果をこの関数の第二引数に適用する合成関数を返します。
     *
     * <p>いずれかの関数の評価時にスローされた例外は呼び出し元に中継されます。
     *
     * @param before この関数に適用される前に第二引数に適用される関数
     * @param <V2>　合成関数の第二引数の型
     * @return 合成関数
     */
    @NotNull
    default <V2> NF2<T1, V2, R> compose2(@NotNull NF1<? super V2, ? extends T2> before) {
        return (t1, v2) -> apply(t1, before.apply(v2));
    }

    /**
     * この関数の第一引数と第二引数を入れ替えた関数を返します。
     *
     * @return 第一引数と第二引数を入れ替えた関数
     */
    @NotNull
    default NF2<T2, T1, R> swap2() {
        return (t2, t1) -> apply(t1, t2);
    }

    /**
     * 入力をこの関数に適用し、二変数関数としての結果を述語afterに適用する合成述語を返します。
     *
     * <p>いずれかの関数の評価時にスローされた例外は呼び出し元に中継されます。
     *
     * @param after この関数が適用された後に適用される述語
     * @return 合成述語
     */
    @NotNull
    default NP2<T1, T2> asP2(@NotNull NP1<? super R> after) {
        return (t1, t2) -> after.test(apply(t1, t2));
    }

    /**
     * 引数をこの関数に適用した結果を返すSupplierを返します。
     *
     * <p>この関数の評価時にスローされた例外は呼び出し元に中継されます。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @return Supplier
     */
    @NotNull
    default NS<R> asS(@NotNull T1 t1, @NotNull T2 t2) {
        return () -> apply(t1, t2);
    }

    /**
     * 引数をこの関数に適用した結果を返すSupplierを返します。
     *
     * <p>この関数の評価時にスローされた例外は呼び出し元に中継されます。
     * 引数は遅延評価されます。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @return Supplier
     */
    @NotNull
    default NS<R> asS(@NotNull NS<? extends T1> t1, @NotNull NS<? extends T2> t2) {
        return () -> apply(t1.get(), t2.get());
    }

    /**
     * この関数の二変数関数としての結果をConsumer afterが消費するConsumerを返します。
     *
     * <p>この関数の評価時にスローされた例外は呼び出し元に中継されます。
     *
     * @param after この関数の結果を消費するConsumer
     * @return Consumer
     */
    @NotNull
    default NC2<T1, T2> asC2(@NotNull NC1<? super R> after) {
        return (t1, t2) -> after.accept(apply(t1, t2));
    }

    /**
     * ラムダやメソッド参照から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数をそのまま返します。オーバーロードされているメソッドを参照する場合は次のようにラムダの引数等で明示的に型を指定してください。
     *
     * <pre>{@code
     *  NF2<T1, T2, R> f1 = NF2.of((T1 t1, T2 t2) -> SomeClass1.someMethod(t1, t2)).then1(SomeClass2::someMethod);
     *  NF2<T1, T2, R> f2 = NF2.<T1, T2, SomeType>of(SomeClass1::someMethod).then1(SomeClass2::someMethod);
     * }</pre>
     *
     * <p>このメソッドの呼び出しに対して様々なメソッドをチェインできます。
     *
     * @param f2 ラムダやメソッド参照で記述された関数
     * @param <T1> 第一引数の型
     * @param <T2> 第二引数の型
     * @param <R> 戻り値の型
     * @return 引数に渡された関数
     */
    @NotNull
    static <T1, T2, R> NF2<T1, T2, R> of(@NotNull NF2<T1, T2, R> f2) {
        return f2;
    }

    /**
     * 型変数の変性を表すキャストメソッド。
     *
     * @param f2 キャスト対象
     * @param <T1> キャスト後第一引数の型
     * @param <T2> キャスト後第二引数の型
     * @param <R> キャスト後の戻り値の型
     * @return キャスト対象の参照
     */
    @SuppressWarnings("unchecked")
    @NotNull
    static <T1, T2, R> NF2<T1, T2, R> cast(@NotNull NF2<? super T1, ? super T2, ? extends R> f2) {
        return (NF2<T1, T2, R>) f2;
    }
}