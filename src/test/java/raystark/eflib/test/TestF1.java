package raystark.eflib.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import raystark.eflib.function.F1;
import raystark.eflib.test.util.MutableData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@DisplayName("Tests for F1(1-variable Function).")
class TestF1 {

    private int addBy2(int a) {
        return a+2;
    }
    private int timesBy5(int a) {
        return a*5;
    }
    private boolean isPositive(int a) {
        return a>0;
    }

    @Test
    void apply() {
        var f1 = F1.of(this::addBy2);
        var ret = f1.apply(1);
        assertEquals(3, ret);
    }

    @Test
    void then1() {
        var f1 = F1.of(this::addBy2).then1(this::timesBy5);
        var ret = f1.apply(1);
        assertEquals(15, ret);
    }

    @Test
    void compose1() {
        var f1 = F1.of(this::addBy2).<String>compose1(Integer::parseInt);
        var ret = f1.apply("1");
        assertEquals(3, ret);
    }

    @Test
    void asP1() {
        var p1 = F1.of(this::addBy2).asP1(this::isPositive);
        var ret = p1.test(1);
        assertTrue(ret);
    }

    @Test
    void asS() {
        var s = F1.of(this::addBy2).asS(1);
        var ret = s.get();
        assertEquals(3, ret);
    }

    @Test
    void asC1() {
        var data = new MutableData<Integer>();
        var c1 = F1.of(this::addBy2).asC1(data::setValue);
        c1.accept(1);
        assertEquals(3, data.getValue());
    }
}
