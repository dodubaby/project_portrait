package com.rat.utils;

import com.sun.istack.internal.Nullable;

public class SafeParseUtils {

    public static final String TAG = SafeParseUtils.class.getSimpleName();

    public static boolean parseBoolean(@Nullable String str) {
        return parseBoolean(str, false);
    }

    public static boolean parseBoolean(@Nullable String str, boolean defaultValue) {
        boolean result = defaultValue;
        if (str != null) {
            try {
                result = Boolean.parseBoolean(str);
            } catch (NumberFormatException ex) {
            }
        }
        return result;
    }

    public static int parseInt(@Nullable String str) {
        return parseInt(str, 0);
    }

    public static int parseInt(@Nullable String str, int defaultValue) {
        return (int) parseDouble(str, defaultValue);
    }

    public static long parseLong(@Nullable String str) {
        return parseLong(str, 0);
    }

    public static long parseLong(@Nullable String str, long defaultValue) {
        return (long) parseDouble(str, defaultValue);
    }

    @Deprecated
    public static float parseFloat(@Nullable String str) {
        return parseFloat(str, 0);
    }

    @Deprecated
    public static float parseFloat(@Nullable String str, float defaultValue) {
        float result = defaultValue;
        if (str != null) {
            try {
                result = Float.parseFloat(str);
            } catch (NumberFormatException ex) {
            }
        }
        return result;
    }

    public static double parseDouble(@Nullable String str) {
        return parseDouble(str, 0);
    }

    public static double parseDouble(@Nullable String str, double defaultValue) {
        double result = defaultValue;
        if (str != null) {
            try {
                result = Double.parseDouble(str);
            } catch (NumberFormatException ex) {
            }
        }
        return result;
    }
}
