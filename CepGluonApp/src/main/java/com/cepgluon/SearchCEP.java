package com.cepgluon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author rodrigo
 */
public class SearchCEP {

    public String getValues(String valueURL) {
        URL url;
        try {
            url = new URL(valueURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            int respCode = -1;
            do {
                if (connection != null) {
                    respCode = connection.getResponseCode();
                }
            } while (respCode == -1);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = buffer.readLine()) != null) {
                stringBuilder.append(line);
            }
            buffer.close();
            connection.disconnect();
            return stringBuilder.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
