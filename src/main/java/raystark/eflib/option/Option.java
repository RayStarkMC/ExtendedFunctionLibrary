package raystark.eflib.option;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.function.P1;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.function.notnull.NS;

import java.util.NoSuchElementException;

public abstract class Option<T> {
    Option() {}

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

        @SuppressWarnings("unchecked")
        @Override
        @NotNull
        public <V> Option<V> flatMap(@NotNull NF1<? super T, Option<? extends V>> mapper) {
            return (Option<V>) mapper.apply(value);
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
    }
}
