package raystark.eflib.lazy;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NS;

import java.util.Optional;

/**
 * マルチスレッド用の遅延初期化される値を表す型("M"ulti-Thread "Lazy")
 *
 * <p>{@link MLazy#of(NS)}でインスタンスを生成する時に指定したSupplierを用いて値を遅延初期化します。
 * Supplierは初めて{@link MLazy#get()}が呼ばれた時に一度のみ評価され、その後Supplierへの参照が破棄されます。
 *
 * <p>このクラスはスレッドセーフです。シングルスレッド環境かつパフォーマンスが重要な場合{@link SLazy}の利用を検討してください。
 * Supplierが参照透過でない場合や副作用を持つ場合は注意してください。
 * 評価毎に異なる参照を返すSupplierを利用した場合、{@link MLazy#get()}は最初の一度目の評価で得られた参照を返し続けます。
 * 副作用を持つSupplierを利用した場合、その副作用は初めて{@link MLazy#get()}が呼び出された時に一度だけ実行されます。
 *
 * @param <T> 遅延初期化される値の型
 */
public final class MLazy<T> {
    private volatile T value;
    private volatile NS<? extends T> initializer;

    private MLazy(NS<? extends T> initializer) {
        this.initializer = initializer;
    }

    /**
     * 値を算出するSupplierを指定してLazyを生成します。
     *
     * <p>initializerにnullを返すSupplierを指定する事は禁止されています。
     * そのような場合は{@link Optional}を返すサプライヤを利用するか、空コンテナに対応するオブジェクトの使用を検討してください。
     *
     * <p>渡されたinitializerの評価はLazy生成後に初めて{@link MLazy#get()}が呼ばれるまで遅延されます。
     * 値の初期化後はinitializerへの参照は破棄されます。
     *
     * @param initializer 値を算出するSupplier
     * @param <T> 生成する値の型
     * @return initializerにより遅延初期化される値を表すMLazyのインスタンス
     */
    @NotNull
    public static <T> MLazy<T> of(@NotNull NS<? extends T> initializer) {
        return new MLazy<>(initializer);
    }

    /**
     * initializerによって生成された値を取得します。
     *
     * <p>このメソッドが複数回呼び出された場合、必ず最初の一回目に返した値を返します。
     *　initializerへの参照はこのメソッドの初回実行後に破棄されます。
     *
     * <p>このメソッドはスレッドセーフです。
     *
     * @return 遅延初期化された値
     */
    @NotNull
    public T get() {
        //二重チェックイディオム
        T result = value;
        if(result == null) {
            synchronized (this) {
                result = value;
                if(result == null) {
                    value = result = initializer.get();
                    initializer = null; //初期化以降不要なinitializerをGC対象にするためのnull代入
                }
            }
        }
        return result;
    }
}
