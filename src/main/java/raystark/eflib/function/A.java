package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface A {
    void run();

    @NotNull
    default A next(@NotNull A after) {
        return () -> {
            run(); after.run();
        };
    }

    @NotNull
    static A of(@NotNull A a) {
        return a;
    }
}
