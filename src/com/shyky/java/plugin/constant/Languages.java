package com.shyky.java.plugin.constant;

public final class Languages {
    public static final String TITLE_US = getTitleUs();
    public static final String TITLE_UK = getTitleUk();
    public static final String TITLE_ENGLISH = getTitleEnglish();
    public static final String TITLE_EXPLAIN = getTitleExplain();
    public static final String TITLE_SEMICOLON = getTitleSemicolon();
    public static final String TITLE_WEB_EXPLAIN = getTitleWebExplain();

    private static String getTitleUs() {
        return "美";
    }

    private static String getTitleUk() {
        return "英";
    }

    private static String getTitleEnglish() {
        return "英语";
    }

    private static String getTitleExplain() {
        return "解释";
    }

    private static String getTitleSemicolon() {
        return "：";
    }

    private static String getTitleWebExplain() {
        return "网络释义";
    }

    private Languages() {

    }
}