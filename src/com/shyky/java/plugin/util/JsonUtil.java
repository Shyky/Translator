package com.shyky.java.plugin.util;

import com.google.gson.Gson;

public final class JsonUtil {
    private JsonUtil() {

    }

    public static <T> T json2Entity(String json, Class<T> clz) {
        if (!TextUtil.isEmpty(json)) {
            Gson gson = new Gson();
            // Replace the json key which contains the specific character such as '-'.
            return gson.fromJson(json.replaceAll("-", "_"), clz);
        }
        return null;
    }
}