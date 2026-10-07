package pe.edu.uni.fc.cc.cc0f7.common;

import org.junit.jupiter.api.Test;
import pe.edu.uni.fc.cc.cc0f7.substitution.CaesarCipherApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pe.edu.uni.fc.cc.cc0f7.common.Utils.modulo;
import static pe.edu.uni.fc.cc.cc0f7.substitution.CaesarCipherApp.encrypt;
import static pe.edu.uni.fc.cc.cc0f7.substitution.CaesarCipherApp.decrypt;

public class CaesarCipherAppTest {
    @Test
    public void testModulo() {
        int a = -7;
        int n = 3;
        assertEquals(2, modulo(a, n));
    }

    @Test
    public void testEncrypt() {
        int key = 3;
        String plainText = "abc";
        String encryptedText = "def";
        assertEquals(encryptedText, encrypt(plainText, 3));
    }

    @Test
    public void testDecrypt() {
        int key = 3;
        String plainText = "def";
        String encryptedText = "abc";
        assertEquals(encryptedText, decrypt(plainText, 3));
    }
}
