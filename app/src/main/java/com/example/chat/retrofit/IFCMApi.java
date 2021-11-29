package com.example.chat.retrofit;

import com.example.chat.models.FCMBody;
import com.example.chat.models.FCMResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IFCMApi {

    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAAJx9kuMM:APA91bGohhrRjVSMHvUGdSlGpiGgv6LJAdyG4OplD0FZxwxl-KSCXUvHHMIlenuEdjiwh3BQu6LuuCWUk7rqRUGqgSBWy_LsW9hweD7Yi_jMc0izQtHarrzvvmCtErPUagRyx5Wqp0cX"
    })
    @POST("fcm/send")
    Call<FCMResponse> send(@Body FCMBody body);

}
