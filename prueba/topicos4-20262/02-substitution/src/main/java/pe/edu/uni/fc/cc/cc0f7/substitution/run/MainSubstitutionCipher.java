package pe.edu.uni.fc.cc.cc0f7.substitution.run;

import pe.edu.uni.fc.cc.cc0f7.substitution.services.SubstitutionCipher;

public class MainSubstitutionCipher {
    public static void main(String[] args) {
        System.out.println("Main Subtitution Cipher !!");
        char[] key = {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p',
                'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l',
                'z', 'x', 'c', 'v', 'b', 'n', 'm'};
        SubstitutionCipher cipher = new SubstitutionCipher(key);
        String original_message = "Texto a ser cifrado con el algoritmo de subtitucion!";
        String encrypt_message = cipher.encrypt(original_message);
        String decrypt_message = cipher.decrypt(encrypt_message);
        System.out.println(original_message);
        System.out.println(encrypt_message);
        System.out.println(decrypt_message);
    }
}
