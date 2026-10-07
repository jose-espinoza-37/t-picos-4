package pe.edu.uni.fc.cc.cc0f7.symmetric.services;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;

import static pe.edu.uni.fc.cc.cc0f7.common.Constants.AES_ALGORITHM;
import static pe.edu.uni.fc.cc.cc0f7.common.Constants.AES_GCM_IV_LENGTH;
import static pe.edu.uni.fc.cc.cc0f7.common.Constants.TAG_LENGTH_BIT;
import static pe.edu.uni.fc.cc.cc0f7.common.Constants.TRANSFORMATION_AES_GCM;

public class AESGCMCipherService {
    private final byte[] key;

    public AESGCMCipherService(byte[] key) {
        this.key = key;
    }

    /** Salida: IV || texto cifrado || tag */
    public byte[] encrypt(byte[] plainText, byte[] iv, byte[] aad) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION_AES_GCM);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, AES_ALGORITHM);
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, gcmParameterSpec);
            if (aad != null) {
                cipher.updateAAD(aad);          // debe ir antes de doFinal
            }
            byte[] encryptedText = cipher.doFinal(plainText);   // cifrado + tag

            byte[] totalEncryptedText = new byte[iv.length + encryptedText.length];
            System.arraycopy(iv, 0, totalEncryptedText, 0, iv.length);
            System.arraycopy(encryptedText, 0, totalEncryptedText, iv.length, encryptedText.length);
            return totalEncryptedText;
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("Error al cifrar con AES-GCM", e);
        }
    }

    public byte[] decrypt(byte[] totalEncryptedText, byte[] aad) {
        try {
            byte[] iv = new byte[AES_GCM_IV_LENGTH];
            byte[] encryptedText = new byte[totalEncryptedText.length - AES_GCM_IV_LENGTH];
            System.arraycopy(totalEncryptedText, 0, iv, 0, AES_GCM_IV_LENGTH);
            System.arraycopy(totalEncryptedText, AES_GCM_IV_LENGTH, encryptedText, 0, encryptedText.length);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION_AES_GCM);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, AES_ALGORITHM);
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, gcmParameterSpec);
            if (aad != null) {
                cipher.updateAAD(aad);
            }
            return cipher.doFinal(encryptedText);   // lanza AEADBadTagException si algo fue alterado
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("Error al descifrar con AES-GCM (¿datos alterados?)", e);
        }
    }
}