package pe.edu.uni.fc.cc.cc0f7.common;

public class Constants {
    public static final String VERSION = "1.0.0";
    public static final int ALPHABET_SIZE = 26;
    public static final int UPPER_TO_LOWER_CASE = 32;
    // AES
    public static final String AES_ALGORITHM = "AES";
    public static final String TRANSFORMATION_AES_CBC = "AES/CBC/PKCS5Padding";
    public static final int AES_CBC_IV_LENGTH = 16;
    public static final int AES_BYTES_LENGTH_KEY_16 = 16;
    public static final int AES_BYTES_LENGTH_KEY_32 = 32;
    public static final int AES_BYTES_LENGTH_KEY_64 = 64;

    // AES GCM
    public static final String TRANSFORMATION_AES_GCM = "AES/GCM/NoPadding";
    public static final int AES_GCM_IV_LENGTH = 12;      // 12 bytes es lo recomendado para GCM
    public static final int TAG_LENGTH_BIT = 128;        // el tag se mide en BITS
    public static final String AAD_AES_GCM = "Header";   // público: todos lo ven, nadie lo puede cambiar sin ser detectado

    // Hashing
    public static final String SHA_256_ALGORITHM = "SHA-256";
    public static final int HASH_BUFFER_SIZE = 1024;
    // PBKDF2
    public static final String PBKDF2_WITH_HMAC_SHA_256_ALGORITHM = "PBKDF2WithHmacSHA256";
    public static final int PBKDF2_ITERATIONS = 600_000;
    public static final int PBKDF2_KEY_LENGTH_BITS = 256;
}
