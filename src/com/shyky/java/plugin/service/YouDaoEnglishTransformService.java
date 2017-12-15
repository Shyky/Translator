package com.shyky.java.plugin.service;

import com.shyky.java.plugin.bean.YouDaoEnglishTranslateResult;
import com.shyky.java.plugin.constant.Languages;

import java.util.List;

public final class YouDaoEnglishTransformService extends TransformService<YouDaoEnglishTranslateResult> {
    @Override
    public String transform(String words, YouDaoEnglishTranslateResult translateResult) {
        {
            final StringBuilder htmlResult = new StringBuilder();
            // Title
            htmlResult.append("<b>").append(words).append("</b>");
            // New line
            htmlResult.append("<br/>");
            // Phonetic
            final YouDaoEnglishTranslateResult.BasicBean basicInfo = translateResult.getBasic();
            if (basicInfo != null) {
                htmlResult.append(Languages.TITLE_UK)
                        .append('[')
                        .append(basicInfo.getUk_phonetic())
                        .append(']')
                        .append("  ")
                        .append(Languages.TITLE_US)
                        .append('[')
                        .append(basicInfo.getUs_phonetic())
                        .append(']');
            }
            // New line
            htmlResult.append("<br/>");
            // Basic Explains
            final List<String> translateWords = translateResult.getTranslation();
            if (translateWords.size() == 1) {
                htmlResult.append(Languages.TITLE_EXPLAIN).append(Languages.TITLE_SEMICOLON).append(translateWords.get(0));
            } else {
                for (String word : translateWords) {
                    htmlResult.append(Languages.TITLE_EXPLAIN).append(Languages.TITLE_SEMICOLON).append(translateWords.get(0)).append("  ");
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
            final List<YouDaoEnglishTranslateResult.WebBean> webExplains = translateResult.getWeb();
            if (webExplains != null && !webExplains.isEmpty()) {
                // Foreach all the explains
                for (YouDaoEnglishTranslateResult.WebBean explain : webExplains) {
                    htmlResult.append(explain.getKey()).append("  ").append(explain.getValue());
                    // New line
                    htmlResult.append("<br/>");
                }
            }
            return htmlResult.toString();
        }
    }
}