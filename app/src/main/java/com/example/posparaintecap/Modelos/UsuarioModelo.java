package com.example.posparaintecap.Modelos;

public class UsuarioModelo {

    private int id_usuario;
    private String nombre;
    private String nombre_usuario;
    private String email;
    private String contrasenia_hash;
    private String tipo_usuario;

    public UsuarioModelo() {
    }

    public UsuarioModelo(int id_usuario, String nombre, String nombre_usuario, String email, String contrasenia_hash, String tipo_usuario) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.nombre_usuario = nombre_usuario;
        this.email = email;
        this.contrasenia_hash = contrasenia_hash;
        this.tipo_usuario = tipo_usuario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
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
