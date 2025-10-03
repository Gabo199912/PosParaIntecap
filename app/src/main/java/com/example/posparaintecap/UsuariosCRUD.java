package com.example.posparaintecap;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.posparaintecap.Modelos.UsuarioModelo;
import com.example.posparaintecap.Retro.UsuarioCliente;
import com.example.posparaintecap.Retro.UsuarioServicio;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuariosCRUD extends AppCompatActivity {

    private UsuarioModelo usuarioModelo = new UsuarioModelo();

    private AutoCompleteTextView txtTipoUsuario;
    private TextInputEditText txtNombre;
    private TextInputEditText txtNombreUsuario;
    private TextInputEditText txtEmail;
    private TextInputEditText txtContrasenia;

    private TextView txtMensajeCreacion;
    private Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_usuarios_crud);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtTipoUsuario = findViewById(R.id.txtTipoUsuario);
        txtNombre = findViewById(R.id.txtNombre);
        txtNombreUsuario = findViewById(R.id.txtNombreUsuario);
        txtEmail = findViewById(R.id.txtEmail);
        txtContrasenia = findViewById(R.id.txtContrasenia);

        btnAgregar = findViewById(R.id.btnAgregar);



        String[] opciones = getResources().getStringArray(R.array.OPCIONES);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, opciones);
        txtTipoUsuario.setAdapter(adapter);


        btnAgregar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nombre = txtNombre.getText().toString().trim();
                String nombreUsuario = txtNombreUsuario.getText().toString().trim();
                String email = txtEmail.getText().toString().trim();
                String contrasenia = txtContrasenia.getText().toString().trim();
                String tipoUsuario = txtTipoUsuario.getText().toString().trim();

                usuarioModelo.setNombre(nombre);
                usuarioModelo.setNombre_usuario(nombreUsuario);
                usuarioModelo.setEmail(email);
                usuarioModelo.setContrasenia_hash(contrasenia);
                usuarioModelo.setTipo_usuario(tipoUsuario);

                UsuarioServicio usuarioServicio = UsuarioCliente.getRetrofit().create(UsuarioServicio.class);
                Call<UsuarioModelo> call = usuarioServicio.crear(usuarioModelo);

                call.enqueue(new Callback<UsuarioModelo>() {

                    @Override
                    public void onResponse(Call<UsuarioModelo> call, Response<UsuarioModelo> response) {
                        if (response.isSuccessful() && response.body() != null){
                            UsuarioModelo usuarioResponse = response.body();
                            txtMensajeCreacion.setText("Usuario "+ usuarioResponse.getNombre_usuario() + " creado exitosamente");
                            Log.d("RESPUESTA", "Respuesta del servidor true: " + response.code());
                        } else {
                            Log.d("RESPUESTA", "Respuesta del servidor else: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<UsuarioModelo> call, Throwable t) {
                        Log.d("RESPUESTA", "Error en la conexión: " + t.getMessage());
                    }
                });
            }
        });
    }


}