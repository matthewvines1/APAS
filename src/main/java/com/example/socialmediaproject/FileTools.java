package com.example.socialmediaproject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileTools {

    private static final int MAX_CHARACTER_COUNT = 128;

    public static final BufferedWriter getBufferedWriter(String filePath, String fileName) {
        createPath(Global.PROGRAM_FILE_PATH);
        createPath(filePath);
        filePath = filePath + "\\" + fileName;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, Global.CHARACTER_ENCODING);
            return new BufferedWriter(outputStreamWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final InputStreamReader getInputStreamReader(String filePath, String fileName) {
        createPath(Global.PROGRAM_FILE_PATH);
        createPath(filePath);
        filePath = filePath + "\\" + fileName;
        try {
            File file = new File(filePath);
            file.createNewFile();
            return new InputStreamReader(new FileInputStream(filePath), Global.CHARACTER_ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final void setFileByCharArray(String filePath, String fileName, char[] chars) {
        try {
            BufferedWriter bufferedWriter = getBufferedWriter(filePath, fileName);
            bufferedWriter.write(chars);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final char[] getCharArrayByFile(String filePath, String fileName) {
        char[] tempArray = new char[MAX_CHARACTER_COUNT];
        InputStreamReader inputStreamReader = getInputStreamReader(filePath, fileName);
        int currentIndex = 0;
        try {
            int character;
            while ((character = inputStreamReader.read()) != -1) {
                tempArray[currentIndex] = (char) character;
                currentIndex += 1;
            }
            inputStreamReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(currentIndex > 0) {
            char[] finalArray = new char[currentIndex];
            for(int i = 0; i < currentIndex; i++) {
                finalArray[i] = tempArray[i];
            }
            Global.clearChars(tempArray);
            return finalArray;
        }
        return null;
    }

    private static void createPath(String pathString) {
        try {
            Path path = Paths.get(pathString);
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void log(String functionName, String message) {
        Global.log("FileTools", functionName, message);
    }
}
