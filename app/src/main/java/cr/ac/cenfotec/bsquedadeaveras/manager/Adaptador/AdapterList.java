package cr.ac.cenfotec.bsquedadeaveras.manager.Adaptador;

import android.support.annotation.NonNull;
import android.support.design.chip.Chip;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cr.ac.cenfotec.bsquedadeaveras.R;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Averia;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder> {
    List<Averia> listaAverias;
    private OnBtnClick btnClick;
    public AdapterList(List<Averia> listaAverias) {
        this.listaAverias = listaAverias;
    }

    public  void setOnBtnClickListener(OnBtnClick listener){
        btnClick = listener;

    }

    @Override
    public AdapterList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_list,null,false);
        return new ViewHolder(view , btnClick);
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
        TextView averiaNombre ,desAveria , id;
        Chip tipo;
        ImageView image;
        Button btnEditar , btnEleminar;
        CardView cardView;


        public ViewHolder(View itemView , final OnBtnClick  listener) {
            super(itemView);
            averiaNombre = itemView.findViewById(R.id.averia_nombre);
            tipo = itemView.findViewById(R.id.tipo_averia);
            desAveria = itemView.findViewById(R.id.des_averia);
            image = itemView.findViewById(R.id.photo);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEleminar = itemView.findViewById(R.id.btnEleminar);
            id = itemView.findViewById(R.id.id_averia);
            cardView=itemView.findViewById(R.id.card);

           btnEditar.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (listener != null){
                       int pos = getAdapterPosition();
                       if (pos != RecyclerView.NO_POSITION){
                           listener.onBtnClick(pos);
                       }
                   }
               }
           });

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            listener.onCardClick(pos);
                        }
                    }
                }
            });

            btnEleminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            listener.onEliminarClick(pos);
                        }
                    }
                }
            });
        }
        public void asignarDatos(Averia averia) {
            averiaNombre.setText(averia.getNombre());
            tipo.setText(averia.getTipo());
            desAveria.setText(averia.getDescripcion());
            id.setText(averia.getId());
            if (averia.getImagen() == null){
                image.setImageResource(R.drawable.back);
            }else if(averia.getImagen().equals("")){
                image.setImageResource(R.drawable.back);
            }else {
                Picasso.get().load(averia.getImagen()).into(image);
            }
        }

    }
}
