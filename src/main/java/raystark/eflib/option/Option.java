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

public abstract class Option<T> {
    Option() {}

    @SuppressWarnings("unchecked")
    @NotNull
    public static <T> Option<T> cast(Option<? extends T> option) {
        return (Option<T>) option;
    }

    @NotNull
    public static <T> Option<T> ofNullable(@Nullable T value) {
        return value == null ? None.of() : Some.of(value);
    }

    @NotNull
    public abstract <V> Option<V> map(@NotNull NF1<? super T, ? extends V> mapper);

    @NotNull
    public abstract <V> Option<V> flatMap(@NotNull NF1<? super T, Option<? extends V>> mapper);

    @NotNull
    public abstract Option<T> filter(@NotNull P1<T> predicate);

    public abstract boolean isPresent();

    public boolean isEmpty() {
        return !isPresent();
    }

    @NotNull
    public abstract Option<T> or(@NotNull Option<? extends T> other);

    @NotNull
    public abstract Option<T> or(@NotNull NS<Option<? extends T>> other);

    @NotNull
    public abstract T orElse(@NotNull T other);

    @NotNull
    public abstract T orElse(@NotNull NS<? extends T> other);

    @NotNull
    public abstract T orElseThrow();

    @NotNull
    public abstract <X extends Throwable> T orElseThrow(@NotNull NS<? extends X> exceptionSupplier) throws X;

    public abstract void ifPresent(@NotNull NC1<? super T> consumer);

    public abstract void ifNotPresent(@NotNull A action);

    @NotNull
    public abstract Option<T> whenPresent(@NotNull NC1<? super T> consumer);

    @NotNull
    public abstract Option<T> whenNotPresent(@NotNull A action);

    @NotNull
    public abstract Optional<T> optional();

    @NotNull
    public abstract Stream<T> stream();

    @NotNull
    public Iterable<T> iterable() {
        return stream()::iterator;
    }

    public static class Some<T> extends Option<T> {
        private final T value;

        private Some(T value) {
            this.value = value;
        }

        public static <T> Some<T> of(@NotNull T value) {
            return new Some<>(value);
        }

        @Override
        @NotNull
        public <V> Option<V> map(@NotNull NF1<? super T, ? extends V> mapper) {
            return Some.of(mapper.apply(value));
        }

        @Override
        @NotNull
        public <V> Option<V> flatMap(@NotNull NF1<? super T, Option<? extends V>> mapper) {
            return cast(mapper.apply(value));
        }

        @Override
        @NotNull
        public Option<T> filter(@NotNull P1<T> predicate) {
            return predicate.test(value) ? this : None.of();
        }

        @Override
        public boolean isPresent() {
            return true;
        }

        @Override
        @NotNull
        public Option<T> or(@NotNull Option<? extends T> other) {
            return cast(other);
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

        @SuppressWarnings("unchecked")
        @NotNull
        public static <T> None<T> of() {
            return (None<T>) INSTANCE;
        }

        @SuppressWarnings("unchecked")
        @Override
        @NotNull
        public <V> Option<V> map(@NotNull NF1<? super T, ? extends V> mapper) {
            return (Option<V>)this;
        }

        @SuppressWarnings("unchecked")
        @Override
        @NotNull
        public <V> Option<V> flatMap(@NotNull NF1<? super T, Option<? extends V>> mapper) {
            return (Option<V>)this;
        }

        @Override
        @NotNull
        public Option<T> filter(@NotNull P1<T> predicate) {
            return this;
        }

        @Override
        public boolean isPresent() {
            return false;
        }

        @SuppressWarnings("unchecked")
        @Override
        @NotNull
        public Option<T> or(@NotNull Option<? extends T> other) {
            return (Option<T>) other;
        }

        @SuppressWarnings("unchecked")
        @Override
        public @NotNull Option<T> or(@NotNull NS<Option<? extends T>> other) {
            return (Option<T>) other.get();
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
        public @NotNull Option<T> whenPresent(@NotNull NC1<? super T> consumer) {
            return this;
        }

        @Override
        public @NotNull Option<T> whenNotPresent(@NotNull A action) {
            action.run();
            return this;
        }

        @Override
        public @NotNull Optional<T> optional() {
            return Optional.empty();
        }

        @Override
        public @NotNull Stream<T> stream() {
            return Stream.of();
        }
    }
}
