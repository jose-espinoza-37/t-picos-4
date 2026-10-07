package pe.edu.uni.fc.cc.cc0f7.hashing;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

import static pe.edu.uni.fc.cc.cc0f7.common.Constants.PBKDF2_ITERATIONS;
import static pe.edu.uni.fc.cc.cc0f7.common.Constants.PBKDF2_KEY_LENGTH_BITS;
import static pe.edu.uni.fc.cc.cc0f7.common.Constants.PBKDF2_WITH_HMAC_SHA_256_ALGORITHM;
import static pe.edu.uni.fc.cc.cc0f7.common.Utils.byteToHex;

public class KeyDerivationApp {

    public static void main(String[] args) {
        final String password = "123456";
        final String salt = "jose@hotmail.com";

        PBEKeySpec keySpec = new PBEKeySpec(
                password.toCharArray(),
                salt.getBytes(StandardCharsets.UTF_8),
                PBKDF2_ITERATIONS,
                PBKDF2_KEY_LENGTH_BITS);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_WITH_HMAC_SHA_256_ALGORITHM);
            byte[] hash = skf.generateSecret(keySpec).getEncoded();
            System.out.println("PBKDF2-HMAC-SHA256 (password + salt): " + byteToHex(hash));
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("Error al derivar la clave", e);
        } finally {
            keySpec.clearPassword();
        }
    }
}