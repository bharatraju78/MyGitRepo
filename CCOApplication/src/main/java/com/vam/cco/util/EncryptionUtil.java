package com.vam.cco.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple AES-GCM based encrypt/decrypt utility.
 *
 * How to provide the encryption key:
 * - Set environment variable APP_ENC_KEY to a passphrase (any string). The utility will derive a 256-bit key via SHA-256.
 * - Alternatively set system property -Dapp.encryption.key=<your-key>.
 *
 * Note: For production, use a secure key management system and rotate keys regularly.
 */
public final class EncryptionUtil {

    private static final Logger logger = LoggerFactory.getLogger(EncryptionUtil.class);
    private static final String ENV_KEY = "APP_ENC_KEY";
    private static final String SYS_PROP = "app.encryption.key";
    private static final String AES_ALGO = "AES";
    private static final String AES_GCM = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH = 128; // bits
    private static final int IV_LENGTH = 12; // bytes (recommended for GCM)
    private static final SecretKeySpec keySpec;
    private static final SecureRandom secureRandom = new SecureRandom();

    static {
        String keySource = System.getenv(ENV_KEY);
        if (keySource == null || keySource.isEmpty()) {
            keySource = System.getProperty(SYS_PROP);
        }
        if (keySource == null || keySource.isEmpty()) {
            logger.warn("No encryption key found in env {} or system property {}. Using a temporary insecure key (ONLY FOR DEV).", ENV_KEY, SYS_PROP);
            keySource = "default-dev-key-change-me";
        }
        keySpec = new SecretKeySpec(deriveKeySHA256(keySource), AES_ALGO);
    }

    private EncryptionUtil() {}

    private static byte[] deriveKeySHA256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(input.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Failed to derive key: " + e.getMessage(), e);
        }
    }

    public static String encrypt(String plaintext) {
        if (plaintext == null) return null;
        try {
            byte[] iv = new byte[IV_LENGTH];
            secureRandom.nextBytes(iv);

            Cipher cipher = Cipher.getInstance(AES_GCM);
            GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, spec);

            byte[] cipherText = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

            ByteBuffer byteBuffer = ByteBuffer.allocate(iv.length + cipherText.length);
            byteBuffer.put(iv);
            byteBuffer.put(cipherText);
            byte[] cipherMessage = byteBuffer.array();
            return Base64.getEncoder().encodeToString(cipherMessage);
        } catch (Exception e) {
            logger.error("Encryption failed: {}", e.getMessage());
            throw new RuntimeException("Encryption failed", e);
        }
    }

    public static String decrypt(String base64CipherText) {
        if (base64CipherText == null) return null;
        try {
            byte[] cipherMessage = Base64.getDecoder().decode(base64CipherText);
            ByteBuffer byteBuffer = ByteBuffer.wrap(cipherMessage);
            byte[] iv = new byte[IV_LENGTH];
            byteBuffer.get(iv);
            byte[] cipherText = new byte[byteBuffer.remaining()];
            byteBuffer.get(cipherText);

            Cipher cipher = Cipher.getInstance(AES_GCM);
            GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, spec);

            byte[] plainText = cipher.doFinal(cipherText);
            return new String(plainText, StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.error("Decryption failed: {}", e.getMessage());
            throw new RuntimeException("Decryption failed", e);
        }
    }
    
    public static void main(String[] args) {
		BigDecimal original = new BigDecimal("1000000");
		String encrypted = encrypt(original.setScale(2, RoundingMode.HALF_UP).toPlainString());
		String decrypted = decrypt(encrypted);
		BigDecimal decryptedSal = new BigDecimal(decrypted);
		System.out.println("Original : " + original);
		System.out.println("Encrypted: " + encrypted);
		System.out.println("Decrypted: " + decrypted);
		System.out.println("decryptedSal: " + decryptedSal);
	}
}
