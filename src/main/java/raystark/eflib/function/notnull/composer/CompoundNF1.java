package raystark.eflib.function.notnull.composer;

import org.jetbrains.annotations.NotNull;
import raystark.eflib.function.notnull.NF1;

import java.util.Deque;
import java.util.Iterator;

public class CompoundNF1<T1, R> implements NF1<T1, R> {
    private final Deque<NF1<?, ?>> functions;

    CompoundNF1(Deque<NF1<?, ?>> functions) {
        this.functions = functions;
    }

    @Override
    public @NotNull R apply(@NotNull T1 t1) {
        Iterator<NF1<?, ?>> iterator = functions.iterator();
        Object value = t1;
        while(iterator.hasNext()) {
            //functionsはT1 -> T2, T2 -> T3, ... , TN -> Rのように型が連続した関数のリストであるため安全
            @SuppressWarnings("unchecked")
            var function = (NF1<Object, Object>) iterator.next();
            value = function.apply(value);
        }

        //functionsの最後の関数の型は必ずRであるので安全
        @SuppressWarnings("unchecked")
        var ret = (R) value;
        return ret;
    }
}
