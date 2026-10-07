package pe.edu.uni.fc.cc.cc0f7.hashing;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static pe.edu.uni.fc.cc.cc0f7.common.Constants.HASH_BUFFER_SIZE;
import static pe.edu.uni.fc.cc.cc0f7.common.Constants.SHA_256_ALGORITHM;
import static pe.edu.uni.fc.cc.cc0f7.common.Utils.byteToHex;

public class Hashing {
    public static void main(String[] args) throws IOException {
        System.out.println("Hashing app");
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(SHA_256_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Algoritmo no disponible: " + SHA_256_ALGORITHM, e);
        }

        try (InputStream in = Hashing.class.getResourceAsStream(Hashing.class.getSimpleName() + ".class")) {
            if (in == null) {
                throw new IllegalArgumentException("El input no puede ser nulo");
            }
            final byte[] bytes = new byte[HASH_BUFFER_SIZE];
            for (int length = in.read(bytes); length != -1; length = in.read(bytes)) {
                md.update(bytes, 0, length);
            }
            final byte[] hash = md.digest();
            System.out.println("SHA-256: " + byteToHex(hash));
        }
        System.out.println("done");
    }
}