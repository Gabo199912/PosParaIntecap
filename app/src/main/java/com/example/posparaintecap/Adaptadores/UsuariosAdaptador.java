package com.example.posparaintecap.Adaptadores;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.posparaintecap.Modelos.UsuarioModelo;
import com.example.posparaintecap.R;
import com.example.posparaintecap.Retro.RespuestaUsuarios;
import com.example.posparaintecap.Retro.UsuarioCliente;
import com.example.posparaintecap.Retro.UsuarioServicio;
import com.example.posparaintecap.UsuariosCRUD;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuariosAdaptador extends RecyclerView.Adapter<UsuariosAdaptador.ViewHolder> {

    public List<UsuarioModelo> listaUsuarios;

    public UsuariosAdaptador(List<UsuarioModelo> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNombreUsuario;
        private TextView txtCorreoUsuario;
        private TextView txtTipoUsuario;
        private Button btnEliminar;
        private Button btnEditar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombreUsuario = itemView.findViewById(R.id.txtNombreUsuario);
            txtCorreoUsuario = itemView.findViewById(R.id.txtCorreoUsuario);
            txtTipoUsuario = itemView.findViewById(R.id.txtTipoUsuario);

            btnEliminar = itemView.findViewById(R.id.btnEliminar);
            btnEditar = itemView.findViewById(R.id.btnEditar);

        }
    }


    @NonNull
    @Override
    public UsuariosAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.usuarios_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuariosAdaptador.ViewHolder holder, int position) {
        UsuarioModelo usuario = listaUsuarios.get(position);
        holder.txtNombreUsuario.setText(usuario.getNombre());
        holder.txtCorreoUsuario.setText(usuario.getEmail());
        holder.txtTipoUsuario.setText(usuario.getTipo_usuario());

        holder.btnEliminar.setOnClickListener(View -> eliminarUsuario(usuario.getNombre_usuario(), holder, position));
        holder.btnEditar.setOnClickListener(View -> btnEditar(holder));

    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public void btnEditar(UsuariosAdaptador.ViewHolder holder){
        Toast.makeText(holder.itemView.getContext(), "TRABAJANDO EN ELLO", Toast.LENGTH_SHORT).show();

    }


    public void eliminarUsuario(String nombreUsuario, UsuariosAdaptador.ViewHolder holder, int position){
        UsuarioServicio usuarioServicio = UsuarioCliente.getRetrofit().create(UsuarioServicio.class);
        Call<RespuestaUsuarios> call = usuarioServicio.eliminar(nombreUsuario);

        Gson gson = new Gson();
        String json = gson.toJson(nombreUsuario);
        Log.d("LO QUE SE ENVIA", "HACIA EL SERVIDOR:" + json);

        call.enqueue(new Callback<RespuestaUsuarios>() {
            @Override
            public void onResponse(Call<RespuestaUsuarios> call, Response<RespuestaUsuarios> response) {
                if (response.isSuccessful() && response.body() != null){
                    listaUsuarios.remove(position);
                    notifyItemRemoved(position);

                    Toast.makeText(holder.itemView.getContext(), "USUARIO ELIMINADO", Toast.LENGTH_SHORT).show();
                    Log.d("OK", "Respuesta del servidor true: " + response.code());

                }else{
                    Toast.makeText(holder.itemView.getContext(), "ERROR EN LA RESPUESTA", Toast.LENGTH_SHORT).show();
                    Log.d("ERROR", "Respuesta del servidor else: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<RespuestaUsuarios> call, Throwable t) {
                Log.d("ERROR", "Error en la conexión: " + t.getMessage());

            }
        });
    }
}
