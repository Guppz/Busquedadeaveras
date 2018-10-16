package cr.ac.cenfotec.bsquedadeaveras.ui.ui.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;
import cr.ac.cenfotec.bsquedadeaveras.manager.Adaptador.AdapterList;
import cr.ac.cenfotec.bsquedadeaveras.R;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Averia;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Ubicacion;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Usuario;

public class ListFragment extends Fragment {

    ArrayList<Averia> listaAverias;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        recyclerView=view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        llenardDatos();
        AdapterList adapterList = new AdapterList(listaAverias);
        recyclerView.setAdapter(adapterList);
        return view;
    }


    public void llenardDatos(){
        Usuario usuario = new Usuario("Fran@mail.com","Francisco","88314308","115040529");
        Ubicacion ubi = new Ubicacion(11,12);
        Averia averia = new Averia(1,"Prueba","test","10/10/2018","Testing","",usuario,ubi);

        Usuario usuario2 = new Usuario("Franc@mail.com","Franc","88314307","115040522");
        Ubicacion ubi2 = new Ubicacion(30,12);
        Averia averia2 = new Averia(2,"Prueba 2","test 2","10/10/2018","Testing 2","",usuario2,ubi2);


        listaAverias = new ArrayList<Averia>();
        listaAverias.add(averia);
        listaAverias.add(averia2);
    }
}
