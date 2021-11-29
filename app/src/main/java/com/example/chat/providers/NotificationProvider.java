package com.example.chat.providers;

import com.example.chat.models.FCMBody;
import com.example.chat.models.FCMResponse;
import com.example.chat.retrofit.IFCMApi;
import com.example.chat.retrofit.RetrofitClient;

import retrofit2.Call;

public class NotificationProvider {

    private String url = "https://fcm.googleapis.com";

    public NotificationProvider() {

    }

    public Call<FCMResponse> sendNotification(FCMBody body) {
        return RetrofitClient.getClient(url).create(IFCMApi.class).send(body);
    }
}
