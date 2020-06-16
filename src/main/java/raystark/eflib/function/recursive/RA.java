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
        return () -> ra.run().execute();
    }

    @NotNull
    static A of(@NotNull TailCallA ra) {
        return () -> ra.run().execute();
    }

    @FunctionalInterface
    interface TailCallA {
        @NotNull
        VoidTailCall run();
    }
}
