package raystark.eflib.function.notnull;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.lazy.MLazy;
import raystark.eflib.lazy.SLazy;
import raystark.eflib.option.Option;

import java.util.function.Function;

/**
 * 型T1から型Rへの一変数関数です。
 *
 * <p>このインターフェースは{@link NF1#apply}を関数メソッドに持つ関数型インターフェースです。
 * <p>この関数はnullを扱いません。
 *
 * <p>このインターフェースは{@link Function}に対応します。{@link Function}に変換する場合次のイディオムが使えます。
 *
 * <pre>{@code
 *  NF1<T1, R> f1 = SomeClass::someMethod;
 *  Function<T1, R> f2 = f1::apply;
 * }</pre>
 *
 * <p>この関数には合成関数を作成するメソッド、自身を他の関数に変換するメソッド、
 * ラムダやメソッド参照からインスタンスを作成するメソッドが定義されています。
 *
 * @param <T1> 第一引数の型
 * @param <R> 戻り値の型
 */
@FunctionalInterface
public interface NF1<T1, R> {
    /**
     * 一変数関数として、引数をこの関数に適用します。
     *
     * @param t1 第一引数
     * @return 適用結果
     */
    @NotNull
    R apply(@NotNull T1 t1);

    /**
     * 入力をこの関数を適用し、一変数関数としての結果を関数afterに適用する合成関数を返します。
     *
     * <p>いずれかの関数の評価時にスローされた例外は呼び出し元に中継されます。
     *
     * @param after この関数が適用された後に適用される関数
     * @param <V> 合成関数の出力の型
     * @return 合成関数
     */
    @NotNull
    default <V> NF1<T1, V> then1(@NotNull NF1<? super R, ? extends V> after) {
        return t1 -> after.apply(this.apply(t1));
    }

    /**
     * 第一引数への入力を関数beforeに適用し、その結果をこの関数の第一引数に適用する合成関数を返します。
     *
     * <p>いずれかの関数の評価時にスローされた例外は呼び出し元に中継されます。
     *
     * @param before この関数が適用される前に第一引数に適用される関数
     * @param <V1>　合成関数の第一引数の型
     * @return 合成関数
     */
    @NotNull
    default <V1> NF1<V1, R> compose1(@NotNull NF1<? super V1, ? extends T1> before) {
        return v1 -> apply(before.apply(v1));
    }

    /**
     * 入力をこの関数に適用し、一変数関数としての結果を述語afterに適用する合成述語を返します。
     *
     * <p>いずれかの関数の評価時にスローされた例外は呼び出し元に中継されます。
     *
     * @param after この関数が適用された後に適用される述語
     * @return 合成述語
     */
    @NotNull
    default NP1<T1> asP1(@NotNull NP1<? super R> after) {
        return t1 -> after.test(apply(t1));
    }

    /**
     * 引数をこの関数に適用した結果を生成するSupplierを返します。
     *
     * <p>この関数の評価時にスローされた例外は呼び出し元に中継されます。
     *
     * @param t1 第一引数
     * @return Supplier
     */
    @NotNull
    default NS<R> asS(@NotNull T1 t1) {
        return () -> apply(t1);
    }

    /**
     * 引数をこの関数に適用した結果を生成するSupplierを返します。
     *
     * <p>この関数の評価時にスローされた例外は呼び出し元に中継されます。
     * 引数は遅延評価されます。
     *
     * @param t1 第一引数
     * @return Supplier
     */
    @NotNull
    default NS<R> asS(@NotNull NS<? extends T1> t1) {
        return () -> apply(t1.get());
    }

    /**
     * この関数の一変数関数としての結果をConsumer afterが消費するConsumerを返します。
     *
     * <p>この関数の評価時にスローされた例外は呼び出し元に中継されます。
     *
     * @param after この関数の結果を消費するConsumer
     * @return Consumer
     */
    @NotNull
    default NC1<T1> asC1(@NotNull NC1<? super R> after) {
        return t1 -> after.accept(apply(t1));
    }

