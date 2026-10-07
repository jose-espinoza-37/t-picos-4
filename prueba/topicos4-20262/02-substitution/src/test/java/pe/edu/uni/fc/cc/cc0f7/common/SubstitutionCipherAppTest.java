package pe.edu.uni.fc.cc.cc0f7.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pe.edu.uni.fc.cc.cc0f7.substitution.SubstitutionCipherApp.indexOfCharsMap;

public class SubstitutionCipherAppTest {
    @Test
    public void testIndexOfCharacter() {
        char c = 'q';
        assertEquals(0, indexOfCharsMap(c));
    }
}
