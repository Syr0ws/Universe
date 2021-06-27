package com.github.syr0ws.universe.tools;

public class Validate {

    public static boolean isInt(String string) {

        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
