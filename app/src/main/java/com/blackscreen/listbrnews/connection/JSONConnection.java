package com.blackscreen.listbrnews.connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Hamon on 14/02/2018.
 */

public class JSONConnection {

    public static String getJsonAsString (String urlAsString) {

        HttpURLConnection http = null;
        BufferedReader bufferedReader = null;

        try {

            //Parse string to URL and connect
            URL url = new URL(urlAsString);
            http = (HttpURLConnection) url.openConnection();

            //Initialize reader
            bufferedReader = new BufferedReader(new InputStreamReader(http.getInputStream()));

            //Read lines from buffered reader
            String currentLine = "";
            StringBuffer stringBuffer = new StringBuffer();
            while ((currentLine = bufferedReader.readLine()) != null) {
                stringBuffer.append(currentLine);
            }

            return stringBuffer.toString();

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {

            //Close http connection and buffered reader
            if (http != null) {
                http.disconnect();
            }

            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }

    }

}
