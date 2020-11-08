package raystark.eflib.visitor.definition.di;

import raystark.eflib.function.notnull.NF1;
import raystark.eflib.function.notnull.NF2;
import raystark.eflib.visitor.acceptor.Acceptor3;
import raystark.eflib.visitor.type.Type1;
import raystark.eflib.visitor.type.Type2;
import raystark.eflib.visitor.type.Type3;

public interface DiDefinition3<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
    R dispatch(Type1<T1> arg1, Type1<T1> arg2);
    R dispatch(Type1<T1> arg1, Type2<T2> arg2);
    R dispatch(Type1<T1> arg1, Type3<T3> arg2);
    R dispatch(Type2<T2> arg1, Type1<T1> arg2);
    R dispatch(Type2<T2> arg1, Type2<T2> arg2);
    R dispatch(Type2<T2> arg1, Type3<T3> arg2);
    R dispatch(Type3<T3> arg1, Type1<T1> arg2);
    R dispatch(Type3<T3> arg1, Type2<T2> arg2);
    R dispatch(Type3<T3> arg1, Type3<T3> arg2);

    static <T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R>
    BuilderT11<T, T1, T2, T3, R> builder() {
        return f11 -> f12 -> f13 ->
               f21 -> f22 -> f23 ->
               f31 -> f32 -> f33 -> new DiDefinition3<>() {
            @Override
            public R dispatch(Type1<T1> arg1, Type1<T1> arg2) {
                return f11.apply(arg1.get(), arg2.get());
            }

            @Override
            public R dispatch(Type1<T1> arg1, Type2<T2> arg2) {
                return f12.apply(arg1.get(), arg2.get());
            }

            @Override
            public R dispatch(Type1<T1> arg1, Type3<T3> arg2) {
                return f13.apply(arg1.get(), arg2.get());
            }

            @Override
            public R dispatch(Type2<T2> arg1, Type1<T1> arg2) {
                return f21.apply(arg1.get(), arg2.get());
            }

            @Override
            public R dispatch(Type2<T2> arg1, Type2<T2> arg2) {
                return f22.apply(arg1.get(), arg2.get());
            }

            @Override
            public R dispatch(Type2<T2> arg1, Type3<T3> arg2) {
                return f23.apply(arg1.get(), arg2.get());
            }

            @Override
            public R dispatch(Type3<T3> arg1, Type1<T1> arg2) {
                return f31.apply(arg1.get(), arg2.get());
            }

            @Override
            public R dispatch(Type3<T3> arg1, Type2<T2> arg2) {
                return f32.apply(arg1.get(), arg2.get());
            }

            @Override
            public R dispatch(Type3<T3> arg1, Type3<T3> arg2) {
                return f33.apply(arg1.get(), arg2.get());
            }
        };
    }

    static <T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R>
    DiDefinition3<T, T1, T2, T3, R> build(NF1<BuilderT11<T, T1, T2, T3, R>, DiDefinition3<T, T1, T2, T3, R>> builder) {
        return builder.apply(builder());
    }

    interface BuilderT11<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        BuilderT12<T, T1, T2, T3, R> type11(NF2<T1, T1, R> f2);
    }
    interface BuilderT12<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        BuilderT13<T, T1, T2, T3, R> type12(NF2<T1, T2, R> f2);
    }
    interface BuilderT13<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        BuilderT21<T, T1, T2, T3, R> type21(NF2<T1, T3, R> f2);
    }
    interface BuilderT21<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        BuilderT22<T, T1, T2, T3, R> type22(NF2<T2, T1, R> f2);
    }
    interface BuilderT22<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        BuilderT23<T, T1, T2, T3, R> type11(NF2<T2, T2, R> f2);
    }
    interface BuilderT23<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        BuilderT31<T, T1, T2, T3, R> type12(NF2<T2, T3, R> f2);
    }
    interface BuilderT31<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        BuilderT32<T, T1, T2, T3, R> type21(NF2<T3, T1, R> f2);
    }
    interface BuilderT32<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        BuilderT33<T, T1, T2, T3, R> type22(NF2<T3, T2, R> f2);
    }
    interface BuilderT33<T extends Acceptor3<T, T1, T2, T3>, T1 extends T, T2 extends T, T3 extends T, R> {
        DiDefinition3<T, T1, T2, T3, R> type22(NF2<T3, T3, R> f2);
    }
}
