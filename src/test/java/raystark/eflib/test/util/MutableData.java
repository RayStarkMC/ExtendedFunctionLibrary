package raystark.eflib.test.util;

import org.jetbrains.annotations.Nullable;

public final class MutableData<T> {
    private T value;

    public MutableData(@Nullable T value) {
        this.value = value;
    }

    public MutableData() {
        this(null);
    }

    public void setValue(@Nullable T nextValue) {
        this.value = nextValue;
    }

    @Nullable
    public T getValue() {
        return this.value;
    }
}
