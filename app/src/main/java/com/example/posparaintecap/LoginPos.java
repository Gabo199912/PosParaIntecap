package com.example.posparaintecap;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.posparaintecap.Modelos.LoginModelo;
import com.example.posparaintecap.Retro.LoginCliente;
import com.example.posparaintecap.Retro.LoginServicio;
import com.example.posparaintecap.TokenSingleton.GuardarTokenSingleton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPos extends AppCompatActivity {

    LoginModelo loginPos = new LoginModelo();
    private TextInputEditText txUsuario;
    private TextInputEditText txContrasenia;
    private TextView txtMensajeLogin;
    private Button loginBoton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_pos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txUsuario = findViewById(R.id.txUsuarioTexto);
        txContrasenia = findViewById(R.id.txContraseniaTexto);
        txtMensajeLogin = findViewById(R.id.txtMensajeLogin);
        loginBoton = findViewById(R.id.loginBoton);




        loginBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = txUsuario.getText().toString().trim();
                String contrasenia = txContrasenia.getText().toString().trim();
                loginPos.setNombre_usuario(usuario);
                loginPos.setContrasenia_hash(contrasenia);


                LoginServicio loginServicio = LoginCliente.getRetrofit().create(LoginServicio.class);
                Call<LoginModelo> call = loginServicio.login(loginPos);

                Log.d("LO QUE SE ENVIA", "HACIA EL SERVIDOR:" + usuario);
                Log.d("LO QUE SE ENVIA", "HACIA EL SERVIDOR:" + contrasenia);
                call.enqueue(new Callback<LoginModelo>() {
                    @Override
                    public void onResponse(Call<LoginModelo> call, Response<LoginModelo> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            LoginModelo loginResponse = response.body();
                            txtMensajeLogin.setText(loginResponse.getNombre_usuario()+ " " + loginResponse.getMensaje() + " " + loginResponse.getToken());

                            GuardarTokenSingleton.setToken(loginResponse.getToken());

                            Log.d("RESPUESTA", "Respuesta del servidor true: " + response.code());
                        } else {
                            txtMensajeLogin.setText("Error en la respuesta del servidor");
                            Log.d("RESPUESTA", "Respuesta del servidor else: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginModelo> call, Throwable t) {
                        txtMensajeLogin.setText("Error en la conexión: " + t.getMessage());
                        Log.d("RESPUESTA", "Error en la conexión: " + t.getMessage());
                    }
                });
            }
        });

    }
}