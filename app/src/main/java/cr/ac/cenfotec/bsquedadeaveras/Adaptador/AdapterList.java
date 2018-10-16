package cr.ac.cenfotec.bsquedadeaveras.Adaptador;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cr.ac.cenfotec.bsquedadeaveras.R;
import cr.ac.cenfotec.bsquedadeaveras.entities.Averia;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder> {
    ArrayList<Averia>listaAverias;

    public AdapterList(ArrayList<Averia> listaAverias) {
        this.listaAverias = listaAverias;
    }

    @Override
    public AdapterList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_list,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterList.ViewHolder holder, int position) {
            holder.asignarDatos(listaAverias.get(position));
    }

    @Override
    public int getItemCount() {
        return listaAverias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView creado , averiaNombre , tipoAveria,desAveria,fecha;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            creado = itemView.findViewById(R.id.creado);
            averiaNombre = itemView.findViewById(R.id.averia_nombre);
            tipoAveria = itemView.findViewById(R.id.tipo_averia);
            desAveria = itemView.findViewById(R.id.des_averia);
            fecha = itemView.findViewById(R.id.fecha);
            image = itemView.findViewById(R.id.photo);
        }
        public void asignarDatos(Averia averia) {
            creado.setText(averia.getUsuario().getNombre());
            averiaNombre.setText(averia.getNombre());
            tipoAveria.setText(averia.getTipo());
            desAveria.setText(averia.getDescripcion());
            fecha.setText(averia.getFecha());
            image.setImageResource(R.drawable.up);
        }
    }
}
