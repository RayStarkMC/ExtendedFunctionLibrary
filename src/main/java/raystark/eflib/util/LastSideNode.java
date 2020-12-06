package raystark.eflib.util;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.option.Option;

interface LastSideNode<T> extends Node<T> {
    void setPrevious(Value<T> previous);

    @NotNull Option<Value<T>> getPrevious();
}
