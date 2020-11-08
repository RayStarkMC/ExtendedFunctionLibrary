package raystark.eflib.visitor.definition.mono;

import raystark.eflib.function.notnull.NF1;
import raystark.eflib.visitor.acceptor.Acceptor3;
import raystark.eflib.visitor.type.Type1;
import raystark.eflib.visitor.type.Type2;
import raystark.eflib.visitor.type.Type3;

public interface MonoDefinition3<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
    R dispatch(Type1<T1> arg1);
    R dispatch(Type2<T2> arg1);
    R dispatch(Type3<T3> arg1);

    static <T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R>
    BuilderT1<T, T1, T2, T3, R> builder() {
        return f1 -> f2 -> f3 -> new MonoDefinition3<>() {
            @Override
            public R dispatch(Type1<T1> arg1) {
                return f1.apply(arg1.get());
            }

            @Override
            public R dispatch(Type2<T2> arg1) {
                return f2.apply(arg1.get());
            }

            @Override
            public R dispatch(Type3<T3> arg1) {
                return f3.apply(arg1.get());
            }
        };
    }

    interface BuilderT1<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        BuilderT2<T, T1, T2, T3, R> type1(NF1<T1, R> f1);
    }
    interface BuilderT2<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        BuilderT3<T, T1, T2, T3, R> type2(NF1<T2, R> f1);
    }
    interface BuilderT3<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        MonoDefinition3<T, T1, T2, T3, R> type3(NF1<T3, R> f1);
    }
}
