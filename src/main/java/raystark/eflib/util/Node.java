package raystark.eflib.util;

import raystark.eflib.option.Option;

interface Node<T> {
    Option<Value<T>> tryCastToValue();
}
