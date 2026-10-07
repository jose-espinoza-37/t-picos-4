package pe.edu.uni.fc.cc.cc0f7.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pe.edu.uni.fc.cc.cc0f7.common.Utils.gcd;
import static pe.edu.uni.fc.cc.cc0f7.common.Utils.modulo;
import static pe.edu.uni.fc.cc.cc0f7.common.Utils.switchCase;

public class UtilsTest {
    @Test
    public void testModulo() {
        int a = -7;
        int n = 3;
        assertEquals(2, modulo(a, n));
    }

    @Test
    public void testGCD() {
        int a = 9;
        int b = 13;
        assertEquals(1, gcd(a, b));
    }

    @Test
    public void testSwitchCase() {
        char min = 'd';
        assertEquals('D', switchCase(min));
    }
}
