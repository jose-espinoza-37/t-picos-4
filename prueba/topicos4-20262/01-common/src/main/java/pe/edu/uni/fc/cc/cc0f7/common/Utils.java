package pe.edu.uni.fc.cc.cc0f7.common;

import java.security.SecureRandom;
import java.util.HexFormat;

import static pe.edu.uni.fc.cc.cc0f7.common.Constants.UPPER_TO_LOWER_CASE;

public class Utils {

    public static int modulo(int a, int n) {
        int result = 0;
        if (n <= 0){
            result = -1;
        }
        result = (a % n + n) % n;
        return result;
    }
    /**
     * funcion que calcula en maximo comun divisor
     * @param x
     * @param y
     * @return
     */
    public static int gcd(int x, int y) {
        while (y  != 0) {
            int temp = y;
            y = modulo(x, y);
            x = temp;
        }
        return x;
    }

    /**
     * Calcula el inversion modular (a⁻1) de a
     * @param a
     * @param m
     * @return
     */
    public static int modInverse(int a, int m) {
        a = modulo(a, m);
        for (int x = 1; x < m; x++) {
            if (modulo(a*x, m) == 1) {
                return x;
            }
        }
        return -1;
    }

    public static char switchCase(char c) {;
        if (Character.isLowerCase(c)) {
            c = (char)(c - UPPER_TO_LOWER_CASE);
        } else{
            c = (char)(c + UPPER_TO_LOWER_CASE);
        }
        return c;
    }

    public static String byteToHex(byte[] bytes) {
        return HexFormat.of().withUpperCase().withDelimiter(" ").formatHex(bytes);
    }

    public static byte[] randomKey(int keySize) {
        byte[] key = new byte[keySize];
        new SecureRandom().nextBytes(key);
        return key;
    }

    public static byte[] generateIV(int length) {
        byte[] iv = new byte[length];
        new SecureRandom().nextBytes(iv);
        return iv;
    }
}
