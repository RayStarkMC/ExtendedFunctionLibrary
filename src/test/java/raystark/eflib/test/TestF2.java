package raystark.eflib.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import raystark.eflib.function.C1;
import raystark.eflib.function.F1;
import raystark.eflib.function.F2;
import raystark.eflib.test.util.MutableData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@DisplayName("Tests for F2(2-variable Function).")
class TestF2 {
    private int sum(int a, int b) {
        return a+b;
    }
    private int apply10(F1<Integer, Integer> f1) {
        //noinspection ConstantConditions
        return f1.apply(10);
    }
    private int timesBy5(int a) {
        return a*5;
    }
    private String concat(String a, String b) {
        return a.concat(b);
    }
    private boolean isPositive(int a) {
        return a>0;
    }
    private boolean isPositiveWhenApplying10(F1<Integer, Integer> f1) {
        return isPositive(apply10(f1));
    }

    @Test
    void apply() {
        var f2 = F2.of(this::sum);
        var ret = f2.apply(1, 2);
        assertEquals(3, ret);
    }

    @Test
    void apply1VariablePartially() {
        var f1 = F2.of(this::sum).apply(1);
        var ret = f1.apply(2);
        assertEquals(3, ret);
    }

    @Test
    void then1() {
        var f1 = F2.of(this::sum).then1(this::apply10);
        var ret = f1.apply(1);
        assertEquals(11, ret);
    }

    @Test
    void then2() {
        var f2 = F2.of(this::sum).then2(this::timesBy5);
        var ret = f2.apply(1, 2);
        assertEquals(15, ret);
    }

    @Test
    void compose1() {
        var f2 = F2.of(this::sum).<String>compose1(Integer::parseInt);
        var ret = f2.apply("1", 2);
        assertEquals(3, ret);
    }

    @Test
    void compose2() {
        var f2 = F2.of(this::sum).<String>compose2(Integer::parseInt);
        var ret = f2.apply(1, "2");
        assertEquals(3, ret);
    }

    @Test
    void swap2() {
        var f2 = F2.of(this::concat).swap2();
        var ret = f2.apply("1", "2");
        assertEquals("21", ret);
    }

    @Test
    void asP1() {
        var p1 = F2.of(this::sum).asP1(this::isPositiveWhenApplying10);
        var ret = p1.test(1);
        assertTrue(ret);
    }

    @Test
    void asP2() {
        var p2 = F2.of(this::sum).asP2(this::isPositive);
        var ret = p2.test(1, 2);
        assertTrue(ret);
    }

    @Test
    void asS1() {
        var s = F2.of(this::sum).asS(1);
        //noinspection ConstantConditions
        var ret = s.get().apply(2);
        assertEquals(3, ret);
    }

    @Test
    void asS2() {
        var s = F2.of(this::sum).asS(1, 2);
        var ret = s.get();
        assertEquals(3, ret);
    }

    @Test
    void asC1() {
        var data = new MutableData<Integer>();
        var apply10AndSetValue = F1.of(this::apply10).asC1(data::setValue);
        var c1 = F2.of(this::sum).asC1(apply10AndSetValue);
        c1.accept(1);
        assertEquals(11, data.getValue());
    }

    @Test
    void asC2() {
        var data = new MutableData<>();
        var c2 = F2.of(this::sum).asC2(data::setValue);
        c2.accept(1, 2);
        assertEquals(3, data.getValue());
    }
}
