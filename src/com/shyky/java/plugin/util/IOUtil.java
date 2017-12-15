package com.shyky.java.plugin.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class IOUtil {
    private IOUtil() {

    }

    public static String inputStream2String(InputStream inputStream) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String temp;
            StringBuilder result = new StringBuilder();
            while ((temp = reader.readLine()) != null) {
                result.append(temp).append("\n");
            }
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}