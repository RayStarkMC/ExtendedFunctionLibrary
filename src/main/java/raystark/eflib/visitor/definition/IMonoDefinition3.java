package raystark.eflib.visitor.definition;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;
import raystark.eflib.visitor.acceptor.Acceptor3;
import raystark.eflib.visitor.type.Type1;
import raystark.eflib.visitor.type.Type2;
import raystark.eflib.visitor.type.Type3;

public interface IMonoDefinition3<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
    @NotNull R dispatch(@NotNull Type1<T1> arg1);
    @NotNull R dispatch(@NotNull Type2<T2> arg1);
    @NotNull R dispatch(@NotNull Type3<T3> arg1);
}
