package com.example.vezbe10_1.service;

import android.util.Log;

import com.example.vezbe10_1.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceUtils {
//    http://<service_ip_adress>:<service_port>/messages
    //EXAMPLE: http://192.168.43.73:8081/messages
    public static final String SERVICE_API_PATH = "http://" + BuildConfig.IP_ADDR + ":8081/messages/";

    /*
     * Ovo ce nam sluziti za debug, da vidimo da li zahtevi i odgovoru idu
     * odnosno dolaze i kako izgeldaju.
     * */
    public static OkHttpClient test(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();

        return client;
    }
    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    /*
     * Prvo je potrebno da definisemo retrofit instancu preko koje ce komunikacija ici
     * */
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(SERVICE_API_PATH)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(test())
            .build();
    /*
     * Definisemo konkretnu instancu servisa na intnerntu sa kojim
     * vrsimo komunikaciju
     * */
    public static MessageRestService messageRestService = retrofit.create(MessageRestService.class);
}
