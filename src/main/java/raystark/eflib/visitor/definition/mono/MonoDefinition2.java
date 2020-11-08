package raystark.eflib.visitor.definition.mono;

import raystark.eflib.function.notnull.NF1;
import raystark.eflib.visitor.acceptor.Acceptor2;
import raystark.eflib.visitor.type.Type1;
import raystark.eflib.visitor.type.Type2;

public interface MonoDefinition2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
    R dispatch(Type1<T1> arg1);
    R dispatch(Type2<T2> arg1);

    static <T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R>
    BuilderT1<T, T1, T2, R> builder() {
        return f1 -> f2 -> new MonoDefinition2<>() {
            @Override
            public R dispatch(Type1<T1> arg1) {
                return f1.apply(arg1.get());
            }

            @Override
            public R dispatch(Type2<T2> arg1) {
                return f2.apply(arg1.get());
            }
        };
    }

    interface BuilderT1<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
        BuilderT2<T, T1, T2, R> type1(NF1<T1, R> f1);
    }

    interface BuilderT2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
        MonoDefinition2<T, T1, T2, R> type2(NF1<T2, R> f1);
    }
}
