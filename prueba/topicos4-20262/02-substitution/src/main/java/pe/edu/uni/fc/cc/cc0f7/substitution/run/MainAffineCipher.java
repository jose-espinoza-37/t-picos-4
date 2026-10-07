package pe.edu.uni.fc.cc.cc0f7.substitution.run;

import pe.edu.uni.fc.cc.cc0f7.substitution.services.AffineCipher;

public class MainAffineCipher {
    public static void main(String[] args){
        System.out.println("Main Affine Cipher !!!");
        int a = 11;
        int b = 6;
        AffineCipher affineCipher = new AffineCipher(a, b);
        String original = "Este es un texto plano";
        String encrypted = affineCipher.encrypt(original);
        String decrypted = affineCipher.decrypt(encrypted);

        System.out.println("Original :" + original);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        System.out.println("done!");
    }
}
