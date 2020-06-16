package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.A;

@FunctionalInterface
public interface RA {

    @NotNull
    VoidTailCall run(@NotNull RA self);

    @NotNull
    default VoidTailCall run() {
        return run(this);
    }

    @NotNull
    static A of(@NotNull RA ra) {
        return () -> ra.run().evaluate();
    }

    @NotNull
    static A of(@NotNull TailCallA ra) {
        return () -> ra.run().evaluate();
    }

    @FunctionalInterface
    interface TailCallA {
        @NotNull
        VoidTailCall run();
    }
}
