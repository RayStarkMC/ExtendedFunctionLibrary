package raystark.eflib.util;

import raystark.eflib.option.Option;

public interface Node<T> {
    Option<Value<T>> tryCastToValue();
}
