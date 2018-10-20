package cr.ac.cenfotec.bsquedadeaveras.ui.ui.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.util.SortedList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Map;


import butterknife.ButterKnife;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Averia;
import cr.ac.cenfotec.bsquedadeaveras.R;
import cr.ac.cenfotec.bsquedadeaveras.ui.ui.activities.CrearActivity;
import cr.ac.cenfotec.bsquedadeaveras.ui.ui.activities.MapActivity;
import cr.ac.cenfotec.bsquedadeaveras.webservices.GestorServicio;
import cr.ac.cenfotec.bsquedadeaveras.webservices.ServicioPosts;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MapFragment extends Fragment implements OnMapReadyCallback ,GoogleMap.OnMapLongClickListener{

    private static final String TAG = "MapActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private Boolean mLocationPermissionGranted = false;
    private GoogleMap gMap;
    private LatLng mTempPosicion;
    private final static int PERM_CODE = 1;
    private FragmentActivity myContext;
    private List<Averia>averiasList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.map_fragment, container, false);
        ButterKnife.bind(this, view);
        SupportMapFragment mapFragment = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map));
        mapFragment.getMapAsync(this);
        obtenerPosts();
        return view;
    }

    private void chequearPermiso() {
        int state = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        if (state == PackageManager.PERMISSION_GRANTED) {
            gMap.setMyLocationEnabled(true);
            gMap.getUiSettings().setMyLocationButtonEnabled(true);
        } else {
            //Si no, pedimos permiso
            askForPermission();
        }
    }

    public void askForPermission(){
        //Pedimos permiso para el de tipo ACCESS_FINE_LOCATION
       requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERM_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED){
            chequearPermiso();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        LatLng cenfotec = new LatLng(9.9328022,-84.0317056);
        gMap.setOnMapLongClickListener(this);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cenfotec, 12.0f));
        chequearPermiso();
    }


    private void obtenerPosts(){
        ServicioPosts servicio = GestorServicio.obtenerServicio();
        servicio.obtenerTodosLasAverias().enqueue(new Callback<List<Averia>>() {
            @Override
            public void onResponse(Call<List<Averia>> call, Response<List<Averia>> response) {
                averiasList = response.body();
                setUpmapPoints();
            }

            @Override
            public void onFailure(Call<List<Averia>> call, Throwable t) {
                Toast.makeText(getContext(),"Error al interactuar con el servicio", Toast.LENGTH_SHORT).show();
                //t.toString();
                Log.d("Error",t.toString());
            }
        });

    }

    private void setUpmapPoints(){
        for (Averia a: averiasList) {
            LatLng latLng = new LatLng(a.getUbicacion().getLatitude(),a.getUbicacion().getLongitud());
            MarkerOptions options = new MarkerOptions().position(latLng).title(a.getNombre()).snippet(a.getDescripcion());
            gMap.addMarker(options);
        }
    }

    @Override
    public void onMapLongClick(final LatLng latLng) {


        new MaterialDialog.Builder(getContext())
                .title("Crear nueva averia")
                .content("Quiere crear nueva averia")
                .positiveText("Si")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        Intent formIntent = new Intent(getContext(), CrearActivity.class);
                        //formIntent.putExtra("latLng",latLng);
                        Bundle args = new Bundle();
                        args.putParcelable("longLat", latLng);
                        formIntent.putExtras(args);
                        startActivityForResult(formIntent, 100);
                    }
                })
                .negativeText("No")
                .show();
    }
}
