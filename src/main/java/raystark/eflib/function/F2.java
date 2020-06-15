package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

/**
 * 型T1, 型T2から型Rへの二変数関数です。
 *
 * <p>このインターフェースは{@link F2#apply}を関数メソッドに持つ関数型インターフェースです。
 *
 * <p>このインターフェースは{@link BiFunction}に対応します。{@link BiFunction}に変換する場合次のイディオムが使えます。
 *
 * <pre>{@code
 *  F2<T1, T2, R> f = (t1, t2) -> SomeClass::someMethod;
 *  BiFunction<T1, T2, R> f2 = f::apply;
 * }</pre>
 *
 * <p>この関数には部分適用を行うメソッド、合成関数を作成するメソッド、引数の順序を変えるメソッド、
 * 他の関数に変換するメソッド、ラムダやメソッド参照からインスタンスを作成するメソッドが定義されています。
 *
 * @param <T1> 第一引数の型
 * @param <T2> 第二引数の型
 * @param <R> 戻り値の型
 */
@FunctionalInterface
public interface F2<T1, T2, R> extends F1<T1, F1<T2, R>> {
    /**
     * 二変数関数として、引数をこの関数に適用します。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @return 適用結果
     */
    R apply(T1 t1, T2 t2);

    /**
     * 第一引数までをこの関数に部分適用します。
     *
     * @param t1 第一引数
     * @return 引数が部分適用された関数。
     */
    @NotNull
    @Override
    default F1<T2, R> apply(T1 t1) {
        return t2 -> apply(t1, t2);
    }

    /**
     * 入力をこの関数を適用し、二変数関数としての結果を関数afterに適用する合成関数を返します。
     *
     * <p>いずれかの関数の評価時に例外がスローされた場合、その例外は呼び出し元に中継されます。
     *
     * @param after この関数が適用された後に適用される関数
     * @param <V> 合成関数の出力の型
     * @return 合成関数
     */
    @NotNull
    default <V> F2<T1, T2, V> then2(@NotNull F1<? super R, ? extends V> after) {
        return (t1, t2) -> after.apply(apply(t1, t2));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default <V1> F2<V1, T2, R> compose1(@NotNull F1<? super V1, ? extends T1> before) {
        return (v1, t2) -> apply(before.apply(v1), t2);
    }

    /**
     * 第二引数への入力を関数beforeに適用し、その結果をこの関数の第二引数に適用する合成関数を返します。
     *
     * <p>いずれかの関数の評価時に例外がスローされた場合、その例外は呼び出し元に中継されます。
     *
     * @param before この関数に適用される前に第二引数に適用される関数
     * @param <V2>　合成関数の第二引数の型
     * @return 合成関数
     */
    @NotNull
    default <V2> F2<T1, V2, R> compose2(@NotNull F1<? super V2, ? extends T2> before) {
        return (t1, v2) -> apply(t1, before.apply(v2));
    }

    /**
     * この関数の第一引数と第二引数を入れ替えた関数を返します。
     *
     * @return 第一引数と第二引数を入れ替えた関数
     */
    @NotNull
    default F2<T2, T1, R> swap2() {
        return (t2, t1) -> apply(t1, t2);
    }

    /**
     * 入力をこの関数に適用し、二変数関数としての結果を述語afterに適用する合成述語を返します。
     *
     * <p>いずれかの関数の評価時に例外がスローされた場合、その例外は呼び出し元に中継されます。
     *
     * @param after この関数が適用された後に適用される述語
     * @return 合成述語
     */
    @NotNull
    default P2<T1, T2> asP2(@NotNull P1<? super R> after) {
        return (t1, t2) -> after.test(apply(t1, t2));
    }

    /**
     * 引数をこの関数に適用した結果を返すSupplierを返します。
     *
     * <p>この関数の評価時に例外がスローされた場合、その例外は呼び出し元に中継されます。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @return Supplier
     */
    @NotNull
    default S<R> asS(T1 t1, T2 t2) {
        return () -> apply(t1, t2);
    }

    /**
     * この関数の二変数関数としての結果をConsumer afterが消費するConsumerを返します。
     *
     * <p>この関数の評価時に例外がスローされた場合、その例外は呼び出し元に中継されます。
     *
     * @param after この関数の結果を消費するConsumer
     * @return Consumer
     */
    @NotNull
    default C2<T1, T2> asC2(@NotNull C1<? super R> after) {
        return (t1, t2) -> after.accept(apply(t1, t2));
    }

    /**
     * ラムダやメソッド参照から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数をそのまま返します。オーバーロードされているメソッドを参照する場合は次のようにラムダの引数等で明示的に型を指定してください。
     *
     * <pre>{@code
     *  F2<T1, T2, R> f1 = F2.of((T1 t1, T2 t2) -> SomeClass1.someMethod(t1, t2)).then1(SomeClass2::someMethod);
     *  F2<T1, T2, R> f2 = F2.<T1, T2, SomeType>of(SomeClass1::someMethod).then1(SomeClass2::someMethod);
     * }</pre>
     *
     * @param f2 ラムダやメソッド参照で記述された関数
     * @param <T1> 第一引数の型
     * @param <T2> 第二引数の型
     * @param <R> 戻り値の型
     * @return 引数に渡された関数
     */
    @NotNull
    static <T1, T2, R> F2<T1, T2, R> of(@NotNull F2<T1, T2, R> f2) {
        return f2;
    }
}
