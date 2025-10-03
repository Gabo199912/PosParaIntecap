package com.example.posparaintecap.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.posparaintecap.Modelos.UsuarioModelo;
import com.example.posparaintecap.R;

import org.w3c.dom.Text;

import java.util.List;

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

    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }
}
