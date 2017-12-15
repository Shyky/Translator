package com.shyky.java.plugin.util;

public final class TextUtil {
    private TextUtil() {

    }

    public static boolean isEmpty(CharSequence s) {
        if (s == null) {
            return true;
        } else {
            if (s instanceof String) {
                return ((String) s).trim().length() == 0;
            }
            return s.length() == 0;
        }
    }

    public static boolean isEnglish(String str) {
        return !isEmpty(str) && str.matches("^[A-Za-z]+$");
    }

    public static boolean isChinese(String str) {
        if (str != null && !str.trim().isEmpty()) {
            char[] chars = str.toCharArray();
            for (char c : chars) {
                if (isChinese(c)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }
}