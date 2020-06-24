package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 型T1, 型T2, 型T3, 型T4から型Rへの四変数関数です。
 *
 * <p>このインターフェースは{@link F4#apply}を関数メソッドに持つ関数型インターフェースです。
 *
 * <p>この関数には部分適用を行うメソッド、合成関数を作成するメソッド、引数の順序を変えるメソッド、
 * 自身を他の関数に変換するメソッド、ラムダやメソッド参照からインスタンスを作成するメソッドが定義されています。
 *
 * @param <T1> 第一引数の型
 * @param <T2> 第二引数の型
 * @param <T3> 第三引数の型
 * @param <T4> 第四引数の型
 * @param <R> 戻り値の型
 */
@FunctionalInterface
public interface F4<T1, T2, T3, T4, R> extends F3<T1, T2, T3, F1<T4, R>> {
    /**
     * 四変数関数として、引数をこの関数に適用します。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @param t3 第三引数
     * @param t4 第四引数
     * @return 適用結果
     */
    @Nullable
    R apply(@Nullable T1 t1, @Nullable T2 t2, @Nullable T3 t3, @Nullable T4 t4);

    /**
     * 第三引数までをこの関数に部分適用します。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @param t3 第三引数
     * @return 引数が部分適用された関数
     */
    @NotNull
    @Override
    default F1<T4, R> apply(@Nullable T1 t1, @Nullable T2 t2, @Nullable T3 t3) {
        return t4 -> apply(t1, t2, t3, t4);
    }

    /**
     * 入力をこの関数を適用し、四変数関数としての結果を関数afterに適用する合成関数を返します。
     *
     * <p>いずれかの関数の評価時にスローされた例外は呼び出し元に中継されます。
     *
     * @param after この関数が適用された後に適用される関数
     * @param <V> 合成関数の出力の型
     * @return 合成関数
     */
    @NotNull
    default <V> F4<T1, T2, T3, T4, V> then4(@NotNull F1<? super R, ? extends V> after) {
        return (t1, t2, t3, t4) -> after.apply(apply(t1, t2, t3, t4));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default <V1> F4<V1, T2, T3, T4, R> compose1(@NotNull F1<? super V1, ? extends T1> before) {
        return (v1, t2, t3, t4) -> apply(before.apply(v1), t2, t3, t4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default <V2> F4<T1, V2, T3, T4, R> compose2(@NotNull F1<? super V2, ? extends T2> before) {
        return (t1, v2, t3, t4) -> apply(t1, before.apply(v2), t3, t4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default <V3> F4<T1, T2, V3, T4, R> compose3(@NotNull F1<? super V3, ? extends T3> before) {
        return (t1, t2, v3, t4) -> apply(t1, t2, before.apply(v3), t4);
    }

    /**
     * 第四引数への入力を関数beforeに適用し、その結果をこの関数の第四引数に適用する合成関数を返します。
     *
     * <p>いずれかの関数の評価時にスローされた例外は呼び出し元に中継されます。
     *
     * @param before この関数に適用される前に第四引数に適用される関数
     * @param <V4> 合成関数の第四引数の型
     * @return 合成関数
     */
    @NotNull
    default <V4> F4<T1, T2, T3, V4, R> compose4(@NotNull F1<? super V4, ? extends T4> before) {
        return (t1, t2, t3, v4) -> apply(t1, t2, t3, before.apply(v4));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default F4<T2, T1, T3, T4, R> swap2() {
        return (t2, t1, t3, t4) -> apply(t1, t2, t3, t4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default F4<T3, T2, T1, T4, R> swap3() {
        return (t3, t2, t1, t4) -> apply(t1, t2, t3, t4);
    }

    /**
     * この関数の第一引数と第四引数を入れ替えた関数を返します。
     *
     * @return 第一引数と第四引数を入れ替えた関数
     */
    @NotNull
    default F4<T4, T2, T3, T1, R> swap4() {
        return (t4, t2, t3, t1) -> apply(t1, t2, t3, t4);
    }

    /**
     * 入力をこの関数に適用し、四変数関数としての結果を述語afterに適用する合成述語を返します。
     *
     * <p>いずれかの関数の評価時にスローされた例外は呼び出し元に中継されます。
     *
     * @param after この関数が適用された後に適用される述語
     * @return 合成述語
     */
    @NotNull
    default P4<T1, T2, T3, T4> asP4(@NotNull P1<? super R> after) {
        return (t1, t2, t3, t4) -> after.test(apply(t1, t2, t3, t4));
    }

    /**
     * 引数をこの関数に適用した結果を返すSupplierを返します。
     *
     * <p>この関数の評価時にスローされた例外は呼び出し元に中継されます。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @param t3 第三引数
     * @param t4 第四引数
     * @return Supplier
     */
    @NotNull
    default S<R> asS(@Nullable T1 t1, @Nullable T2 t2, @Nullable T3 t3, @Nullable T4 t4) {
        return () -> apply(t1, t2, t3, t4);
    }

    /**
     * この関数の四変数関数としての結果をConsumer afterが消費するConsumerを返します。
     *
     * <p>この関数の評価時にスローされた例外は呼び出し元に中継されます。
     *
     * @param after この関数の結果を消費するConsumer
     * @return Consumer
     */
    @NotNull
    default C4<T1, T2, T3, T4> asC4(@NotNull C1<? super R> after) {
        return (t1, t2, t3, t4) -> after.accept(apply(t1, t2, t3, t4));
    }

    /**
     * ラムダやメソッド参照から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数をそのまま返します。オーバーロードされているメソッドを参照する場合は次のようにラムダの引数等で明示的に型を指定してください。
     *
     * <pre>{@code
     *  F2<T1, T2, T3, T4, R> f1 = F4.of((T1 t1, T2 t2, T3 t3, T4 t4) -> SomeClass1.someMethod(t1, t2, t3, t4)).then1(SomeClass2::someMethod);
     *  F2<T1, T2, T3, T4, R> f2 = F4.<T1, T2, T3, T3, SomeType>of(SomeClass1::someMethod).then1(SomeClass2::someMethod);
     * }</pre>
     *
     * <p>このメソッドの呼び出しに対して様々なメソッドをチェインできます。
     *
     * @param f4 ラムダやメソッド参照で記述された関数
     * @param <T1> 第一引数の型
     * @param <T2> 第二引数の型
     * @param <T3> 第三引数の型
     * @param <T4> 第四引数の型
     * @param <R> 戻り値の型
     * @return 引数に渡された関数
     */
    @NotNull
    static <T1, T2, T3, T4, R> F4<T1, T2, T3, T4, R> of(@NotNull F4<T1, T2, T3, T4, R> f4) {
        return f4;
    }
}
