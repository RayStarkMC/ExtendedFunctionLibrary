package raystark.eflib.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import raystark.eflib.function.A;
import raystark.eflib.test.util.MutableData;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests for A(Action).")
final class TestA {
    private final MutableData<Integer> data;

    TestA() {
        data = new MutableData<>();
    }

    private void addBy10() {
        //noinspection ConstantConditions
        data.setValue(data.getValue()+10);
    }
    private void timesBy2() {
        //noinspection ConstantConditions
        data.setValue(data.getValue()*2);
    }
    private void setBy0() {
        data.setValue(0);
    }
    private void setBy1() {
        data.setValue(1);
    }

    @Test
    void run() {
        setBy0();
        var a = A.of(this::addBy10);
        a.run();
        assertEquals(10, data.getValue());
    }

    @Test
    void next() {
        setBy1();
        var a = A.of(this::addBy10).next(this::timesBy2);
        a.run();
        assertEquals(22, data.getValue());
    }

    @Test
    void prev() {
        setBy1();
        var a = A.of(this::addBy10).prev(this::timesBy2);
        a.run();
        assertEquals(12, data.getValue());
    }
}
