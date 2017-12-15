package com.shyky.java.plugin.service;

import com.shyky.java.plugin.bean.YouDaoChineseTranslateResult;
import com.shyky.java.plugin.constant.Languages;

import java.util.List;

public final class YouDaoChineseTransformService extends TransformService<YouDaoChineseTranslateResult> {
    @Override
    public String transform(String words, YouDaoChineseTranslateResult translateResult) {
        final StringBuilder htmlResult = new StringBuilder();
        // Title
        htmlResult.append("<b>").append(words).append("</b>");
        // Phonetic
        final YouDaoChineseTranslateResult.BasicBean basicInfo = translateResult.getBasic();
        if (basicInfo != null) {
            htmlResult.append("  ")
                    .append('[')
                    .append(basicInfo.getPhonetic())
                    .append(']');
        }
        // New line
        htmlResult.append("<br/>");
        // Basic Explains
        final List<String> translateWords = translateResult.getTranslation();
        if (translateWords.size() == 1) {
            htmlResult.append(Languages.TITLE_ENGLISH).append(Languages.TITLE_SEMICOLON).append(translateWords.get(0));
        } else {
            for (String word : translateWords) {
                htmlResult.append(Languages.TITLE_ENGLISH).append(Languages.TITLE_SEMICOLON).append(word).append("  ");
            }
        }
        // New line
        htmlResult.append("<br/>");
        // Detail Explains
        if (basicInfo != null) {
            final List<String> explains = basicInfo.getExplains();
            if (explains != null && !explains.isEmpty()) {
                // Foreach all the explains
                for (String explain : explains) {
                    htmlResult.append(explain);
                    // New line
                    htmlResult.append("<br/>");
                }
            }
        }
        // Web Explains
        htmlResult.append(Languages.TITLE_WEB_EXPLAIN).append(Languages.TITLE_SEMICOLON);
        // New line
        htmlResult.append("<br/>");
        final List<YouDaoChineseTranslateResult.WebBean> webExplains = translateResult.getWeb();
        if (webExplains != null && !webExplains.isEmpty()) {
            // Foreach all the explains
            for (YouDaoChineseTranslateResult.WebBean explain : webExplains) {
                htmlResult.append(explain.getKey()).append("  ").append(explain.getValue());
                // New line
                htmlResult.append("<br/>");
            }
        }
        return htmlResult.toString();
    }
}