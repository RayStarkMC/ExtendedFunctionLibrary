package raystark.eflib.util;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.option.Option;
import raystark.eflib.option.Option.Some;

class Value<T> implements FirstSideNode<T>, LastSideNode<T> {
    @NotNull private FirstSideNode<T> previous;
    @NotNull private LastSideNode<T> next;

    @NotNull private final T t;

    Value(@NotNull T t, @NotNull FirstSideNode<T> previous, @NotNull LastSideNode<T> next) {
        this.t = t;
        this.previous = previous;
        this.next = next;
    }

    @Override
    public void setPrevious(Value<T> previous) {
        this.previous = previous;
    }

    @Override
    public @NotNull Option<Value<T>> getPrevious() {
        return previous.tryCastToValue();
    }

    @Override
    public void setNext(@NotNull Value<T> next) {
        this.next = next;
    }

    @Override
    public @NotNull Option<Value<T>> getNext() {
        return next.tryCastToValue();
    }

    @NotNull
    T value() {
        return t;
    }

    @Override
    public Option<Value<T>> tryCastToValue() {
        return Some.of(this);
    }
}
