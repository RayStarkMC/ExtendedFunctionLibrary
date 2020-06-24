package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.A;

/**
 * 再帰的に定義されたActionです。
 *
 * <p>このインターフェースは{@link RA#run(RA)}を関数メソッドに持つ関数型インターフェースです。
 *
 * <p>このインターフェースはAction {@link A}を再帰的ラムダ式で定義するためのインターフェースです。
 * このインターフェース自体のインスタンスからは{@link A}で定義されるdefaultメソッドにはアクセスできません。
 * {@link #of}メソッドを使うことで再帰的ラムダ式から{@link A}型の関数オブジAェクトを作成できます。
 *
 */
@FunctionalInterface
public interface RA {

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
     * @see RA#run()
     * @see VoidTailCall#call
     */
    @NotNull
    VoidTailCall run(@NotNull RA self);

    /**
     * 手続きを実行します。
     *
     * <p>この実装では{@link RA#run(RA)}メソッドに関数自身を渡します。
     *
     * @return 再帰関数の末尾呼び出し
     * @see VoidTailCall#call
     */
    @NotNull
    default VoidTailCall run() {
        return run(this);
    }

    /**
     * 再帰的ラムダ式から関数オブジェクトを生成するファクトリメソッドです。
     *
     * <p>このメソッドは引数に渡された再帰的ラムダ式をA型に変換して返します。
     * {@link VoidTailCall#execute()}の実行は{@link A#run}の実行まで遅延されます。
     *
     * @param ra 再帰的ラムダ式
     * @return ラムダ式のA変換
     */
    @NotNull
    static A of(@NotNull RA ra) {
        return () -> ra.run().execute();
    }
}