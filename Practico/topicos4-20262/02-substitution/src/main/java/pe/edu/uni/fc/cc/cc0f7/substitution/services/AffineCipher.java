package pe.edu.uni.fc.cc.cc0f7.substitution.services;

import static pe.edu.uni.fc.cc.cc0f7.common.Constants.ALPHABET_SIZE;
import static pe.edu.uni.fc.cc.cc0f7.common.Utils.gcd;
import static pe.edu.uni.fc.cc.cc0f7.common.Utils.modInverse;
import static pe.edu.uni.fc.cc.cc0f7.common.Utils.modulo;

public class AffineCipher {
    private final int a;
    private final int b;
    private final int inv_a;

    public AffineCipher(int a, int b) {
        if (gcd(a, ALPHABET_SIZE) != 1){
            throw new IllegalArgumentException("el valor de 'a' debe ser coprimo con " + ALPHABET_SIZE);
        }
        this.a = a % ALPHABET_SIZE;
        this.b = b % ALPHABET_SIZE;
        int inv_a = modInverse(a, ALPHABET_SIZE);
        if (inv_a == -1) {
            throw new IllegalArgumentException("No existe inverso modular de 'a'");
        }
        this.inv_a = inv_a;
    }

    public String encrypt(String plainText) {
        String result = "";
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char)(modulo((a * (c - base) + b), ALPHABET_SIZE) + base);
            }
            result += c;
        }
        return result;
    }

    public String decrypt(String encryptText) {
        String result = "";
        for (int i = 0; i < encryptText.length(); i++) {
            char c = encryptText.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char)(modulo((inv_a * (c - base - b)), ALPHABET_SIZE) + base);
            }
            result += c;
        }
        return result;
    }
}

