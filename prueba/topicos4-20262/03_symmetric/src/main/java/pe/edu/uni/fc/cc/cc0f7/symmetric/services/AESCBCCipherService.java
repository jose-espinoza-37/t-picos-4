package pe.edu.uni.fc.cc.cc0f7.symmetric.services;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;

import static pe.edu.uni.fc.cc.cc0f7.common.Constants.AES_ALGORITHM;
import static pe.edu.uni.fc.cc.cc0f7.common.Constants.AES_CBC_IV_LENGTH;
import static pe.edu.uni.fc.cc.cc0f7.common.Constants.TRANSFORMATION_AES_CBC;

public class AESCBCCipherService {
    private final byte[] key;

    public AESCBCCipherService(byte[] key) {
        this.key = key;
    }

    /** Salida: IV || texto cifrado (sin tag: CBC no autentica) */
    public byte[] encrypt(byte[] plainText, byte[] initVector) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, AES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION_AES_CBC);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);

            byte[] encryptedText = cipher.doFinal(plainText);

            byte[] totalEncryptedText = new byte[initVector.length + encryptedText.length];
            System.arraycopy(initVector, 0, totalEncryptedText, 0, initVector.length);
            System.arraycopy(encryptedText, 0, totalEncryptedText, initVector.length, encryptedText.length);
            return totalEncryptedText;
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("Error al cifrar con AES-CBC", e);
        }
    }

    public byte[] decrypt(byte[] totalEncryptedText) {
        try {
            byte[] initVector = new byte[AES_CBC_IV_LENGTH];
            byte[] encryptedText = new byte[totalEncryptedText.length - AES_CBC_IV_LENGTH];
            System.arraycopy(totalEncryptedText, 0, initVector, 0, AES_CBC_IV_LENGTH);
            System.arraycopy(totalEncryptedText, AES_CBC_IV_LENGTH, encryptedText, 0, encryptedText.length);

            SecretKeySpec secretKeySpec = new SecretKeySpec(key, AES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION_AES_CBC);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(initVector));

            return cipher.doFinal(encryptedText);
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("Error al descifrar con AES-CBC", e);
        }
    }
}