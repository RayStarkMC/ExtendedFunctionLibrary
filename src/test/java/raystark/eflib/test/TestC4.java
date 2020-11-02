package raystark.eflib.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import raystark.eflib.function.C4;
import raystark.eflib.test.util.MutableData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@DisplayName("Tests for C3(3-variable Consumer).")
class TestC4 {
    private final MutableData<Integer> data;

    TestC4() {
        data = new MutableData<>();
    }

    private void setBy0() {
        data.setValue(0);
    }
    private void setBy1() {
        data.setValue(1);
    }
    private void addBySum(int a, int b, int c, int d) {
        //noinspection ConstantConditions
        data.setValue(data.getValue()+a+b+c+d);
    }
    private void timesBySum(int a, int b, int c, int d) {
        //noinspection ConstantConditions
        data.setValue(data.getValue()*(a+b+c+d));
    }
    private void timesBy5() {
        //noinspection ConstantConditions
        data.setValue(data.getValue()*5);
    }
    private void pushDigit(int a, int b, int c, int d) {
        //noinspection ConstantConditions
        data.setValue(data.getValue()*10000 + a*1000 + b*100 + c*10 + d);
    }

    @Test
    void accept() {
        setBy0();
        var c4 = C4.of(this::addBySum);
        c4.accept(1, 2, 3, 4);
        assertEquals(10, data.getValue());
    }

    @Test
    void apply1VariablePartially() {
        setBy0();
        var c3 = C4.of(this::addBySum).apply(1);
        c3.accept(2, 3, 4);
        assertEquals(10, data.getValue());
    }

    @Test
    void apply2VariablesPartially() {
        setBy0();
        var c2 = C4.of(this::addBySum).apply(1, 2);
        c2.accept(3, 4);
        assertEquals(10, data.getValue());
    }

    @Test
    void apply3VariablesPartially() {
        setBy0();
        var c1 = C4.of(this::addBySum).apply(1, 2, 3);
        c1.accept(4);
        assertEquals(10, data.getValue());
    }

    @Test
    void nextC4() {
        setBy1();
        var c4 = C4.of(this::addBySum).next(this::timesBySum);
        c4.accept(1, 2, 3, 4);
        assertEquals(110, data.getValue());
    }

    @Test
    void nextA() {
        setBy1();
        var c4 = C4.of(this::addBySum).next(this::timesBy5);
        c4.accept(1, 2, 3, 4);
        assertEquals(55, data.getValue());
    }

    @Test
    void prevC4() {
        setBy1();
        var c4 = C4.of(this::addBySum).prev(this::timesBySum);
        c4.accept(1, 2, 3, 4);
        assertEquals(20, data.getValue());
    }

    @Test
    void prevA() {
        setBy1();
        var c4 = C4.of(this::addBySum).prev(this::timesBy5);
        c4.accept(1, 2, 3, 4);
        assertEquals(15, data.getValue());
    }

    @Test
    void compose1() {
        setBy0();
        var c4 = C4.of(this::addBySum).<String>compose1(Integer::parseInt);
        c4.accept("1", 2, 3, 4);
        assertEquals(10, data.getValue());
    }

    @Test
    void compose2() {
        setBy0();
        var c4 = C4.of(this::addBySum).<String>compose2(Integer::parseInt);
        c4.accept(1, "2", 3, 4);
        assertEquals(10, data.getValue());
    }

    @Test
    void compose3() {
        setBy0();
        var c4 = C4.of(this::addBySum).<String>compose3(Integer::parseInt);
        c4.accept(1, 2, "3", 4);
        assertEquals(10, data.getValue());
    }

    @Test
    void compose4() {
        setBy0();
        var c4 = C4.of(this::addBySum).<String>compose4(Integer::parseInt);
        c4.accept(1, 2, 3, "4");
        assertEquals(10, data.getValue());
    }

    @Test
    void swap2() {
        setBy0();
        var c4 = C4.of(this::pushDigit).swap2();
        c4.accept(1, 2, 3, 4);
        assertEquals(2134, data.getValue());
    }

    @Test
    void swap3() {
        setBy0();
        var c4 = C4.of(this::pushDigit).swap3();
        c4.accept(1, 2, 3, 4);
        assertEquals(3214, data.getValue());
    }

    @Test
    void swap4() {
        setBy0();
        var c4 = C4.of(this::pushDigit).swap4();
        c4.accept(1, 2, 3, 4);
        assertEquals(4231, data.getValue());
    }

    @Test
    void asA() {
        setBy0();
        var a = C4.of(this::addBySum).asA(1, 2, 3, 4);
        a.run();
        assertEquals(10, data.getValue());
    }
}
