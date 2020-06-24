package raystark.eflib.function.recursive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import raystark.eflib.function.P1;

@FunctionalInterface
public interface RP1<T1> {
    @NotNull
    BooleanTailCall test(@Nullable T1 t1, @NotNull RP1<T1> self);

    @NotNull
    default BooleanTailCall test(@Nullable T1 t1) {
        return test(t1, this);
    }

    @NotNull
    static <T1> P1<T1> of(@NotNull RP1<T1> rp1) {
        return t1 -> rp1.test(t1).evaluate();
    }
}
