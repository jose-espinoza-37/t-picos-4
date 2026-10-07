package pe.edu.uni.fc.cc.cc0f7.substitution;

import static pe.edu.uni.fc.cc.cc0f7.common.Constants.ALPHABET_SIZE;
import static pe.edu.uni.fc.cc.cc0f7.common.Utils.gcd;
import static pe.edu.uni.fc.cc.cc0f7.common.Utils.modulo;
import static pe.edu.uni.fc.cc.cc0f7.common.Utils.modInverse;

public class AffineCipherApp {
    public static String encrypt(String plainText, int a, int b) {
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

    public static String decrypt(String encryptText, int inv_a, int b) {
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

    public static void main(String[] args) {
        System.out.println("Affine cipher !!!");
        int a = 11;
        int b = 6;
        int res = gcd(a, ALPHABET_SIZE);
        System.out.println(res);
        int inv_a = modInverse(a, ALPHABET_SIZE);
        System.out.println(inv_a);

        String original = "Este es un texto plano";
        String encrypted = encrypt(original, a, b);
        String decrypted = decrypt(encrypted, inv_a, b);

        System.out.println(original);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }
}
