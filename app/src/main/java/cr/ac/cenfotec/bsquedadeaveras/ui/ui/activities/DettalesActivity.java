package cr.ac.cenfotec.bsquedadeaveras.ui.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.chip.Chip;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import javax.security.auth.callback.CallbackHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Averia;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Ubicacion;
import cr.ac.cenfotec.bsquedadeaveras.R;
import cr.ac.cenfotec.bsquedadeaveras.webservices.GestorServicio;
import cr.ac.cenfotec.bsquedadeaveras.webservices.ServicioPosts;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DettalesActivity extends AppCompatActivity  implements OnMapReadyCallback {
    private GoogleMap gMap;
    private  LatLng mLatlng;
    MarkerOptions options;

    @BindView(R.id.det_nombre)
    TextView titulo;
    @BindView(R.id.chip_tipo)
    Chip chip;
    @BindView(R.id.det_des)
    TextView des;
    @BindView(R.id.fecha)
    TextView fecha;
    @BindView(R.id.por)
    TextView por;
    @BindView(R.id.det_img)
    ImageView imageView;

    Averia averiaG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalkes_averias);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String id = intent.getStringExtra("MyId");
        averiaG = (Averia) getIntent().getSerializableExtra("obj");
        mLatlng = new LatLng(averiaG.getUbicacion().getLatitude(),averiaG.getUbicacion().getLongitud());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.det_map);
        mapFragment.getMapAsync(this);
        Crear();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatlng, 12.0f));
        options = new MarkerOptions().position(mLatlng);
        gMap.addMarker(options);
    }


    public void Crear(){
        titulo.setText(averiaG.getNombre());
        chip.setText(averiaG.getTipo());
        des.setText(averiaG.getDescripcion());
        if (averiaG.getImagen() == null){
            imageView.setImageResource(R.drawable.back);
        }else if(averiaG.getImagen().equals("")) {
            imageView.setImageResource(R.drawable.back);
        }else {
            Picasso.get().load(averiaG.getImagen()).into(imageView);
            imageView.setContentDescription(averiaG.getImagen());
        }
        obtenerDettale(averiaG.getId());
    }
    public String randomString() {
        Random ran = new Random();
        int x = ran.nextInt(6) + 5;
        return x+"";
    }
    private void obtenerDettale(String id) {
        ServicioPosts servicio = GestorServicio.obtenerServicio();
        servicio.obtenerDetallesDeAveria(id).enqueue(new Callback<Averia>() {
            @Override
            public void onResponse(Call<Averia> call, Response<Averia> response) {
                Averia avera = response.body();
                por.setText(avera.getUsuario().nombre);
                fecha.setText(avera.getFecha());

            }

            @Override
            public void onFailure(Call<Averia> call, Throwable t) {

            }
        });
    }

}
