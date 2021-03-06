package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiPredicate;

/**
 * 型T1, 型T2の述語です。またBoolean型を返す関数です。
 *
 * <p>このインターフェースは{@link P2#test}を関数メソッドに持つ関数型インターフェースです。
 *
 * <p>オーバーライドされた{@link F2}のメソッドの内、関数を返すものは対応する述語を返します。
 *
 * <p>このインターフェースは{@link BiPredicate}に対応します。{@link BiPredicate}に変換する場合次のイディオムが使えます。
 *
 * <pre>{@code
 *  P2<T1, T2> p1 = SomeClass1::someMethod;
 *  BiPredicate<T1, T2> p2 = p1::test;
 * }</pre>
 *
 * <p>この述語には部分適用を行うメソッド、合成述語を作成するメソッド、引数の順序を変えるメソッド、
 * 自身を他の関数に変換するメソッド、ラムダやメソッド参照からインスタンスを作成するメソッドが定義されています。
 *
 * @param <T1> 第一引数の型
 * @param <T2> 第二引数の型
 * @see F2
 */
@FunctionalInterface
public interface P2<T1, T2> extends F2<T1, T2, Boolean> {
    /**
     * 指定された引数でこの述語を評価します。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @return 引数が述語に一致する場合true, それ以外の場合false
     */
    boolean test(@Nullable T1 t1, @Nullable T2 t2);

    /**
     * 指定された引数でこの述語を評価します。
     *
     * <p>このメソッドは{@link P2#test}の結果をBoolean型にラップします。
     *
     * @param t1 第一引数
     * @param t2 第二引数
     * @return Boolean型にラップされた述語の結果
     */
    @Override
    @NotNull
    default Boolean apply(@Nullable T1 t1, @Nullable T2 t2) {
        return test(t1, t2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default P1<T2> apply(@Nullable T1 t1) {
        return t2 -> test(t1, t2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default P1<T2> apply(@NotNull S<? extends T1> t1) {
        return t2 -> apply(t1.get(), t2);
    }

    /**
     * この述語と述語otherの短絡論理積を表す合成述語を返します。
     *
     * <p>この述語がfalseだった場合、又はこの述語の評価時に例外がスローされた場合otherは評価されません。
     *
     * <p>いずれかの述語の評価時にスローされた例外は呼び出し元に中継されます。
     *
     * @param other この述語と論理積を取る述語
     * @return 合成述語
     */
    @NotNull
    default P2<T1, T2> and(@NotNull P2<? super T1, ? super T2> other) {
        return (t1, t2) -> test(t1, t2) && other.test(t1, t2);
    }

    /**
     * この述語と述語otherの短絡論理和を表す合成述語を返します。
     *
     * <p>この述語がtrueだった場合、又はこの述語の評価時に例外がスローされた場合otherは評価されません。
     *
     * <p>いずれかの述語の評価時にスローされた例外は呼び出し元に中継されます。
     *
     * @param other この述語と論理和を取る述語
     * @return 合成述語
     */
    @NotNull
    default P2<T1, T2> or(@NotNull P2<? super T1, ? super T2> other) {
        return (t1, t2) -> test(t1, t2) || other.test(t1, t2);
    }

    /**
     * この述語の論理否定を表す述語を返します。
     *
     * @return 述語
     */
    @NotNull
    default P2<T1, T2> not() {
        return (t1, t2) -> !test(t1, t2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default <V1> P2<V1, T2> compose1(@NotNull F1<? super V1, ? extends T1> before) {
        return (v1, t2) -> test(before.apply(v1), t2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default <V2> P2<T1, V2> compose2(@NotNull F1<? super V2, ? extends T2> before) {
        return (t1, v2) -> test(t1, before.apply(v2));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    default P2<T2, T1> swap2() {
        return (t2, t1) -> test(t1, t2);
    }

    /**
     * ラムダやメソッド参照から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数をそのまま返します。オーバーロードされているメソッドを参照する場合は次のようにラムダの引数等で明示的に型を指定してください。
     *
     * <pre>{@code
     *  P2<T1, T2> p1 = P2.of((T1 t1, T2 t2) -> SomeClass1.someMethod(t1, t2)).and(SomeClass2::someMethod);
     *  P2<T1, T2> p2 = P2.<T1, T2>of(SomeClass1::someMethod).then1(SomeClass2::someMethod);
     * }</pre>
     *
     * <p>このメソッドの呼び出しに対して様々なメソッドをチェインできます。
     *
     * @param p2 ラムダやメソッド参照で記述された述語
     * @param <T1> 第一引数の型
     * @param <T2> 第二引数の型
     * @return 引数に渡された述語
     */
    @NotNull
    static <T1, T2> P2<T1, T2> of(@NotNull P2<T1, T2> p2) {
        return p2;
    }

    /**
     * 型変数の変性を表すキャストメソッド。
     *
     * @param p2 キャスト対象
     * @param <T1> キャスト後第一引数の型
     * @param <T2> キャスト後第二引数の型
     * @return キャスト対象の参照
     */
    @SuppressWarnings("unchecked")
    @NotNull
    static <T1, T2> P2<T1, T2> cast(@NotNull P2<? super T1, ? super T2> p2) {
        return (P2<T1, T2>) p2;
    }
}