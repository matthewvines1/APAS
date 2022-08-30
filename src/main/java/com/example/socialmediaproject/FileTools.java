package com.example.socialmediaproject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTools {

    public static final BufferedWriter getBufferedWriter(String filePath) {
        createProgramFilesPath(Global.PROGRAM_FILE_PATH);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, Global.CHARACTER_ENCODING);
            return new BufferedWriter(outputStreamWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final InputStreamReader getInputStreamReader(String filePath) {
        createProgramFilesPath(Global.PROGRAM_FILE_PATH);
        try {
            File file = new File(filePath);
            file.createNewFile();
            return new InputStreamReader(new FileInputStream(filePath), Global.CHARACTER_ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void createProgramFilesPath(String pathString) {
        try {
            Path path = Paths.get(pathString);
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
