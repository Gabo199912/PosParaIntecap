package com.example.posparaintecap.Interceptores;

import androidx.annotation.NonNull;

import com.example.posparaintecap.TokenSingleton.GuardarTokenSingleton;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        String token = GuardarTokenSingleton.getToken();

        Request request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .build();

        return chain.proceed(request);
    }
}
