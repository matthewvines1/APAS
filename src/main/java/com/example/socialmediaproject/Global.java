package com.example.socialmediaproject;

import javafx.scene.image.Image;

import java.util.Arrays;

public class Global {

    public static final int DEFAULT_WINDOW_WIDTH = 960;
    public static final int DEFAULT_WINDOW_HEIGHT = 600;
    public static final int DEFAULT_POPUP_WIDTH = 380;
    public static final int DEFAULT_POPUP_HEIGHT = 300;
    public static final Image LOGO = new Image(Main.class.getResourceAsStream("logo.png"));
    public static final String PROGRAM_FILE_PATH = System.getenv("ProgramFiles") + "\\APAS";
    public static final String CHARACTER_ENCODING = "UTF-8";

    public static final void clearBytes(byte[] bytes) {
        Arrays.fill(bytes, (byte)0);
    }

    public static final void clearChars(char[] chars) {
        Arrays.fill(chars, (char)0);
    }

    public static final void log(String className, String functionName, String message) {
        System.out.println(className + " - " + functionName + " : " + message + "\n");
    }
}
