package com.example.posparaintecap;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.posparaintecap.Adaptadores.UsuariosAdaptador;
import com.example.posparaintecap.Modelos.UsuarioModelo;
import com.example.posparaintecap.Retro.UsuarioCliente;
import com.example.posparaintecap.Retro.UsuarioServicio;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaUsuarios extends AppCompatActivity {

    private RecyclerView rvUsuarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_usuarios);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rvUsuarios = findViewById(R.id.rvUsuarios);
        rvUsuarios.setLayoutManager(new LinearLayoutManager(this));


        llenarLista();
    }

    public void regresarCrearUsuario(View view) {
        Intent intent = new Intent(ListaUsuarios.this, UsuariosCRUD.class);
        startActivity(intent);
    }

    public void actualizar(View view) {
        llenarLista();
        Toast.makeText(ListaUsuarios.this, "USUARIOS ACTUALIZADOS", Toast.LENGTH_SHORT).show();
    }

    public void llenarLista() {
        UsuarioServicio listarUsuarios = UsuarioCliente.getRetrofit().create(UsuarioServicio.class);
        Call<List<UsuarioModelo>> call = listarUsuarios.listarUsuarios();

        call.enqueue(new Callback<List<UsuarioModelo>>() {
            @Override
            public void onResponse(Call<List<UsuarioModelo>> call, Response<List<UsuarioModelo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<UsuarioModelo> listaUsuarios = response.body();
                    UsuariosAdaptador adaptador = new UsuariosAdaptador(listaUsuarios);

                    rvUsuarios.setAdapter(adaptador);
                    Log.d("OK", "Respuesta del servidor true: " + response.code());
                } else {
                    Log.d("BAD", "Respuesta del servidor else: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<UsuarioModelo>> call, Throwable t) {
                Toast.makeText(ListaUsuarios.this, "Error en la conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("ERROR", "Error en la conexión: " + t.getMessage());
            }
        });
    }
}