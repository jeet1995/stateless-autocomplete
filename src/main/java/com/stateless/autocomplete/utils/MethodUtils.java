package com.stateless.autocomplete.utils;

/**
 * Utility class for utility methods
 * */
public class MethodUtils {

    private MethodUtils() {
    }

    public static Integer getIntValue(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException ne) {
            return null;
        }
    }

}
