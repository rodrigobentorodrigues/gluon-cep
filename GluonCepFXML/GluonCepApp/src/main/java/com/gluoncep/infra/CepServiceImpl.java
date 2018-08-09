package com.gluoncep.infra;

import com.gluoncep.service.CepService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author SOFTEC
 */
public class CepServiceImpl implements CepService {

    @Override
    public String searchValues(String cep) {
        try {
            URL url = new URL(cep);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.connect();
            int respCode = 0;
            do {
                respCode = con.getResponseCode();
            } while (respCode == -1);
            BufferedReader buffer = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String line = "";
            StringBuilder aux = new StringBuilder();
            while((line = buffer.readLine()) != null){
                aux.append(line);
            }
            con.disconnect();
            buffer.close();
            return aux.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }  
        
}
