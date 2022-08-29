package com.example.socialmediaproject;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import org.apache.commons.codec.binary.Base64;

public class CryptoWrapper {

    private static final String CRYPTO_PROPERTIES_FILE_NAME = "crypto.txt";
    private static final String CHARACTER_ENCODING = "UTF-8";
    private static final int KEY_SIZE = 256;
    //if KEY_SIZE changes, make sure to change KEY_SIZE_CHARACTER_AES
    private static final int KEY_SIZE_CHARACTER_AES = 44;
    private static final String HASHING_ALGORITHM = "SHA";
    private static final String ENCRYPTION_ALGORITHM = "AES";


    public static String generateHash(String username, String password) {
        byte[] salt = username.getBytes();
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance(HASHING_ALGORITHM + "-" + KEY_SIZE);
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

    //Ciper[0] = encrypt cipher; Cipher[1] = decrypt cipher;
    public static Cipher[] getCipher(String username) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmac"+HASHING_ALGORITHM+KEY_SIZE);
            KeySpec spec = new PBEKeySpec(getEncryptionKey(), username.getBytes(), 65536, KEY_SIZE);
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
            CharBuffer charBuffer = CharBuffer.wrap(plainText);
            ByteBuffer byteBuffer = Charset.forName(CHARACTER_ENCODING).encode(charBuffer);
            byte[] bytes = new byte[byteBuffer.remaining()];
            Global.clearBytes(byteBuffer.array());
            byteBuffer.clear();
            Global.clearChars(charBuffer.array());
            charBuffer.clear();
            return Base64.encodeBase64URLSafeString(cipher.doFinal(bytes)).toCharArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static char[] getPlainText(Cipher cipher, char[] cipherText) {
        try {
            CharBuffer charBuffer = CharBuffer.wrap(cipherText);
            ByteBuffer byteBuffer = Charset.forName(CHARACTER_ENCODING).encode(charBuffer);
            byte[] bytes = new byte[byteBuffer.remaining()];
            Global.clearBytes(byteBuffer.array());
            byteBuffer.clear();
            Global.clearChars(charBuffer.array());
            charBuffer.clear();
            return new String(cipher.doFinal(Base64.decodeBase64(bytes))).toCharArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //this function gets the encryption key used to encrypt and decrypt all sensitive information
    //for now, the key is stored locally, but this will soon be moved to an online service requiring username and password
    private static char[] getEncryptionKey() {
        String cryptoFilePath = Global.PROGRAM_FILE_PATH + "\\" + CRYPTO_PROPERTIES_FILE_NAME;
        char[] cryptoKey = new char[KEY_SIZE_CHARACTER_AES];
        try {
            Path path = Paths.get(Global.PROGRAM_FILE_PATH);
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
            File file = new File(cryptoFilePath);
            file.createNewFile();
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(
                    cryptoFilePath), CHARACTER_ENCODING);
            int currentIndex = 0;
            int character;
            while((character = inputStreamReader.read()) != -1) {
                cryptoKey[currentIndex] = (char) character;
                currentIndex += 1;
            }
            if (currentIndex == KEY_SIZE_CHARACTER_AES) {
                return cryptoKey;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            KeyGenerator keyGen = null;
            try {
                keyGen = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            keyGen.init(KEY_SIZE);
            FileOutputStream fileOutputStream = new FileOutputStream(cryptoFilePath);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, CHARACTER_ENCODING);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            cryptoKey = Base64.encodeBase64String(keyGen.generateKey().getEncoded()).toCharArray();
            bufferedWriter.write(cryptoKey);
            bufferedWriter.close();
            return cryptoKey;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
