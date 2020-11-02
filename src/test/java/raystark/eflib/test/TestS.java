package raystark.eflib.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import raystark.eflib.function.S;
import raystark.eflib.test.util.MutableData;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests for S(Supplier).")
class TestS {
    private int supply10() {
        return 10;
    }

    @Test
    void get() {
        var s = S.of(this::supply10);
        assertEquals(10, s.get());
    }

    @Test
    void then() {
        var s = S.of(this::supply10).then(String::valueOf);
        assertEquals("10", s.get());
    }

    @Test
    void asA() {
        var data = new MutableData<>(0);
        var a = S.of(this::supply10).asA(data::setValue);
        a.run();
        assertEquals(10, data.getValue());
    }
}
