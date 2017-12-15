package com.shyky.java.plugin.util;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public final class HttpUtil {
    public interface Callback {
        void onSuccess(String response);

        void onFailure(Throwable error, String message);
    }

    private HttpUtil() {

    }

    public static String sendGet(String url) {
        return null;
    }

    public static void sendGet(String url, Callback callback) {
        try {
            URL u = new URL(url);
            URLConnection connection = u.openConnection();
            final String response = IOUtil.inputStream2String(connection.getInputStream());
            if (callback != null) {
                callback.onSuccess(response);
            }
        } catch (MalformedURLException e) {
//            e.printStackTrace();
            if (callback != null) {
                callback.onFailure(e.getCause(), e.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (callback != null) {
                callback.onFailure(e.getCause(), e.getMessage());
            }
        }
    }
}