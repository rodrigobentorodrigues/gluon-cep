package com.gluoncep.infra;

import org.json.JSONObject;

public class CEPRepo {

    public static JSONObject cep;

    public static JSONObject getCep() {
        return cep;
    }

    public static void setCep(JSONObject cep) {
        CEPRepo.cep = cep;
    }
    
}
