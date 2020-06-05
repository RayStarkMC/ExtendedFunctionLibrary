package raystark.eflib.function;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface Action {
    void done();

    @NotNull
    default Action next(@NotNull Action after) {
        return () -> {done(); after.done();};
    }

    @NotNull
    static Action of(@NotNull Action action) {
        return action;
    }
}
