package cr.ac.cenfotec.bsquedadeaveras.ui.ui.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.Map;

import butterknife.ButterKnife;
import cr.ac.cenfotec.bsquedadeaveras.R;
import cr.ac.cenfotec.bsquedadeaveras.ui.ui.activities.MapActivity;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private static final String TAG = "MapActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private Boolean mLocationPermissionGranted = false;
    private GoogleMap gMap;
    private LatLng mTempPosicion;
    private final static int PERM_CODE = 1;
    private FragmentActivity myContext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.map_fragment, container, false);
        ButterKnife.bind(this, view);
        SupportMapFragment mapFragment = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map));
        mapFragment.getMapAsync(this);
        return view;
    }

    private void chequearPermiso() {
        //Obtenemos el estado del permiso de ubicacion
        int state = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);

        //Si lo tenemos, habilitamos el boton de ubicacion del usuario
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
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cenfotec, 12.0f));
        chequearPermiso();
    }


}
