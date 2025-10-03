package com.example.posparaintecap.Retro;


import com.example.posparaintecap.Modelos.LoginModelo;
import com.example.posparaintecap.Modelos.UsuarioModelo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UsuarioServicio {

    @POST("login")
    Call<LoginModelo> login(@Body LoginModelo login);

    @POST("usuarios/administrador/crear")
    Call<UsuarioModelo> crear(@Body UsuarioModelo usuarios);
}
