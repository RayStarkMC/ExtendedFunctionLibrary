package raystark.eflib.visitor.definition;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.visitor.acceptor.Acceptor2;
import raystark.eflib.type.Type1;
import raystark.eflib.type.Type2;

public interface IDiDefinition2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
    @NotNull R dispatch(@NotNull Type1<T1> arg1, @NotNull Type1<T1> arg2);
    @NotNull R dispatch(@NotNull Type1<T1> arg1, @NotNull Type2<T2> arg2);
    @NotNull R dispatch(@NotNull Type2<T2> arg1, @NotNull Type1<T1> arg2);
    @NotNull R dispatch(@NotNull Type2<T2> arg1, @NotNull Type2<T2> arg2);
}
