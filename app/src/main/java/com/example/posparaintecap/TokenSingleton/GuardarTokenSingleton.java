package com.example.posparaintecap.TokenSingleton;

public class GuardarTokenSingleton {
    private static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        GuardarTokenSingleton.token = token;
    }
}
