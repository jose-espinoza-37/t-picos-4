package pe.edu.uni.fc.cc.cc0f7.hashing.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static pe.edu.uni.fc.cc.cc0f7.common.Constants.HASH_BUFFER_SIZE;

public class HashService {
    private final String algorithm;

    public HashService(String algorithm) {
        this.algorithm = algorithm;
    }

    private MessageDigest newDigest() {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Algoritmo no disponible: " + algorithm, e);
        }
    }

    public byte[] hash(InputStream inputStream) {
        MessageDigest md = newDigest();
        byte[] bytes = new byte[HASH_BUFFER_SIZE];
        try {
            int bytesRead;
            while ((bytesRead = inputStream.read(bytes)) != -1) {
                md.update(bytes, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Error al leer el flujo de datos", e);
        }
        return md.digest();
    }

    public byte[] hash(byte[] data) {
        return newDigest().digest(data);
    }

    public byte[] hash(String text) {
        return hash(text.getBytes(StandardCharsets.UTF_8));
    }
}