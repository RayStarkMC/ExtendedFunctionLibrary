package raystark.eflib.test.exhandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import raystark.eflib.exhandler.Try2;
import raystark.eflib.test.util.Exceptions.CheckException1;
import raystark.eflib.test.util.Exceptions.CheckException2;
import raystark.eflib.test.util.MutableData;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@DisplayName("Tests for Try2.")
class TestTry2 {
    private final MutableData<Integer> data1;

    private int throwing1() throws CheckException1, CheckException2 {
        throw new CheckException1();
    }
    private int throwing2() throws  CheckException1, CheckException2 {
        throw new CheckException2();
    }
    private int handle1Returning10(CheckException1 x) {
        return 10;
    }
    private int handle2Returning15(CheckException2 x) {
        return 15;
    }
    private int handle1NeverCalled(CheckException1 x) {
        return -1;
    }
    private void handleFinallySetting20() {
        data1.setValue(20);
    }

    TestTry2() {
        this.data1 = new MutableData<>();
    }

    @BeforeEach
    void setUp() {
        data1.setValue(null);
    }

    @Test
    void rawGet() {
        var try2 = Try2.builder(CheckException1.class, CheckException2.class).build(this::throwing1);
        assertThrows(CheckException1.class, try2::rawGet);
    }

    @Test
    void recover1() {
        var try2 = Try2.builder(CheckException1.class, CheckException2.class).build(this::throwing1);
        var try1 = try2.recover1(this::handle1Returning10, this::handleFinallySetting20);
        assertDoesNotThrow(() -> {
            var ret = try1.rawGet();
            assertEquals(10, ret);
        });
        assertEquals(20, data1.getValue());
    }

    @Test
    void recover2() {
        var try2 = Try2.builder(CheckException1.class, CheckException2.class).build(this::throwing2);
        var s = try2.recover2(this::handle1NeverCalled, this::handle2Returning15, this::handleFinallySetting20);
        assertDoesNotThrow(() -> {
            var ret = s.get();
            assertEquals(15, ret);
        });
        assertEquals(20, data1.getValue());
    }

    @Test
    void swap2() {
        var try2 = Try2.builder(CheckException1.class, CheckException2.class).build(this::throwing2);
        var swappedTry2 = try2.swap2();
        var try1 = swappedTry2.recover1(this::handle2Returning15, this::handleFinallySetting20);
        assertDoesNotThrow(() -> {
            var ret = try1.rawGet();
            assertEquals(15, ret);
        });
        assertEquals(20, data1.getValue());
    }
}
