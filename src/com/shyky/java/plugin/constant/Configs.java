package com.shyky.java.plugin.constant;

public final class Configs {
    private static final String TRANSLATE_API_URL_PLATFORM_YOU_DAO = "http://fanyi.youdao.com/openapi.do?keyfrom=Skykai521&key=977124034&type=data&doctype=json&version=1.1&q=";
    public static final String TRANSLATE_API_URL = getTranslatePlatform();

    private static String getTranslatePlatform() {
        return TRANSLATE_API_URL_PLATFORM_YOU_DAO;
    }
}