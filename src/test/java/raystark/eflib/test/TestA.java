package raystark.eflib.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import raystark.eflib.function.A;
import raystark.eflib.test.util.MutableData;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests for A(Action).")
final class TestA {
    private final MutableData<Integer> data;

    TestA() {
        data = new MutableData<>(0);
    }

    private void set0() {
        data.setValue(0);
    }
    private void add10() {
        //noinspection ConstantConditions
        data.setValue(data.getValue()+10);
    }
    private void timesBy2() {
        //noinspection ConstantConditions
        data.setValue(data.getValue()*2);
    }

    @BeforeEach
    void setup() {
        set0();
    }

    @Test
    void run() {
        var a = A.of(this::add10);
        a.run();
        assertEquals(10, data.getValue());
    }

    @Test
    void next() {
        var a = A.of(this::add10).next(this::timesBy2);
        a.run();
        assertEquals(20, data.getValue());
    }

    @Test
    void prev() {
        var a = A.of(this::timesBy2).prev(this::add10);
        a.run();
        assertEquals(20, data.getValue());
    }
}
