package raystark.eflib.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import raystark.eflib.function.C2;
import raystark.eflib.test.util.MutableData;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests for C2(2-variable Consumer).")
class TestC2 {
    private final MutableData<Integer> data;

    TestC2() {
        data = new MutableData<>();
    }

    private void addBySum(int a, int b) {
        //noinspection ConstantConditions
        data.setValue(data.getValue()+a+b);
    }
    private void timesBySum(int a, int b) {
        //noinspection ConstantConditions
        data.setValue(data.getValue()*(a+b));
    }
    private void timesBy2() {
        //noinspection ConstantConditions
        data.setValue(data.getValue()*2);
    }
    private void addBySub(int a, int b) {
        //noinspection ConstantConditions
        data.setValue(data.getValue()+(a-b));
    }
    private void setBy0() {
        data.setValue(0);
    }
    private void setBy1() {
        data.setValue(1);
    }

    @Test
    void accept() {
        setBy0();
        var c2 = C2.of(this::addBySum);
        c2.accept(1, 2);
        assertEquals(3, data.getValue());
    }

    @Test
    void apply() {
        setBy0();
        var c1 = C2.of(this::addBySum).apply(1);
        c1.accept(2);
        assertEquals(3, data.getValue());
    }

    @Test
    void nextA() {
        setBy1();
        var c2 = C2.of(this::addBySum).next(this::timesBy2);
        c2.accept(1, 2);
        assertEquals(8, data.getValue());
    }

    @Test
    void nextC2() {
        setBy1();
        var c2 = C2.of(this::addBySum).next(this::timesBySum);
        c2.accept(1, 2);
        assertEquals(12, data.getValue());
    }

    @Test
    void prevA() {
        setBy1();
        var c2 = C2.of(this::addBySum).prev(this::timesBy2);
        c2.accept(1, 2);
        assertEquals(5, data.getValue());
    }

    @Test
    void prevC2() {
        setBy1();
        var c2 = C2.of(this::addBySum).prev(this::timesBySum);
        c2.accept(1, 2);
        assertEquals(6, data.getValue());
    }

    @Test
    void compose1() {
        setBy0();
        var c2 = C2.of(this::addBySum).<String>compose1(Integer::parseInt);
        c2.accept("1", 2);
        assertEquals(3, data.getValue());
    }

    @Test
    void compose2() {
        setBy0();
        var c2 = C2.of(this::addBySum).<String>compose2(Integer::parseInt);
        c2.accept(1, "2");
        assertEquals(3, data.getValue());
    }

    @Test
    void swap2() {
        setBy0();
        var c1 = C2.of(this::addBySub).swap2().apply(1);
        c1.accept(5);
        assertEquals(4, data.getValue());
    }

    @Test
    void asA() {
        setBy0();
        var a = C2.of(this::addBySum).asA(1, 2);
        a.run();
        assertEquals(3, data.getValue());
    }
}
