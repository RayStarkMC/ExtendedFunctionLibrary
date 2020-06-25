package raystark.eflib.function.notnull.recursive;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NS;

/**
 * 再帰的に定義された型Tの値のSupplierです。
 *
 * <p>このインターフェースは{@link NRS#get(NRS)}を関数メソッドに持つ関数型インターフェースです。
 * <p>この関数はnullを扱いません。
 *
 * <p>このインターフェースはSupplier {@link NS}を再帰的ラムダ式で定義するためのインターフェースです。
 * このインターフェース自体のインスタンスからは{@link NS}で定義されるdefaultメソッドにはアクセスできません。
 * {@link NRS#of}メソッドを使うことで再帰的ラムダ式から{@link NS}型の関数オブジェクトを作成できます。
 *
 * @param <T> 供給する値の型
 */
@FunctionalInterface
public interface NRS<T> {

    /**
     * Supplierを再帰的に定義します。
     *
     * <p>selfにこの関数自身を渡した場合、再帰的に値を取り出します。
     * selfに自身以外を渡した場合の動作は未定義です。
     *
     * <p>再帰的ラムダ式では{@link NTailCall#call}に渡すSupplierの中でselfを参照し、{@link NRS#get()}メソッドを呼び出してください。
     *
     * @param self this参照
     * @return 再帰関数の末尾呼び出し
     * @see NRS#get()
     * @see NTailCall#call
     * @see NTailCall#complete
     */
    @NotNull
    NTailCall<T> get(@NotNull NRS<T> self);

    /**
     * 型Tの値を取得します。
     *
     * <p>この実装では{@link NRS#get(NRS)}メソッドに引数と関数自身を渡します。
     *
     * @return 再帰関数の末尾呼び出し
     */
    @NotNull
    default NTailCall<T> get() {
        return get(this);
    }

    /**
     * 再帰的ラムダ式から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数に渡された再帰的ラムダ式をNS型に変換して返します。
     * {@link NTailCall#evaluate}の実行は{@link NS#get}の実行まで遅延されます。
     *
     * <p>再帰的ラムダ式の型が推論されない場合は次のように明示的に型を指定してください。
     * <pre>{@code
     *  NS<R> s = NRS.<T>of(self -> {
     *      if(someCondition) return complete(someValue);
     *      return call(() -> self.get());
     *  }.then(SomeClass2::someMethod);
     * }</pre>
     *
     * @param rs 再帰的ラムダ式
     * @param <T> 供給する値の型
     * @return ラムダ式のNS変換
     */
    @NotNull
    static <T> NS<T> of(@NotNull NRS<T> rs) {
        return () -> rs.get().evaluate();
    }
}
