package raystark.eflib.test;

import org.junit.jupiter.api.BeforeEach;
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

    private void timesBy(int value) {
        //noinspection ConstantConditions
        this.data.setValue(data.getValue()*value);
    }

    private void timesBy5() {
        timesBy(5);
    }

    private void set10() {
        data.setValue(10);
    }

    @BeforeEach
    void setUp() {
        data.setValue(0);
    }

    @Test
    void accept(){
        var c1 = C1.of(data::setValue);
        c1.accept(10);
        assertEquals(10, data.getValue());
    }

    @Test
    void nextC1() {
        var c1 = C1.of(data::setValue).next(this::timesBy);
        c1.accept(10);
        assertEquals(100, data.getValue());
    }

    @Test
    void nextA() {
        var c1 = C1.of(data::setValue).next(this::timesBy5);
        c1.accept(10);
        assertEquals(50, data.getValue());
    }

    @Test
    void prevC1() {
        var c1 = C1.of(this::timesBy).prev(data::setValue);
        c1.accept(10);
        assertEquals(100, data.getValue());
    }

    @Test
    void prevA() {
        var c1 = C1.of(this::timesBy).prev(this::set10);
        c1.accept(5);
        assertEquals(50, data.getValue());
    }

    @Test
    void compose1() {
        var c1 = C1.of(data::setValue).<String>compose1(Integer::parseInt);
        c1.accept("10");
        assertEquals(10, data.getValue());
    }

    @Test
    void asA() {
        var a = C1.of(data::setValue).asA(10);
        a.run();
        assertEquals(10, data.getValue());
    }
}
