package com.prostate.doctor.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HttpsConnectionUtils {

    public static String conn(String urlStr) {
        StringBuilder json = new StringBuilder();
        try {
            URL url = new URL(urlStr);
            URLConnection uc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
            // 将json字符串转成javaBean
            return json.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";

    }
}