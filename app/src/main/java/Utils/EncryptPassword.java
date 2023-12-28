package Utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptPassword {
    /*
        I have created this class to encrypt password using hash based encryption
        - https://github.com/sondosaabed/File-Carving-Tool/blob/main/model/CalculateCompareHash.java
     */
    private static MessageDigest digest;

    public static String encryptPassword(String password) throws NoSuchAlgorithmException {
        /*
            Args: Takes a password
            Returns: it's encryption using SHA-256
         */
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes("UTF-8"));

            StringBuilder strb = new StringBuilder();

            for(byte hashedByte : hashBytes){
                strb.append(Integer.toString((hashedByte & 0xff) + 0x100, 16).substring(1));
            }

            return strb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String compareHashes(String pass, String retreived) throws NoSuchAlgorithmException {
        /*
            Takes two strings and compares their hashes
         */
        String passHash = encryptPassword(pass);
        String retHash = encryptPassword(retreived);

        if (passHash.equals(retHash)) {
            return "Match";
        } else {
            return "Mismatch";
        }
    }
}