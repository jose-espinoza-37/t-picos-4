package pe.edu.uni.fc.cc.cc0f7.hashing.run;

import pe.edu.uni.fc.cc.cc0f7.hashing.services.HashService;

import java.io.IOException;
import java.io.InputStream;

import static pe.edu.uni.fc.cc.cc0f7.common.Constants.SHA_256_ALGORITHM;
import static pe.edu.uni.fc.cc.cc0f7.common.Utils.byteToHex;

public class MainHash {
    public static void main(String[] args) {
        System.out.println("Main Hash");
        HashService service = new HashService(SHA_256_ALGORITHM);
        try (InputStream in = MainHash.class.getResourceAsStream(MainHash.class.getSimpleName() + ".class")) {
            if (in != null) {
                byte[] hash = service.hash(in);
                System.out.println("SHA-256: " + byteToHex(hash));
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo leer el recurso", e);
        }
        System.out.println("done");
    }
}