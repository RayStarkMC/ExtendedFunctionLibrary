package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface A {
    void run();

    @NotNull
    default A next(@NotNull A after) {
        return () -> {
            this.run(); after.run();
        };
    }

    @NotNull
    default A prev(@NotNull A before) {
        return () -> {
            before.run(); this.run();
        };
    }

    @NotNull
    static A of(@NotNull A a) {
        return a;
    }
}
