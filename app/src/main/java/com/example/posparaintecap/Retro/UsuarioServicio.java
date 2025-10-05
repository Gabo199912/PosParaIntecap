package com.example.posparaintecap.Retro;


import com.example.posparaintecap.Modelos.LoginModelo;
import com.example.posparaintecap.Modelos.UsuarioModelo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioServicio {

    @POST("login")
    Call<LoginModelo> login(@Body LoginModelo login);

    @POST("usuarios/administrador/crear")
    Call<RespuestaUsuarios> crear(@Body UsuarioModelo usuarios);

    @GET("/usuarios/vendedor/todos")
    Call<List<UsuarioModelo>> listarUsuarios();

    @DELETE("/usuarios/administrador/eliminar/{nombreUsuario}")
    Call<RespuestaUsuarios> eliminar(@Path("nombreUsuario") String nombreUsuario);
}
