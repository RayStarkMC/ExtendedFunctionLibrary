package raystark.eflib.util;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.option.Option;
import raystark.eflib.option.Option.None;
import raystark.eflib.option.Option.Some;

final class Last<T> implements LastSideNode<T> {
    @NotNull private Option<Value<T>> previous;

    Last() {
        this.previous = None.of();
    }

    void setPrevious(@NotNull Option<Value<T>> previous) {
        this.previous = previous;
    }

    public void setPrevious(@NotNull Value<T> previous) {
        setPrevious(Some.of(previous));
    }

    @NotNull
    public Option<Value<T>> getPrevious() {
        return previous;
    }

    @NotNull
    Option<FirstSideNode<T>> getPreviousAsFirstSide() {
        return Option.cast(getPrevious());
    }

    @Override
    public Option<Value<T>> tryCastToValue() {
        return None.of();
    }
}
