package cr.ac.cenfotec.bsquedadeaveras.ui.ui.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.List;

import butterknife.ButterKnife;
import cr.ac.cenfotec.bsquedadeaveras.manager.Adaptador.AdapterList;
import cr.ac.cenfotec.bsquedadeaveras.R;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Averia;
import cr.ac.cenfotec.bsquedadeaveras.manager.Adaptador.OnBtnClick;
import cr.ac.cenfotec.bsquedadeaveras.ui.ui.activities.DettalesActivity;
import cr.ac.cenfotec.bsquedadeaveras.ui.ui.activities.EditarActivity;
import cr.ac.cenfotec.bsquedadeaveras.ui.ui.activities.MapActivity;
import cr.ac.cenfotec.bsquedadeaveras.webservices.GestorServicio;
import cr.ac.cenfotec.bsquedadeaveras.webservices.ServicioPosts;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment {

    List<Averia> listaAverias;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        recyclerView=view.findViewById(R.id.rv);
        obtenerPosts();
        return view;
    }





    private void obtenerPosts(){
        ServicioPosts servicio = GestorServicio.obtenerServicio();
        servicio.obtenerTodosLasAverias().enqueue(new Callback<List<Averia>>() {
            @Override
            public void onResponse(Call<List<Averia>> call, Response<List<Averia>> response) {
                listaAverias = response.body();
                makeList();

            }

            @Override
            public void onFailure(Call<List<Averia>> call, Throwable t) {
                Toast.makeText(getContext(),"Error al interactuar con el servicio", Toast.LENGTH_SHORT).show();
                //t.toString();
                Log.d("Error",t.toString());
            }
        });

    }

    private  void makeList(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        AdapterList adapterList = new AdapterList(listaAverias);
        recyclerView.setAdapter(adapterList);

        adapterList.setOnBtnClickListener(new OnBtnClick() {
            @Override
            public void onBtnClick(int pos) {
                Intent myIntent = new Intent(getContext(), EditarActivity.class);
                myIntent.putExtra("MyId",listaAverias.get(pos).getId());
                myIntent.putExtra("obj",listaAverias.get(pos));
                startActivity(myIntent);
            }

            @Override
            public void onCardClick(int pos) {
                Intent myIntent = new Intent(getContext(), DettalesActivity.class);
                myIntent.putExtra("obj",listaAverias.get(pos));
                startActivity(myIntent);
            }

            @Override
            public void onEliminarClick(int pos) {

                   final String id = listaAverias.get(pos).getId();
                     new MaterialDialog.Builder(getContext())
                    .title("Esta seguro")
                    .content("Desea borrar esta averia?")
                    .positiveText("Si")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            borrarTodo(id);
                        }
                    })
                    .negativeText("No")
                    .show();

            }
        });
    }

    public void borrarTodo(String id){
        ServicioPosts servicio = GestorServicio.obtenerServicio();
        servicio.deleteAveria(id).enqueue(new Callback<Averia>() {
            @Override
            public void onResponse(Call<Averia> call, Response<Averia> response) {
                Averia resultado = response.body();
                Toast.makeText(getContext(), "Borrado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Averia> call, Throwable t) {
                Toast.makeText(getContext(),
                        "Error al interactuar con el servicio",
                        Toast.LENGTH_SHORT).show();
            }
        });
        Intent myIntent = new Intent(getContext(), MapActivity.class);
        startActivity(myIntent);
    }
}
