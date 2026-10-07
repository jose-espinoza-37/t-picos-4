package pe.edu.uni.fc.cc.cc0f7.substitution;

import static pe.edu.uni.fc.cc.cc0f7.common.Constants.ALPHABET_SIZE;
import static pe.edu.uni.fc.cc.cc0f7.common.Constants.VERSION;
import static pe.edu.uni.fc.cc.cc0f7.common.Utils.modulo;

public class CaesarCipherApp {
    public static String encrypt(String plainText, int key) {
        StringBuilder result = new StringBuilder();
        for  (int i = 0; i < plainText.length(); i++) {
            char originalChar = plainText.charAt(i);
            // Verificar si es letra
            if (Character.isLetter(originalChar)) {
                // mayuscula o minuscula
                char base =  Character.isUpperCase(originalChar) ? 'A' : 'a';
                char encryptedChar = (char) (modulo(originalChar - base + key, ALPHABET_SIZE) + base);
                result.append(encryptedChar);
            } else  {
                result.append(originalChar);
            }
        }
        return result.toString();
    }

    public static String decrypt(String cipherText, int key) {
        return encrypt(cipherText, -key);
    }

    public static void main(String[] args) {
        System.out.println("Caesar cipher app: " + VERSION);
        String plainText = "Texto confidencial enviado desde el emisor hacia el receptor";
        int [] keys = {3, -3, 26, 27, -27};
        for (int key : keys) {
            System.out.println("Key: " + key);
            String cipherText = encrypt(plainText, key);
            String recoverText = decrypt(cipherText, key);
            System.out.println("Texto original: " + plainText);
            System.out.println("Texto cifrado: " + cipherText);
            System.out.println("Texto recuperado: " + recoverText);

            System.out.println("Correcto? " + (plainText.equals(recoverText) ? "ok!" : "ko!"));
        }

    }
}
