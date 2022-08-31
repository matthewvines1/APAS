package com.example.socialmediaproject;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class CryptoWrapper {

    private static final String CRYPTO_PROPERTIES_FILE_NAME = "crypto.txt";
    private static final int KEY_SIZE = 256;
    //if KEY_SIZE changes, make sure to change KEY_SIZE_CHARACTER_AES
    private static final int KEY_SIZE_CHARACTER_AES = 44;
    private static final String HASHING_ALGORITHM = "SHA";
    private static final String ENCRYPTION_ALGORITHM = "AES";


    public static char[] generateHash(char[] username, char[] password) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(username);
        byte[] salt = stringBuilder.toString().getBytes();
        stringBuilder.setLength(0);
        char[] generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance(HASHING_ALGORITHM + "-" + KEY_SIZE);
            md.update(salt);
            Global.clearBytes(salt);
            stringBuilder = new StringBuilder();
            stringBuilder.append(password);
            byte[] bytes = md.digest(stringBuilder.toString().getBytes());
            stringBuilder.setLength(0);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString().toCharArray();
            sb.setLength(0);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    //Ciper[0] = encrypt cipher; Cipher[1] = decrypt cipher;
    public static Cipher[] getCipher(char[] passwordHash) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmac"+HASHING_ALGORITHM+KEY_SIZE);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(passwordHash);
            KeySpec spec = new PBEKeySpec(getEncryptionKey(), stringBuilder.toString().getBytes(), 65536, KEY_SIZE);
            stringBuilder.setLength(0);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec newSecretKey = new SecretKeySpec(tmp.getEncoded(), ENCRYPTION_ALGORITHM);
            Cipher encryptCipher = Cipher.getInstance(ENCRYPTION_ALGORITHM + "/CBC/PKCS5Padding");
            Cipher decryptCipher = Cipher.getInstance(ENCRYPTION_ALGORITHM + "/CBC/PKCS5Padding");
            encryptCipher.init(Cipher.ENCRYPT_MODE, newSecretKey, ivspec);
            decryptCipher.init(Cipher.DECRYPT_MODE, newSecretKey, ivspec);
            Cipher[] cipherArray = new Cipher[2];
            cipherArray[0] = encryptCipher;
            cipherArray[1] = decryptCipher;
            return cipherArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static char[] getCipherText(Cipher cipher, char[] plainText) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(plainText);
            Global.clearChars(plainText);
            byte[] bytes = cipher.doFinal((stringBuilder.toString()).getBytes(Global.CHARACTER_ENCODING));
            stringBuilder.setLength(0);
            bytes = Base64.getEncoder().encode(bytes);
            stringBuilder = new StringBuilder();
            for(int i = 0; i < bytes.length; i++){
                stringBuilder.append((char)bytes[i]);
            }
            Global.clearBytes(bytes);
            char[] finalArray = stringBuilder.toString().toCharArray();
            stringBuilder.setLength(0);
            return finalArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static char[] getPlainText(Cipher cipher, char[] cipherText) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(cipherText);
            Global.clearChars(cipherText);
            byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(stringBuilder.toString()));
            stringBuilder.setLength(0);
            stringBuilder = new StringBuilder();
            for(int i = 0; i < bytes.length; i++){
                stringBuilder.append((char)bytes[i]);
            }
            Global.clearBytes(bytes);
            char[] finalArray = stringBuilder.toString().toCharArray();
            stringBuilder.setLength(0);
            return finalArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //this function gets the encryption key used to encrypt and decrypt all sensitive information
    //for now, the key is stored locally, but this will soon be moved to an online service requiring username and password
    private static char[] getEncryptionKey() {
        String cryptoFilePath = Global.PROGRAM_FILE_PATH + "\\" + CRYPTO_PROPERTIES_FILE_NAME;
        InputStreamReader inputStreamReader = FileTools.getInputStreamReader(cryptoFilePath);
        int currentIndex = 0;
        int character;
        char[] cryptoKey = new char[KEY_SIZE_CHARACTER_AES];
        try {
            while ((character = inputStreamReader.read()) != -1) {
                cryptoKey[currentIndex] = (char) character;
                currentIndex += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (currentIndex == KEY_SIZE_CHARACTER_AES) {
            return cryptoKey;
        }
        KeyGenerator keyGen = null;
        try {
            keyGen = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyGen.init(KEY_SIZE);
        BufferedWriter bufferedWriter = FileTools.getBufferedWriter(cryptoFilePath);
        cryptoKey = new String(Base64.getEncoder().encode(keyGen.generateKey().getEncoded())).toCharArray();
        try {
            bufferedWriter.write(cryptoKey);
            bufferedWriter.close();
            return cryptoKey;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