    /**
     * Optionに対してこの関数でマッピングする関数を返します。
     *
     * @return Option型にリフトされた関数
     */
    @NotNull
    default NF1<Option<T1>, Option<R>> liftOption() {
        return liftOption(this);
    }

    /**
     * SLazyに対してこの関数でマッピングする関数を返します。
     *
     * @return SLazy型にリフトされた関数
     */
    @NotNull
    default NF1<SLazy<T1>, SLazy<R>> liftSLazy() {
        return sLazy -> sLazy.map(this);
    }

    /**
     * MLazyに対してこの関数でマッピングする関数を返します。
     *
     * @return MLazy型にリフトされた関数
     */
    @NotNull
    default NF1<MLazy<T1>, MLazy<R>> liftMLazy() {
        return mLazy -> mLazy.map(this);
    }

    /**
     * Optionに対してmapperでマッピングする関数を返します。
     *
     * @param mapper マッピング関数
     * @param <T1> マッピング関数の引数の型
     * @param <R> マッピング関数の戻り値の型
     * @return Option型にリフトされた関数
     */
    @NotNull
    static <T1, R> NF1<Option<T1>, Option<R>> liftOption(@NotNull NF1<? super T1, ? extends R> mapper) {
        return opt -> opt.map(mapper);
    }

    /**
     * SLazyに対してmapperでマッピングする関数を返します。
     *
     * @param mapper マッピング関数
     * @param <T1> マッピング関数の引数の型
     * @param <R> マッピング関数の戻り値の型
     * @return SLazy型にリフトされた関数
     */
    @NotNull
    static <T1, R> NF1<SLazy<T1>, SLazy<R>> liftSLazy(@NotNull NF1<? super T1, ? extends R> mapper) {
        return sLazy -> sLazy.map(mapper);
    }

    /**
     * MLazyに対してmapperでマッピングする関数を返します。
     *
     * @param mapper マッピング関数
     * @param <T1> マッピング関数の引数の型
     * @param <R> マッピング関数の戻り値の型
     * @return MLazy型にリフトされた関数
     */
    static <T1, R> NF1<MLazy<T1>, MLazy<R>> liftMLazy(@NotNull NF1<? super T1, ? extends R> mapper) {
        return mLazy -> mLazy.map(mapper);
    }

    /**
     * ラムダやメソッド参照から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数をそのまま返します。オーバーロードされているメソッドを参照する場合は次のようにラムダの引数等で明示的に型を指定してください。
     *
     * <pre>{@code
     *  NF1<T1, R> f1 = NF1.of((T1 t1) -> SomeClass1.someMethod(t1)).then1(SomeClass2::someMethod);
     *  NF1<T1, R> f2 = NF1.<T1, SomeType>of(SomeClass1::someMethod).then1(SomeClass2::someMethod);
     * }</pre>
     *
     * <p>このメソッドの呼び出しに対して様々なメソッドをチェインできます。
     *
     * @param f1 ラムダやメソッド参照で記述された関数
     * @param <T1> 第一引数の型
     * @param <R> 戻り値の型
     * @return 引数に渡された関数
     */
    @NotNull
    static <T1, R> NF1<T1, R> of(@NotNull NF1<T1, R> f1) {
        return f1;
    }

    /**
     * 型変数の変性を表すキャストメソッド。
     *
     * @param f1 キャスト対象
     * @param <T1> キャスト後第一引数の型
     * @param <R> キャスト後の戻り値の型
     * @return キャスト対象の参照
     */
    @SuppressWarnings("unchecked")
    @NotNull
    static <T1, R> NF1<T1, R> cast(@NotNull NF1<? super T1, ? extends R> f1) {
        return (NF1<T1, R>) f1;
    }

    /**
     * 恒等関数を返します。
     *
     * @param <T1> 恒等関数の型
     * @return 恒等関数
     */
    @NotNull
    static <T1> NF1<T1, T1> identity() {
        return NFunctionSupport.identity();
    }
}