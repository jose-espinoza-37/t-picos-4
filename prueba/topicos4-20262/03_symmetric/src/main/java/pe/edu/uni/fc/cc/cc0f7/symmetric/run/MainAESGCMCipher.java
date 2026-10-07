package pe.edu.uni.fc.cc.cc0f7.symmetric.run;

import pe.edu.uni.fc.cc.cc0f7.symmetric.services.AESGCMCipherService;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static pe.edu.uni.fc.cc.cc0f7.common.Constants.AAD_AES_GCM;
import static pe.edu.uni.fc.cc.cc0f7.common.Constants.AES_BYTES_LENGTH_KEY_16;
import static pe.edu.uni.fc.cc.cc0f7.common.Constants.AES_GCM_IV_LENGTH;
import static pe.edu.uni.fc.cc.cc0f7.common.Utils.generateIV;
import static pe.edu.uni.fc.cc.cc0f7.common.Utils.randomKey;

public class MainAESGCMCipher {
    public static void main(String[] args) {
        System.out.println("Main AES GCM Cipher");
        byte[] key = randomKey(AES_BYTES_LENGTH_KEY_16);
        AESGCMCipherService cipher = new AESGCMCipherService(key);
        byte[] iv = generateIV(AES_GCM_IV_LENGTH);
        byte[] aad = AAD_AES_GCM.getBytes(StandardCharsets.UTF_8);
        byte[] msg = "Mensaje AES GCM confidencial".getBytes(StandardCharsets.UTF_8);

        byte[] encrypted = cipher.encrypt(msg, iv, aad);
        byte[] decrypted = cipher.decrypt(encrypted, aad);

        System.out.println(new String(msg, StandardCharsets.UTF_8));
        System.out.println(Base64.getEncoder().encodeToString(encrypted));
        System.out.println(new String(decrypted, StandardCharsets.UTF_8));
        System.out.println("done");
    }
}