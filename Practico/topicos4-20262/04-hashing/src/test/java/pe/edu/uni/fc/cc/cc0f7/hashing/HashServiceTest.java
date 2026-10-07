package pe.edu.uni.fc.cc.cc0f7.hashing;

import org.junit.jupiter.api.Test;
import pe.edu.uni.fc.cc.cc0f7.hashing.services.HashService;

import java.util.HexFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pe.edu.uni.fc.cc.cc0f7.common.Constants.SHA_256_ALGORITHM;

public class HashServiceTest {
    private final HashService service = new HashService(SHA_256_ALGORITHM);

    @Test
    public void testSha256Abc() {
        assertEquals("ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad",
                HexFormat.of().formatHex(service.hash("abc")));
    }

    @Test
    public void testSha256Empty() {
        assertEquals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855",
                HexFormat.of().formatHex(service.hash("")));
    }
}