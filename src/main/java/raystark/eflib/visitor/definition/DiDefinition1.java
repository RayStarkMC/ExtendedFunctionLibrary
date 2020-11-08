package raystark.eflib.visitor.definition;

import raystark.eflib.function.notnull.NF2;
import raystark.eflib.visitor.acceptor.Acceptor1;
import raystark.eflib.visitor.type.Type1;

public interface DiDefinition1<T extends Acceptor1<T, T1>, T1 extends T, R> {
    R dispatch(Type1<T1> arg1, Type1<T1> arg2);

    static <T extends Acceptor1<T, T1>, T1 extends T, R>
    BuilderT11<T, T1, R> builder() {
        //noinspection Convert2Lambda
        return f11 -> new DiDefinition1<>() {
            @Override
            public R dispatch(Type1<T1> arg1, Type1<T1> arg2) {
                return f11.apply(arg1.get(), arg2.get());
            }
        };
    }

    interface BuilderT11<T extends Acceptor1<T, T1>, T1 extends T, R> {
        DiDefinition1<T, T1, R> type11(NF2<T1, T1, R> f11);
    }
}
