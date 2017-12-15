package com.shyky.java.plugin.service;

import com.shyky.java.plugin.constant.Configs;
import com.shyky.java.plugin.util.HttpUtil;
import com.shyky.java.plugin.util.JsonUtil;

public final class TranslateService {
    public static abstract class Callback<T> {
        private Class<T> clz;

        public Callback(Class<T> clz) {
            this.clz = clz;
        }

        public abstract void onSuccess(T translateResult, String response);

        public abstract void onFailure(Throwable error, String message);
    }

    public <T> void translate(String words, Callback<T> callback) {
        HttpUtil.sendGet(Configs.TRANSLATE_API_URL + words, new HttpUtil.Callback() {
            @Override
            public void onSuccess(String response) {
                if (callback != null) {
                    callback.onSuccess(JsonUtil.json2Entity(response, callback.clz/* YouDaoTranslateResult.class*/), response);
                }
            }

            @Override
            public void onFailure(Throwable error, String message) {
                if (callback != null) {
                    callback.onFailure(error, message);
                }
            }
        });
    }
}