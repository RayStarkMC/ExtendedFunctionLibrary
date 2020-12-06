package raystark.eflib.util;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NS;
import raystark.eflib.option.Option;

import java.util.Iterator;

public final class Chain<T> {
    private final First<T> first;
    private final Last<T> last;

    private final OneShotState state;

    private static final NS<IllegalStateException> EXCEPTION_SUPPLIER = () -> new IllegalStateException("This chain has already been operated.");

    public Chain() {
        this.state = OneShotState.newInstance();
        this.first = new First<>();
        this.last = new Last<>();
    }

    private Chain(Chain<T> left, Chain<T> right) {
        this();

        var firstValueLeft = left.getFirst().getNext();
        var lastValueLeft = left.getLast().getPrevious();
        var firstValueRight = right.getFirst().getNext();
        var lastValueRight = right.getLast().getPrevious();

        getFirst().setNext(firstValueLeft);
        getLast().setPrevious(lastValueRight);

        lastValueLeft.ifPresent(lastLeft -> firstValueRight.ifPresent(firstRight -> {
            lastLeft.setNext(firstRight);
            firstRight.setPrevious(lastLeft);
        }));
    }

    @NotNull
    private First<T> getFirst() {
        return first;
    }

    @NotNull
    private Last<T> getLast() {
        return last;
    }

    public void addFirst(@NotNull T t) {
        state.shouldBeInit(EXCEPTION_SUPPLIER);

        var first = getFirst();
        var oldNextFirstOrLast = first.getNextAsLastSide().orElse(this::getLast);

        var newNextFirst = new Value<>(t, first, oldNextFirstOrLast);
        first.setNext(newNextFirst);
        oldNextFirstOrLast.setPrevious(newNextFirst);

    }

    public void addLast(@NotNull T t) {
        state.shouldBeInit(EXCEPTION_SUPPLIER);

        var last = getLast();
        var oldPreviousLastOrFirst = last.getPreviousAsFirstSide().orElse(this::getFirst);

        var newPreviousLst = new Value<>(t, oldPreviousLastOrFirst, last);
        oldPreviousLastOrFirst.setNext(newPreviousLst);
        last.setPrevious(newPreviousLst);
    }

    public Iterable<T> asIterable() {
        return () -> new Iterator<>() {
            private Option<Value<T>> next = getFirst().getNext();

            @Override
            public boolean hasNext() {
                return next.isPresent();
            }

            @Override
            public T next() {
                Value<T> valueNode = next.orElseThrow();
                next = valueNode.getNext();
                return valueNode.value();
            }
        };
    }

    public Iterable<T> asIterableReversed() {
        return () -> new Iterator<>() {
            private Option<Value<T>> previous = getLast().getPrevious();

            @Override
            public boolean hasNext() {
                return previous.isPresent();
            }

            @Override
            public T next() {
                Value<T> valueNode = previous.orElseThrow();
                previous = valueNode.getPrevious();
                return valueNode.value();
            }
        };
    }

    public Chain<T> concatFirst(@NotNull Chain<T> other) {
        return concat(other, this);
    }

    public Chain<T> concatLast(@NotNull Chain<T> other) {
        return concat(this, other);
    }

    public static <T> Chain<T> concat(@NotNull Chain<T> c1, @NotNull Chain<T> c2) {
        c1.state.transitionThrowing(EXCEPTION_SUPPLIER);
        c2.state.transitionThrowing(EXCEPTION_SUPPLIER);
        return new Chain<>(c1, c2);
    }
}
