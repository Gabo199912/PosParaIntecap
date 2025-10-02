package com.example.posparaintecap.Retro;


import com.example.posparaintecap.Modelos.LoginModelo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginServicio {

    @POST("login")
    Call<LoginModelo> login(@Body LoginModelo login);
}
