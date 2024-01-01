package com.paltales.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptPassword {
    /*
        I have created this class to encrypt password using hash based encryption
        - https://github.com/sondosaabed/File-Carving-Tool/blob/main/model/CalculateCompareHash.java
     */

    public static String encryptPassword(String password) throws NoSuchAlgorithmException {
        /*
            Args: Takes a password
            Returns: it's encryption using SHA-256
         */
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder strb = new StringBuilder();

            for(byte hashedByte : hashBytes){
                strb.append(Integer.toString((hashedByte & 0xff) + 0x100, 16).substring(1));
            }

            return strb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException();
        }
    }

    /*

        I hash the string once taken so no need to use this method
        the strings that will be compred on Authenticator will already be comparing
        two hashed encrypted strings

    public static boolean compareHashes(String pass, String retreived) throws NoSuchAlgorithmException {

            //Takes two strings and compares their hashes

        String passHash = encryptPassword(pass);
        String retHash = encryptPassword(retreived);

        if (passHash.equals(retHash)) {
            return true;
        } else {
            return false;
        }
    }
    */
}