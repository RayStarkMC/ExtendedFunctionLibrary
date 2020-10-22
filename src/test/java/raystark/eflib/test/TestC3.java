package raystark.eflib.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import raystark.eflib.function.C3;
import raystark.eflib.test.util.MutableData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@DisplayName("Tests for C3(3-variable Consumer).")
class TestC3 {
    private final MutableData<Integer> data;

    TestC3() {
        data = new MutableData<>();
    }

    private void setBy0() {
        data.setValue(0);
    }
    private void setBy1() {
        data.setValue(1);
    }
    private void addBySum(int a, int b, int c) {
        //noinspection ConstantConditions
        data.setValue(data.getValue()+a+b+c);
    }
    private void timesBySum(int a, int b, int c) {
        //noinspection ConstantConditions
        data.setValue(data.getValue()*(a+b+c));
    }
    private void timesBy5() {
        //noinspection ConstantConditions
        data.setValue(data.getValue()*5);
    }
    private void pushDigit(int a, int b, int c) {
        //noinspection ConstantConditions
        data.setValue(data.getValue()*1000 + a*100 + b*10 + c);
    }

    @Test
    void accept() {
        setBy0();
        var c3 = C3.of(this::addBySum);
        c3.accept(1, 2, 3);
        assertEquals(6, data.getValue());
    }

    @Test
    void apply1VariablePartially() {
        setBy0();
        var c2 = C3.of(this::addBySum).apply(1);
        c2.accept(2, 3);
        assertEquals(6, data.getValue());
    }

    @Test
    void apply2VariablesPartially() {
        setBy0();
        var c1 = C3.of(this::addBySum).apply(1, 2);
        c1.accept(3);
        assertEquals(6, data.getValue());
    }

    @Test
    void nextC3() {
        setBy1();
        var c3 = C3.of(this::addBySum).next(this::timesBySum);
        c3.accept(1, 2, 3);
        assertEquals(42, data.getValue());
    }

    @Test
    void nextA() {
        setBy1();
        var c3 = C3.of(this::addBySum).next(this::timesBy5);
        c3.accept(1, 2, 3);
        assertEquals(35, data.getValue());
    }

    @Test
    void prevC3() {
        setBy1();
        var c3 = C3.of(this::addBySum).prev(this::timesBySum);
        c3.accept(1, 2, 3);
        assertEquals(12, data.getValue());
    }

    @Test
    void prevA() {
        setBy1();
        var c3 = C3.of(this::addBySum).prev(this::timesBy5);
        c3.accept(1, 2, 3);
        assertEquals(11, data.getValue());
    }

    @Test
    void compose1() {
        setBy0();
        var c3 = C3.of(this::addBySum).<String>compose1(Integer::parseInt);
        c3.accept("1", 2, 3);
        assertEquals(6, data.getValue());
    }

    @Test
    void compose2() {
        setBy0();
        var c3 = C3.of(this::addBySum).<String>compose2(Integer::parseInt);
        c3.accept(1, "2", 3);
        assertEquals(6, data.getValue());
    }

    @Test
    void compose3() {
        setBy0();
        var c3 = C3.of(this::addBySum).<String>compose3(Integer::parseInt);
        c3.accept(1, 2, "3");
        assertEquals(6, data.getValue());
    }

    @Test
    void swap2() {
        setBy0();
        var c2 = C3.of(this::pushDigit).swap2().apply(1);
        c2.accept(2, 3);
        assertEquals(213, data.getValue());
    }

    @Test
    void swap3() {
        setBy0();
        var c2 = C3.of(this::pushDigit).swap3().apply(1);
        c2.accept(2, 3);
        assertEquals(321, data.getValue());
    }

    @Test
    void asA() {
        setBy0();
        var a = C3.of(this::addBySum).asA(1, 2, 3);
        a.run();
        assertEquals(6, data.getValue());
    }
}
