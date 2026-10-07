package pe.edu.uni.fc.cc.cc0f7.substitution;

import static pe.edu.uni.fc.cc.cc0f7.common.Utils.switchCase;

public class SubstitutionCipherApp {
    // mapa de caracteres
    static char[] charsMap = {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p',
            'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l',
            'z', 'x', 'c', 'v', 'b', 'n', 'm'};

    public static int indexOfCharsMap(char c) {
        int index = -1;
        for (int i = 0; i < charsMap.length; i++) {
            if (c == charsMap[i]) {
                index = i;
                break;
            }
        }
        return index;
    }
    public static void main(String[] args) {
        System.out.println("Substitution Cipher App");
        System.out.println(charsMap.length);
        String original_message = "Texto a ser cifrado con el algoritmo de subtitucion!";
        String encrypt_message = encrypt(original_message);
        String decrypt_message = decrypt(encrypt_message);
        System.out.println(original_message);
        System.out.println(encrypt_message);
        System.out.println(decrypt_message);
    }

    public static String encrypt(String plainText) {
        StringBuilder result= new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    c = switchCase(c);
                    c = charsMap[c - 'a'];
                    c = switchCase(c);
                    // result.append(c);
                } else {
                    c = charsMap[c - 'a'];
                    // result.append(c);
                }
            }
            result.append(c);
        }
        return result.toString();
    }

    public static String decrypt(String cipherText) {
        StringBuilder result= new StringBuilder();
        for (int i = 0; i < cipherText.length(); i++) {
            char c = cipherText.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    c = switchCase(c);
                    c = (char)('a' + indexOfCharsMap(c));
                    c = switchCase(c);
                } else {
                    c = (char) ('a' + indexOfCharsMap(c));
                }
            }
            result.append(c);
        }
        return  result.toString();
    }
}
