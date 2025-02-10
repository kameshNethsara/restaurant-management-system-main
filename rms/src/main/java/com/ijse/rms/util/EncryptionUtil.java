package com.ijse.rms.util;

import org.mindrot.jbcrypt.BCrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class EncryptionUtil {
//
//    // Method to generate a random salt
//    public static String generateSalt() {
//        SecureRandom random = new SecureRandom();
//        byte[] salt = new byte[16];
//        random.nextBytes(salt);
//        return Base64.getEncoder().encodeToString(salt);
//    }
//
//    // Method to hash the password using SHA-256 with a salt
//    public static String hashPassword(String password, String salt) {
//        try {
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            digest.update(salt.getBytes());
//            byte[] hashedPassword = digest.digest(password.getBytes());
//            return Base64.getEncoder().encodeToString(hashedPassword);
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException("Error hashing password", e);
//        }
//    }

    public class EncryptPassword {
        public static String hashPassword(String password){
            String salt = BCrypt.gensalt();
            String hashedPassword = BCrypt.hashpw(password,salt);
            return hashedPassword;
        }

        public static boolean verifyPassword(String plainPassword, String hashedPassword) {
            if (hashedPassword == null || hashedPassword.isEmpty()) {
                throw new IllegalArgumentException("Hashed password cannot be null or empty");
            }

            try {
                return BCrypt.checkpw(plainPassword, hashedPassword);
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid password hash format: " + hashedPassword);
                return false;
            }
        }
    }
}

