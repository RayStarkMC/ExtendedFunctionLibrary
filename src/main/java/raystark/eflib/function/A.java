package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface A {
    void done();

    @NotNull
    default A next(@NotNull A after) {
        return () -> {done(); after.done();};
    }

    @NotNull
    static A of(@NotNull A a) {
        return a;
    }
}
