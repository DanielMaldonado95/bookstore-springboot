package com.apirest.bookshop.common;

public class common {
    public static boolean isNumeric(String strNum) {
        try {
            Double.parseDouble(strNum);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isBoolean(String strBoolean) {
        try {
            Boolean.parseBoolean(strBoolean);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
