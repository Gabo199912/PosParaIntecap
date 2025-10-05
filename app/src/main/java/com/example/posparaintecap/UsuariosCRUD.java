package com.example.posparaintecap;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.posparaintecap.Modelos.UsuarioModelo;
import com.example.posparaintecap.Retro.RespuestaUsuarios;
import com.example.posparaintecap.Retro.UsuarioCliente;
import com.example.posparaintecap.Retro.UsuarioServicio;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

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

    private FloatingActionButton fbtnListarUsuarios;

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
        txtMensajeCreacion = findViewById(R.id.txtMensajeCreacion);

        btnAgregar = findViewById(R.id.btnAgregar);
        fbtnListarUsuarios = findViewById(R.id.fbtnListarUsuarios);


        String[] opciones = getResources().getStringArray(R.array.OPCIONES);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, opciones);
        txtTipoUsuario.setAdapter(adapter);

        fbtnListarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UsuariosCRUD.this, ListaUsuarios.class);
                startActivity(intent);
            }
        });


        btnAgregar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                usuarioModelo.setNombre(txtNombre.getText().toString().trim());
                usuarioModelo.setNombre_usuario(txtNombreUsuario.getText().toString().trim());
                usuarioModelo.setEmail(txtEmail.getText().toString().trim());
                usuarioModelo.setContrasenia_hash(txtContrasenia.getText().toString().trim());
                usuarioModelo.setTipo_usuario(txtTipoUsuario.getText().toString().trim());


                Gson gson = new Gson();
                String json = gson.toJson(usuarioModelo);
                Log.d("LO QUE SE ENVIA", "HACIA EL SERVIDOR:" + json);

                UsuarioServicio usuarioServicio = UsuarioCliente.getRetrofit().create(UsuarioServicio.class);
                Call<RespuestaUsuarios> call = usuarioServicio.crear(usuarioModelo);

                call.enqueue(new Callback<RespuestaUsuarios>() {

                    @Override
                    public void onResponse(Call<RespuestaUsuarios> call, Response<RespuestaUsuarios> response) {
                        if (response.isSuccessful()){
                            RespuestaUsuarios respuestaUsuarios = response.body();
                            String nombreUsuario = respuestaUsuarios.getNombre_usuario();
                            String respuesta = respuestaUsuarios.getRespuesta();

                            Toast.makeText(UsuariosCRUD.this, "USUARIO CREADO", Toast.LENGTH_SHORT).show();

                            limpiarCampos();
                            Log.d("RESPUESTA", "NOMBRE USUARIO:" + nombreUsuario);
                            Log.d("RESPUESTA", "RESPUESTA:" + respuesta);
                        }else{
                            Toast.makeText(UsuariosCRUD.this, "ERROR EN LA RESPUESTA", Toast.LENGTH_SHORT).show();
                            Log.d("RESPUESTA", "CODIGO DE RESPUESTA:" + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<RespuestaUsuarios> call, Throwable t) {
                        txtMensajeCreacion.setText("ERROR EN LA RESPUESTA" + t.getMessage());
                        Log.d("RESPUESTA", "ERROR EN LA RESPUESTA");
                    }
                });
            }
        });
    }


    public void limpiarCampos(){
        txtNombre.setText("");
        txtNombreUsuario.setText("");
        txtEmail.setText("");
        txtContrasenia.setText("");

    }

}