package raystark.eflib.util;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.option.Option;

interface FirstSideNode<T> extends Node<T> {
    void setNext(Value<T> next);

    @NotNull Option<Value<T>> getNext();
}
