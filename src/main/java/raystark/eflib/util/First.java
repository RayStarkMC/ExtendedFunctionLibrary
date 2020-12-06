package raystark.eflib.util;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.option.Option;
import raystark.eflib.option.Option.None;
import raystark.eflib.option.Option.Some;

final class First<T> implements FirstSideNode<T> {
    @NotNull private Option<Value<T>> next;

    First() {
        this.next = None.of();
    }

    void setNext(@NotNull Option<Value<T>> next) {
        this.next = next;
    }

    @Override
    public void setNext(Value<T> next) {
        setNext(Some.of(next));
    }

    @NotNull
    Option<LastSideNode<T>> getNextAsLastSide() {
        return Option.cast(getNext());
    }

    @Override
    public @NotNull Option<Value<T>> getNext() {
        return next;
    }

    @Override
    public Option<Value<T>> tryCastToValue() {
        return None.of();
    }
}
