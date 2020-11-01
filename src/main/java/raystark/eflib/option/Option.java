package raystark.eflib.option;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.function.A;
import raystark.eflib.function.P1;
import raystark.eflib.function.notnull.NC1;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.function.notnull.NS;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 存在しない可能性のある値を表す型。
 *
 * <p>T型の非null値を表す{@link Some}と値が存在しない事を表す{@link None}の二つの具象型が存在します。
 * このクラスを含め、Optionの全ての実装は継承する事が出来ません。
 * Optionは不変なので全てのメソッドは外部同期無しでスレッドセーフです。
 *
 * <p>Optionの生成に用いる値、{@link Option#map(NF1)}や{@link Option#flatMap(NF1)}等のメソッドで受け取った関数は
 * 全て正格評価されます。もし評価を遅延したい場合は{@literal Lazy<Option<T>>}の形式での利用を検討してください。
 *
 * <p>Tの変性については{@link Option#cast(Option)}を参照してください。
 *
 * @param <T> 値の型
 */
public abstract class Option<T> {
    Option() {}

    /**
     * このインスタンスの変性を表すキャストメソッド。
     *
     * <p>OptionはT型について共変です。すなわちA extends Bの時{@literal Option<A>}は{@literal Option<B>}のサブクラスです。
     * このメソッドを使ってそのようなOptionをキャストする事ができます。
     *
     * @param option サブタイプのoption
     * @param <T> キャスト後のOptionの型
     * @return 同じインスタンス
     */
    @SuppressWarnings("unchecked")
    @NotNull
    public static <T> Option<T> cast(@NotNull Option<? extends T> option) {
        return (Option<T>) option;
    }

    /**
     * valueがnullの場合Noneを、非nullの場合はその値を保持したSomeを返します。
     *
     * @param value Optionでラップする値
     * @param <T> 値の型
     * @return None又はvalueを保持したSome
     */
    @NotNull
    public static <T> Option<T> ofNullable(@Nullable T value) {
        return value == null ? None.of() : Some.of(value);
    }

    /**
     * このOptionの値にmapperを適用した値を保持するOptionを返します。
     *
     * <p>このOptionがNoneの場合はmapperが評価されず、Noneが返されます。
     * Someの場合mapperが評価され、その結果を保持するSomeが返されます。
     *
     * @param mapper マッピング関数
     * @param <V> マッピング後の型
     * @return Optionの値をmapperに適用した結果を保持するOption
     */
    @NotNull
    public abstract <V> Option<V> map(@NotNull NF1<? super T, ? extends V> mapper);

    /**
     * このOptionの値にmapperを適用した結果を返します。
     *
     * <p>このOptionがNoneの場合はmapperが評価されず、自身が返されます。
     * Someの場合その値でmapperが評価され、その結果が返されます。
     *
     * @param mapper マッピング関数
     * @param <V> マッピング後の型
     * @return Optionの値をmapperに適用した結果
     */
    @NotNull
    public abstract <V> Option<V> flatMap(@NotNull NF1<? super T, ? extends Option<? extends V>> mapper);

    /**
     * testerによりOptionを選別します。
     *
     * <p>このOptionがSomeで、かつその値がtesterを満たす場合その値を保持したSomeを返し、それ以外の場合Noneが返されます。
     * このOptionがNoneの場合testerは評価されません。
     *
     * @param tester Optionを選別する述語
     * @return testerにより選別されたOption
     */
    @NotNull
    public abstract Option<T> filter(@NotNull P1<T> tester);

    /**
     * testerによりOptionを選別します。
     *
     * <p>{@link Option#filter(P1)}と同様ですが、testerを満たさないSomeを通過させます。
     *
     * @param tester Optionを選別する述語
     * @return testerにより選別されたOption
     */
    @NotNull
    public Option<T> filterNot(@NotNull P1<T> tester) {
        return filter(tester.not());
    }

    /**
     * このOptionがSomeの場合trueを、Noneの場合falseを返します。
     *
     * @return このOptionがSomeの場合true
     */
    public abstract boolean isPresent();

    /**
     * このOptionがNoneの場合trueを、Someの場合falseを返します。
     *
     * @return このOptionがNoneの場合true
     */
    public boolean isEmpty() {
        return !isPresent();
    }

    /**
     * このOptionがSomeの場合このOptionを、Noneの場合otherを返します。
     *
     * @param other このOptionがNoneの場合に返すOption
     * @return このOptionがSomeの場合このOption、Noneの場合other
     */
    @NotNull
    public abstract Option<T> or(@NotNull Option<? extends T> other);

    /**
     * このOptionがSomeの場合このOptionを、Noneの場合otherの生成するOptionを返します。
     *
     * <p>このOptionがSomeの場合otherは評価されません。
     *
     * @param other このOptionがNoneの場合に返すOptionのSupplier
     * @return このOptionがSomeの場合このOption、Noneの場合otherの生成するOption
     */
    @NotNull
    public abstract Option<T> or(@NotNull NS<Option<? extends T>> other);

    /**
     * 値が存在する場合その値を、そうでない場合otherを返します。
     *
     * @param other 値が存在しない時に還す値
     * @return 値が存在する場合その値、そうでない場合other
     */
    @NotNull
    public abstract T orElse(@NotNull T other);

    /**
     * 値が存在する場合その値を、そうでない場合otherの生成する値を返します。
     *
     * <p>このOptionがSomeの場合otherは評価されません。
     *
     * @param other 値が存在しない場合に返す値を生成するSupplier
     * @return 値が存在する場合その値、そうでない場合otherの生成する値
     */
    @NotNull
    public abstract T orElse(@NotNull NS<? extends T> other);

    /**
     * 値が存在する場合その値を返し、そうでない場合例外をスローします。
     *
     * @throws NoSuchElementException 値が存在しない場合
     * @return 値が存在する場合その値
     */
    @NotNull
    public abstract T orElseThrow();

    /**
     * 値が存在する場合その値を返し、そうでない場合例外をスローします。
     *
     * <p>このOptionがSomeの場合exceptionSupplierは評価されません。
     *
     * @throws X 値が存在しない場合
     * @return 値が存在する場合その値
     */
    @NotNull
    public abstract <X extends Throwable> T orElseThrow(@NotNull NS<? extends X> exceptionSupplier) throws X;

    /**
     * 値が存在する場合、その値にconsumerを適用します。
     *
     * @param consumer 値が存在する場合にその値に適用するConsumer
     */
    public abstract void ifPresent(@NotNull NC1<? super T> consumer);

    /**
     * 値が存在しない場合、actionを実行します。
     *
     * @param action 値が存在しない場合に実行するaction
     */
    public abstract void ifNotPresent(@NotNull A action);

    /**
     * 値が存在する場合、その値にconsumerを適用します。
     *
     * <p>このメソッドは自分自身を返します。
     *
     * @param consumer 値が存在する場合にその値に適用するConsumer
     * @return this
     */
    @NotNull
    public abstract Option<T> whenPresent(@NotNull NC1<? super T> consumer);

    /**
     * 値が存在しない場合、actionを実行します。
     *
     * @param action 値が存在しない場合に実行するaction
     * @return this
     */
    @NotNull
    public abstract Option<T> whenNotPresent(@NotNull A action);

    /**
     * このOptionをOptionalに変換します。
     *
     * <p>このOptionがSomeの場合その値を保持するOptionalを、Noneの場合空のOptionalを返します。
     *
     * @return このOptionに対応するOptional
     */
    @NotNull
    public abstract Optional<T> optional();

    /**
     * このOptionをStreamに変換します。
     *
     * <p>このOptionがSomeの場合その値のみを保持するStreamを、Noneの場合空のStreamを返します。
     *
     * @return このOptionに対応するStream
     */
    @NotNull
    public abstract Stream<T> stream();

    /**
     * このOptionをIterableに変換します。
     *
     * <p>このOptionがSomeの場合その値のみを保持するIterableを、Noneの場合空のIterableを返します。
     *
     * @return このOptionに対応するIterable
     */
    @NotNull
    public Iterable<T> iterable() {
        return stream()::iterator;
    }

    public static class Some<T> extends Option<T> {
        private final T value;

        private Some(T value) {
            this.value = value;
        }

        @NotNull
        public static <T> Some<T> of(@NotNull T value) {
            return new Some<>(value);
        }

        @SuppressWarnings("unchecked")
        @NotNull
        public static <T> Some<T> cast(@NotNull Some<? extends T> some) {
            return (Some<T>) some;
        }

        @Override
        @NotNull
        public <V> Option<V> map(@NotNull NF1<? super T, ? extends V> mapper) {
            return Some.of(mapper.apply(value));
        }

        @Override
        @NotNull
        public <V> Option<V> flatMap(@NotNull NF1<? super T, ? extends Option<? extends V>> mapper) {
            return cast(mapper.apply(value));
        }

        @Override
        @NotNull
        public Option<T> filter(@NotNull P1<T> tester) {
            return tester.test(value) ? this : None.of();
        }

        @Override
        public boolean isPresent() {
            return true;
        }

        @Override
        @NotNull
        public Option<T> or(@NotNull Option<? extends T> other) {
            return this;
        }

        @Override
        @NotNull
        public Option<T> or(@NotNull NS<Option<? extends T>> other) {
            return this;
        }

        @Override
        @NotNull
        public T orElse(@NotNull T other) {
            return value;
        }

        @Override
        @NotNull
        public T orElse(@NotNull NS<? extends T> other) {
            return value;
        }

        @Override
        @NotNull
        public T orElseThrow() {
            return value;
        }

        @Override
        @NotNull
        public <X extends Throwable> T orElseThrow(@NotNull NS<? extends X> exceptionSupplier) {
            return value;
        }

        public T get() {
            return value;
        }

        @Override
        public void ifPresent(@NotNull NC1<? super T> consumer) {
            consumer.accept(value);
        }

        @Override
        public void ifNotPresent(@NotNull A action) {}

        @Override
        @NotNull
        public Option<T> whenPresent(@NotNull NC1<? super T> consumer) {
            consumer.accept(value);
            return this;
        }

        @Override
        @NotNull
        public Option<T> whenNotPresent(@NotNull A action) {
            return this;
        }

        @Override
        @NotNull
        public Optional<T> optional() {
            return Optional.of(value);
        }

        @Override
        @NotNull
        public Stream<T> stream() {
            return Stream.of(value);
        }
    }

    public static class None<T> extends Option<T> {
        private static final None<?> INSTANCE = new None<>();

        private None() {}

        @NotNull
        public static <T> None<T> of() {
            return cast(INSTANCE);
        }

        @SuppressWarnings("unchecked")
        public static <T> None<T> cast(None<?> option) {
            return (None<T>) option;
        }

        @Override
        @NotNull
        public <V> Option<V> map(@NotNull NF1<? super T, ? extends V> mapper) {
            return cast(this);
        }

        @Override
        @NotNull
        public <V> Option<V> flatMap(@NotNull NF1<? super T, ? extends Option<? extends V>> mapper) {
            return cast(this);
        }

        @Override
        @NotNull
        public Option<T> filter(@NotNull P1<T> tester) {
            return this;
        }

        @Override
        public boolean isPresent() {
            return false;
        }

        @Override
        @NotNull
        public Option<T> or(@NotNull Option<? extends T> other) {
            return cast(other);
        }

        @Override
        @NotNull
        public Option<T> or(@NotNull NS<Option<? extends T>> other) {
            return cast(other.get());
        }

        @Override
        @NotNull
        public T orElse(@NotNull T other) {
            return other;
        }

        @Override
        @NotNull
        public T orElse(@NotNull NS<? extends T> other) {
            return other.get();
        }

        @Override
        @NotNull
        public T orElseThrow() {
            throw new NoSuchElementException();
        }

        @Override
        @NotNull
        public <X extends Throwable> T orElseThrow(@NotNull NS<? extends X> exceptionSupplier) throws X {
            throw exceptionSupplier.get();
        }

        @Override
        public void ifPresent(@NotNull NC1<? super T> consumer) {}

        @Override
        public void ifNotPresent(@NotNull A action) {
            action.run();
        }

        @Override
        @NotNull
        public Option<T> whenPresent(@NotNull NC1<? super T> consumer) {
            return this;
        }

        @Override
        @NotNull
        public Option<T> whenNotPresent(@NotNull A action) {
            action.run();
            return this;
        }

        @Override
        @NotNull
        public Optional<T> optional() {
            return Optional.empty();
        }

        @Override
        @NotNull
        public Stream<T> stream() {
            return Stream.of();
        }
    }
}
