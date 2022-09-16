package com.example.socialmediaproject;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static final void clearStringBuilders(StringBuilder[] stringBuilders) {
        for(int i = 0; i < stringBuilders.length; i++) {
            stringBuilders[i].setLength(0);
        }
    }

    public static final void log(String className, String functionName, String message) {
        System.out.println(className + " - " + functionName + " : " + message + "\n");
    }

    public static final void show2dArray(ArrayList<List<String>> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            for (int j = 0; j < arrayList.get(i).size(); j++) {
                System.out.print(arrayList.get(i).get(j));
                if (j != arrayList.get(i).size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}
