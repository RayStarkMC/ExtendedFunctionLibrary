package raystark.eflib.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import raystark.eflib.function.C1;
import raystark.eflib.test.util.MutableData;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests for C1(1-variable Consumer).")
class TestC1 {
    private final MutableData<Integer> data;

    TestC1() {
        this.data = new MutableData<>();
    }

    private void addBy(int a) {
        //noinspection ConstantConditions
        data.setValue(data.getValue()+a);
    }
    private void timesBy(int value) {
        //noinspection ConstantConditions
        this.data.setValue(data.getValue()*value);
    }
    private void timesBy5() {
        timesBy(5);
    }
    private void setBy0() {
        data.setValue(0);
    }
    private void setBy1() {
        data.setValue(1);
    }

    @Test
    void accept(){
        setBy0();
        var c1 = C1.of(this::addBy);
        c1.accept(10);
        assertEquals(10, data.getValue());
    }

    @Test
    void nextC1() {
        setBy1();
        var c1 = C1.of(this::addBy).next(this::timesBy);
        c1.accept(10);
        assertEquals(110, data.getValue());
    }

    @Test
    void nextA() {
        setBy1();
        var c1 = C1.of(this::addBy).next(this::timesBy5);
        c1.accept(10);
        assertEquals(55, data.getValue());
    }

    @Test
    void prevC1() {
        setBy1();
        var c1 = C1.of(this::addBy).prev(this::timesBy);
        c1.accept(10);
        assertEquals(20, data.getValue());
    }

    @Test
    void prevA() {
        setBy1();
        var c1 = C1.of(this::addBy).prev(this::timesBy5);
        c1.accept(10);
        assertEquals(15, data.getValue());
    }

    @Test
    void compose1() {
        setBy0();
        var c1 = C1.of(this::addBy).<String>compose1(Integer::parseInt);
        c1.accept("10");
        assertEquals(10, data.getValue());
    }

    @Test
    void asA() {
        setBy0();
        var a = C1.of(this::addBy).asA(10);
        a.run();
        assertEquals(10, data.getValue());
    }
}
