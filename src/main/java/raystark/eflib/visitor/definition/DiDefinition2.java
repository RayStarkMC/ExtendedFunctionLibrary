package raystark.eflib.visitor.definition;

import raystark.eflib.function.notnull.NF2;
import raystark.eflib.visitor.acceptor.Acceptor2;
import raystark.eflib.visitor.type.Type1;
import raystark.eflib.visitor.type.Type2;

public interface DiDefinition2<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
    R dispatch(Type1<T1> arg1, Type1<T1> arg2);
    R dispatch(Type1<T1> arg1, Type2<T2> arg2);
    R dispatch(Type2<T2> arg1, Type1<T1> arg2);
    R dispatch(Type2<T2> arg1, Type2<T2> arg2);

    static <T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R>
    BuilderT11<T, T1, T2, R> builder() {
        return f11 -> f12 ->
               f21 -> f22 -> new DiDefinition2<>() {
            @Override
            public R dispatch(Type1<T1> arg1, Type1<T1> arg2) {
                return f11.apply(arg1.get(), arg2.get());
            }

            @Override
            public R dispatch(Type1<T1> arg1, Type2<T2> arg2) {
                return f12.apply(arg1.get(), arg2.get());
            }

            @Override
            public R dispatch(Type2<T2> arg1, Type1<T1> arg2) {
                return f21.apply(arg1.get(), arg2.get());
            }

            @Override
            public R dispatch(Type2<T2> arg1, Type2<T2> arg2) {
                return f22.apply(arg1.get(), arg2.get());
            }
        };
    }

    interface BuilderT11<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
        BuilderT12<T, T1, T2, R> type11(NF2<T1, T1, R> f2);
    }
    interface BuilderT12<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
        BuilderT21<T, T1, T2, R> type12(NF2<T1, T2, R> f2);
    }
    interface BuilderT21<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
        BuilderT22<T, T1, T2, R> type21(NF2<T2, T1, R> f2);
    }
    interface BuilderT22<T extends Acceptor2<T, T1, T2>, T1 extends T, T2 extends T, R> {
        DiDefinition2<T, T1, T2, R> type22(NF2<T2, T2, R> f2);
    }
}
