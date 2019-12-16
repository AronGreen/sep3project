package security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

public class EncryptionHelper {

    public static void main(String[] args) throws Exception {

        String plaintext = "This is a good secret.";
        System.out.println(plaintext);

        String ciphertext = encrypt(plaintext);
        System.out.println(ciphertext);

        String decrypted = decrypt(ciphertext);
        System.out.println(decrypted);

    }

    public static String encrypt(String plaintext) throws Exception {
        return encrypt(generateIV(), plaintext);
    }

    public static String encrypt(byte[] iv, String plaintext) throws Exception {

        byte[] decrypted = plaintext.getBytes();
        byte[] encrypted = encrypt(iv, decrypted);

        StringBuilder ciphertext = new StringBuilder();

        ciphertext.append(new String(Base64.getEncoder().encode(iv)));
        ciphertext.append(":");
        ciphertext.append(new String(Base64.getEncoder().encode(encrypted)));

        return ciphertext.toString();

    }

    public static String decrypt(String ciphertext) throws Exception {
        String[] parts = ciphertext.split(":");
        byte[] iv = Base64.getDecoder().decode(parts[0]);
        byte[] encrypted = Base64.getDecoder().decode(parts[1]);
        byte[] decrypted = decrypt(iv, encrypted);
        return new String(decrypted);
    }

    private static Key key = new SecretKeySpec(
            new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16},
            "AES");

    private static byte[] generateIV() {
        SecureRandom random = new SecureRandom();
        byte[] iv = new byte[16];
        random.nextBytes(iv);
        return iv;
    }

    public static byte[] encrypt(byte[] iv, byte[] plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance(key.getAlgorithm() + "/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        return cipher.doFinal(plaintext);
    }

    public static byte[] decrypt(byte[] iv, byte[] ciphertext) throws Exception {
        Cipher cipher = Cipher.getInstance(key.getAlgorithm() + "/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        return cipher.doFinal(ciphertext);
    }

}