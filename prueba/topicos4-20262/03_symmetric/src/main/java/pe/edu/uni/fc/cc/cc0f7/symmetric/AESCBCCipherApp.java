package pe.edu.uni.fc.cc.cc0f7.symmetric;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static pe.edu.uni.fc.cc.cc0f7.common.Constants.AES_ALGORITHM;
import static pe.edu.uni.fc.cc.cc0f7.common.Constants.TRANSFORMATION_AES_CBC;
import static pe.edu.uni.fc.cc.cc0f7.common.Constants.AES_BYTES_LENGTH_KEY_16;
import static pe.edu.uni.fc.cc.cc0f7.common.Constants.AES_CBC_IV_LENGTH;
import static pe.edu.uni.fc.cc.cc0f7.common.Utils.byteToHex;
import static pe.edu.uni.fc.cc.cc0f7.common.Utils.randomKey;
import static pe.edu.uni.fc.cc.cc0f7.common.Utils.generateIV;


public class AESCBCCipherApp {

    public static void main(String[] args) {
        System.out.println("AES CBC Cipher App !!");
        // fuente de aleatoridad
        // SecureRandom sr = new SecureRandom();
        // generar la llave AES
        // byte[] key = new byte[16];
        // sr.nextBytes(key);
        byte[] key = randomKey(AES_BYTES_LENGTH_KEY_16);
        System.out.println(byteToHex(key));
        System.out.println("done !!");
        // IV
        // byte[] initVector = new byte[16];
        // sr.nextBytes(initVector);
        byte[] initVector = generateIV(AES_CBC_IV_LENGTH);
        System.out.println("IV:");
        System.out.println(byteToHex(initVector));

        String payload = "This is a plain text sent from Alice to Bob|";
        System.out.println(payload);
        String encrypted = encrypt(key, initVector, payload);
        String decrypted = decrypt(key, initVector, encrypted);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }

    public static String encrypt(byte[] key, byte[] initVector, String plainText) {
        String encoded;
        IvParameterSpec iv = new IvParameterSpec(initVector);
        SecretKeySpec sKeySpec = new SecretKeySpec(key, AES_ALGORITHM);
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION_AES_CBC);
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);
            byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            encoded = Base64.getEncoder().encodeToString(encrypted);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
        return encoded;
    }

    public static String decrypt(byte[] key, byte[] initVector, String cipheredText) {
        String decoded;
        IvParameterSpec iv = new IvParameterSpec(initVector);
        SecretKeySpec sKeySpec = new SecretKeySpec(key, AES_ALGORITHM);
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION_AES_CBC);
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, iv);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(cipheredText));
            decoded = new String(decrypted, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
        return decoded;
    }

}
