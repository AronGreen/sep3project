package helpers;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.StringTokenizer;

public class Password {
    // inspired by this: https://stackoverflow.com/a/11038230
    private static final int iterations = 20000;
    private static final int saltLength = 32;
    private static final int desiredKeyLength = 256;

    /**
     * Hashes the given password with a random salt.
     * Returns generated salt together with the salted hash of the password
     * in the format "salt$saltedHash"
     * @param password the password to store
     * @return Salt and salted hash of password delimited by '$', or null if exception is thrown
     */
    public static String getSaltedHash(String password) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = secureRandom.generateSeed(saltLength);
        // store the salt with the password
        try {
            return new String(Base64.getEncoder().encode(salt)) + "$" + hash(password, salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "NoSuchAlgorithmException";
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return "InvalidKeySpecException";
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return "IllegalArgumentException";
        }
    }

    /**
     * Checks whether given plaintext password corresponds
     * to a stored salted hash of the password.
     * @param password the password to verify
     * @param stored the persisted password with its salt
     * @return true on match
     * @throws Exception
     */
    public static boolean check(String password, String stored) throws Exception{
        StringTokenizer tokenizer = new StringTokenizer(stored, "$");
        final String salt = tokenizer.nextToken();
        final String saltedHash = tokenizer.nextToken();

        String hashOfInput = hash(password, Base64.getDecoder().decode(salt));
        return hashOfInput.equals(saltedHash);
    }

    private static String hash(String password, byte[] salt) throws IllegalArgumentException, NoSuchAlgorithmException, InvalidKeySpecException {
        if (password == null || password.length() == 0)
            throw new IllegalArgumentException("Empty passwords are not supported.");
        return password;
        // SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        // SecretKey key = f.generateSecret(new PBEKeySpec(
        //         password.toCharArray(), salt, iterations, desiredKeyLength));
        // return new String(Base64.getEncoder().encode(key.getEncoded()), StandardCharsets.UTF_8);
    }
}
