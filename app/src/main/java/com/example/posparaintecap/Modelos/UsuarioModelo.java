package com.example.posparaintecap.Modelos;

public class UsuarioModelo {
    private String nombre;
    private String nombre_usuario;
    private String email;
    private String contrasenia_hash;
    private String tipo_usuario;

    public UsuarioModelo() {
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia_hash() {
        return contrasenia_hash;
    }

    public void setContrasenia_hash(String contrasenia_hash) {
        this.contrasenia_hash = contrasenia_hash;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
}
