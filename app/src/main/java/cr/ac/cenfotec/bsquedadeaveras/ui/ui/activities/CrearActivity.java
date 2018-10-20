package cr.ac.cenfotec.bsquedadeaveras.ui.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Averia;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Ubicacion;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Usuario;
import cr.ac.cenfotec.bsquedadeaveras.R;
import cr.ac.cenfotec.bsquedadeaveras.webservices.GestorServicio;
import cr.ac.cenfotec.bsquedadeaveras.webservices.ServicioPosts;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearActivity extends AppCompatActivity  implements OnMapReadyCallback , GoogleMap.OnMapLongClickListener{
    private GoogleMap gMap;
    private  LatLng mLatlng;
    MarkerOptions options;
    Marker marker;

    @BindView(R.id.nombre_crear)
    TextView titulo;
    @BindView(R.id.crear_tipo)
    TextView chip;
    @BindView(R.id.des_crear)
    TextView des;
    @BindView(R.id.crear_img)
    ImageView imageView;
    @BindView(R.id.crear)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_averias);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        LatLng ll = intent.getParcelableExtra("longLat");
        if (ll == null){
            mLatlng  = new LatLng(9.9328022,-84.0317056);

        }else {
            mLatlng = ll;
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_crear);
        mapFragment.getMapAsync(this);

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        LatLng larlng = new LatLng(9.9328022,-84.0317056);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatlng, 12.0f));
        options = new MarkerOptions().position(mLatlng);
        gMap.addMarker(options);
        gMap.setOnMapLongClickListener(this);
    }



    @OnClick(R.id.crear)
    public void Crear(){
        String s1 = titulo.getText().toString();
        String s2 =chip.getText().toString();
        String s3 =des.getText().toString();
        String id = getid();
        LatLng position = options.getPosition();
        Ubicacion ubicacion = new Ubicacion(position.latitude,position.longitude);
        Usuario usuario = MapActivity.usuario;
        if (usuario == null){
            usuario = MapActivity.usuarioP;
        }
        String fecha = getDate();
        Averia averia = new Averia(id,s1,s2,s3,"",ubicacion,usuario,fecha);
        ServicioPosts servicio = GestorServicio.obtenerServicio();
        servicio.crearNuevoAveria(averia).enqueue(new Callback<Averia>() {
            @Override
            public void onResponse(Call<Averia> call, Response<Averia> response) {
                Averia resultado = response.body();
                if (response.code() == 400){
                    Toast.makeText(CrearActivity.this,"No se pudo crear la averia intentelo otra ves",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(CrearActivity.this,"Se a creador una nueva veria",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Averia> call, Throwable t) {
                Toast.makeText(CrearActivity.this,
                        "Error al interactuar con el servicio",
                        Toast.LENGTH_SHORT).show();
            }
        });
        Intent myIntent = new Intent(this, MapActivity.class);
        startActivity(myIntent);
    }

    public String randomString() {
        Random r = new Random();
        int x = r.nextInt((100 - 100) + 1) + 100;
        return x+"";
    }

    public String getid() {
          final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_";
          final SecureRandom RANDOM = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; ++i) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        gMap.clear();
        options = new MarkerOptions().position(latLng);
        gMap.addMarker(options);
    }

    public String getDate(){
        return  new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }
}
