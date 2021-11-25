package br.gov.mt.saude.cuiaba.e_patrimonio.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import br.gov.mt.saude.cuiaba.e_patrimonio.constants.Constantes;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiClient {

    private static final String BASE_URL = new Constantes().IPSERVIDORPATRIMONIO;//I used IP of my local machine

    private static Retrofit retrofit = null;

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private RetrofitApiClient() {} // So that nobody can create an object with constructor

    public static synchronized Retrofit getClient() {
        if (retrofit==null) {

            int timeOut = 1 * 60;
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(timeOut, TimeUnit.SECONDS)
                    .writeTimeout(timeOut, TimeUnit.SECONDS)
                    .readTimeout(timeOut, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }
        return retrofit;

    }

}
