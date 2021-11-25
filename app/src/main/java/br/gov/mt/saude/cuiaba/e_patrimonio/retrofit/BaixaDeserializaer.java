package br.gov.mt.saude.cuiaba.e_patrimonio.retrofit;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import br.gov.mt.saude.cuiaba.e_patrimonio.modelo.Baixa;

public class BaixaDeserializaer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            JsonElement element = json.getAsJsonObject();

            if(json.getAsJsonObject() != null){
                element = json.getAsJsonObject();
            }

            return (new Gson().fromJson(element, Baixa.class));

        }catch (JsonParseException ex){
            Log.e("Error:",ex.toString());
            return null;
        }
    }
}
