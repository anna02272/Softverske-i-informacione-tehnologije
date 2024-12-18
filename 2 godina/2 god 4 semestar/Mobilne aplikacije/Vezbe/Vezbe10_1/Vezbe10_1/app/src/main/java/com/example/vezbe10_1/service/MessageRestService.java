package com.example.vezbe10_1.service;


import com.example.vezbe10_1.model.Message;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
/*
 * Klasa koja opisuje koji tj mapira putanju servisa
 * opisuje koji metod koristimo ali i sta ocekujemo kao rezultat
 * */
public interface MessageRestService {

    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json",
            "Accept: application/json"
    })
    @POST(".")
    Call<Message> sendMessage(@Body String message);

    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json",
            "Accept: application/json"
    })
    @GET(".")
    Call<List<Message>> getMessages();
}
