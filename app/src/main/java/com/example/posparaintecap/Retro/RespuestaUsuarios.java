package com.example.posparaintecap.Retro;

public class RespuestaUsuarios {
    private String nombre_usuario;
    private String Respuesta;

    public RespuestaUsuarios(String nombre_usuario, String Respuesta) {
        this.nombre_usuario = nombre_usuario;
        this.Respuesta = Respuesta;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }
    public String getRespuesta() {
        return Respuesta;
    }
    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }
    public void setRespuesta(String Respuesta) {
        this.Respuesta = Respuesta;
    }
}
