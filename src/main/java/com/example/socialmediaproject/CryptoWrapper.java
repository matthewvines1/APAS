package com.example.socialmediaproject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoWrapper {

    public CryptoWrapper() {

    }

    public String generateHash(String username, String password) {
        byte[] salt = username.getBytes();
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
