package com.example.posparaintecap.Modelos;

public class LoginModelo {
    private String nombre_usuario;
    private String contrasenia_hash;
    private String token;
    private String mensaje;

    public LoginModelo() {
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContrasenia_hash() {
        return contrasenia_hash;
    }

    public void setContrasenia_hash(String contrasenia_hash) {
        this.contrasenia_hash = contrasenia_hash;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
