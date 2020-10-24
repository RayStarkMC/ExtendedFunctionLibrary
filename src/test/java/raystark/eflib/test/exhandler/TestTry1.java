package raystark.eflib.test.exhandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import raystark.eflib.exhandler.Try1;
import raystark.eflib.test.util.Exceptions.CheckException1;
import raystark.eflib.test.util.MutableData;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@DisplayName("Tests for Try1.")
class TestTry1 {
    private final MutableData<Integer> data;

    private int throwing1() throws CheckException1 {
        throw new CheckException1();
    }
    private int handleReturning10(CheckException1 x) {
        return 10;
    }
    private void handleFinallySetting20() {
        data.setValue(20);
    }

    TestTry1() {
        this.data = new MutableData<>();
    }

    @BeforeEach
    void setUp() {
        data.setValue(null);
    }

    @Test
    void rawGet() {
        var try1 = Try1.builder(CheckException1.class).build(this::throwing1);
        assertThrows(Exception.class, try1::rawGet);
    }

    @Test
    void recover1() {
        var try1 = Try1.builder(CheckException1.class).build(this::throwing1);
        var s = try1.recover1(this::handleReturning10, this::handleFinallySetting20);
        assertDoesNotThrow(() -> {
            var ret = s.get();
            assertEquals(10, ret);
        });
        assertEquals(20, data.getValue());
    }
}
