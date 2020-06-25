package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.S;

/**
 * 再帰的に定義された型Tの値のSupplierです。
 *
 * <p>このインターフェースは{@link RS#get(RS)}を関数メソッドに持つ関数型インターフェースです。
 *
 * <p>このインターフェースはSupplier {@link S}を再帰的ラムダ式で定義するためのインターフェースです。
 * このインターフェース自体のインスタンスからは{@link S}で定義されるdefaultメソッドにはアクセスできません。
 * {@link RS#of}メソッドを使うことで再帰的ラムダ式から{@link S}型の関数オブジェクトを作成できます。
 *
 * @param <T> 供給する値の型
 */
@FunctionalInterface
public interface RS<T> {

    /**
     * Supplierを再帰的に定義します。
     *
     * <p>selfにこの関数自身を渡した場合、再帰的に値を取り出します。
     * selfに自身以外を渡した場合の動作は未定義です。
     *
     * <p>再帰的ラムダ式では{@link TailCall#call}に渡すSupplierの中でselfを参照し、{@link RS#get()}メソッドを呼び出してください。
     *
     * @param self this参照
     * @return 再帰関数の末尾呼び出し
     * @see RS#get()
     * @see TailCall#call
     * @see TailCall#complete
     */
    @NotNull
    TailCall<T> get(@NotNull RS<T> self);

    /**
     * 型Tの値を取得します。
     *
     * <p>この実装では{@link RS#get(RS)}メソッドに引数と関数自身を渡します。
     *
     * @return 再帰関数の末尾呼び出し
     */
    @NotNull
    default TailCall<T> get() {
        return get(this);
    }

    /**
     * 再帰的ラムダ式から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数に渡された再帰的ラムダ式をS型に変換して返します。
     * {@link TailCall#evaluate}の実行は{@link S#get}の実行まで遅延されます。
     *
     * <p>再帰的ラムダ式の型が推論されない場合は次のように明示的に型を指定してください。
     * <pre>{@code
     *  S<R> s = RS.<T>of(self -> {
     *      if(someCondition) return complete(someValue);
     *      return call(() -> self.get());
     *  }.then(SomeClass2::someMethod);
     * }</pre>
     *
     * @param rs 再帰的ラムダ式
     * @param <T> 供給する値の型
     * @return ラムダ式のS変換
     */
    @NotNull
    static <T> S<T> of(@NotNull RS<T> rs) {
        return () -> rs.get().evaluate();
    }
}