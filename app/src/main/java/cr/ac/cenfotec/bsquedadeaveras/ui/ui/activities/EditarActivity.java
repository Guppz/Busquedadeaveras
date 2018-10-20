package cr.ac.cenfotec.bsquedadeaveras.ui.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.chip.Chip;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Averia;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Basic;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Image;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.ImageResponse;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Ubicacion;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Usuario;
import cr.ac.cenfotec.bsquedadeaveras.R;
import cr.ac.cenfotec.bsquedadeaveras.webservices.GestorImgur;
import cr.ac.cenfotec.bsquedadeaveras.webservices.GestorServicio;
import cr.ac.cenfotec.bsquedadeaveras.webservices.Imgur;
import cr.ac.cenfotec.bsquedadeaveras.webservices.ServicioPosts;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarActivity extends AppCompatActivity  implements OnMapReadyCallback , GoogleMap.OnMapLongClickListener{
    private GoogleMap gMap;
    private  LatLng mLatlng;

    MarkerOptions options;
    Marker marker;

    private static final int PERM_CODE = 1000;
    private static final int REQUEST_TAKE_PHOTO = 101;

    private Uri mUri;
    private File imageFile;
    Averia averiaG;

    @BindView(R.id.editar_Titulo)
    TextView titulo;
    @BindView(R.id.editar_tipo)
    TextView chip;
    @BindView(R.id.editar_av)
    TextView des;
    @BindView(R.id.id_averia_editar)
    TextView id;
    @BindView(R.id.editar_photo)
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_editar_averias);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String id = intent.getStringExtra("MyId");
        averiaG = (Averia) getIntent().getSerializableExtra("obj");
        mLatlng = new LatLng(averiaG.getUbicacion().getLatitude(),averiaG.getUbicacion().getLongitud());
        llenarDatos(averiaG);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_editar);
        mapFragment.getMapAsync(this);
    }


    @OnClick(R.id.editar_photo)
    public void imageclick(){
        verificarPermisos();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        LatLng larlng = new LatLng(9.9328022,-84.0317056);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatlng, 12.0f));
        options = new MarkerOptions().position(mLatlng);
        gMap.setOnMapLongClickListener(this);
        gMap.addMarker(options);
    }

    @OnClick(R.id.actualisar)
    public void actualisar(){
        String s1 = titulo.getText().toString();
        String s2 =chip.getText().toString();
        String s3 =des.getText().toString();
        String s4 =id.getText().toString();
        String s5;
        if (imageView.getContentDescription()==null){
            s5="";

        }else if (imageView.getContentDescription().equals("")){
            s5="";
        }else {
             s5 = imageView.getContentDescription().toString();
        }
        LatLng position = options.getPosition();
        Ubicacion ubicacion = new Ubicacion(position.latitude,position.longitude);
        Usuario usuario = MapActivity.usuario;
        if (usuario == null){
            usuario = MapActivity.usuarioP;
        }
        String fecha = getDate();
        Averia averia = new Averia(s4,s1,s2,s3,s5,ubicacion,usuario,fecha);
        ServicioPosts servicio = GestorServicio.obtenerServicio();
        servicio.updateAveria(s4,averia).enqueue(new Callback<Averia>(){
            @Override
            public void onResponse(Call<Averia> call, Response<Averia> response) {
                if(response.isSuccessful()){
                    Toast.makeText(EditarActivity.this, "Se a editado la averia ",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Averia> call, Throwable t) {
                Toast.makeText(EditarActivity.this,
                        "Error al interactuar con el servicio",
                        Toast.LENGTH_SHORT).show();
            }
        });
        Intent myIntent = new Intent(this, MapActivity.class);
        startActivity(myIntent);
    }


    @OnClick(R.id.btn_borrar)
    public void borrar(){

        new MaterialDialog.Builder(this)
                .title("Esta seguro")
                .content("Desea borrar esta averia?")
                .positiveText("Si")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        borrarTodo();
                    }
                })
                .negativeText("No")
                .show();
    }

    public void borrarTodo(){
        String s4 =id.getText().toString();
        ServicioPosts servicio = GestorServicio.obtenerServicio();
        servicio.deleteAveria(s4).enqueue(new Callback<Averia>() {
            @Override
            public void onResponse(Call<Averia> call, Response<Averia> response) {
                Averia resultado = response.body();
                Toast.makeText(EditarActivity.this, "El post fue borrado",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Averia> call, Throwable t) {
                Toast.makeText(EditarActivity.this,
                        "Error al interactuar con el servicio",
                        Toast.LENGTH_SHORT).show();
            }
        });
        Intent myIntent = new Intent(this, MapActivity.class);
        startActivity(myIntent);
    }

    public void llenarDatos(Averia a){
        titulo.setText(a.getNombre());
        chip.setText(a.getTipo());
        des.setText(a.getDescripcion());
        if (a.getImagen() == null){
            imageView.setImageResource(R.drawable.back);
        }else if(a.getImagen().equals("")){
            imageView.setImageResource(R.drawable.back);
        }else {
            Picasso.get().load(a.getImagen()).into(imageView);
            imageView.setContentDescription(a.getImagen());
        }
        id.setText(a.getId());
    }

    private void verificarPermisos() {
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED){
            continuarTomarFoto();
        } else {
            askForPermission();
        }
    }

    public void askForPermission(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERM_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED){
            verificarPermisos();
        } else {
            finish();
        }
    }

    private void continuarTomarFoto() {
        File archivo = crearArchivo();
        imageFile = archivo;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mUri = FileProvider.getUriForFile(this, "cr.ac.cenfotec.bsquedadeaveras",archivo);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
    }

    private File crearArchivo() {
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                    .format(new Date());
            String imageFileName = "JPEG_" + timeStamp;

            File storageDir =
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File image = File.createTempFile(
                    imageFileName, /* prefix */
                    ".jpg", /* suffix */
                    storageDir /* directory */
            );
            //...y lo retornamos
            return image;
        }catch(Exception e){
            Log.d("Prueba", e.getMessage());
            return null;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO &&
                resultCode == RESULT_OK) {
            Bitmap imageBitmap = null;
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(imageBitmap);
                File uploadFile = convertBitMap();
                GestorImgur.obtenerServicioAnon().postImage(RequestBody.create(MediaType.parse("image/jpg"),uploadFile)).enqueue(new Callback <Basic<Image>>() {
                @Override
                public void onResponse(Call<Basic<Image>> call, Response<Basic<Image>> response) {
                    Toast.makeText(EditarActivity.this, "Upload successful !", Toast.LENGTH_SHORT).show();
                }


                @Override
                public void onFailure(Call<Basic<Image>>  call, Throwable t) {
                    Toast.makeText(EditarActivity.this, "An unknown error has occured.", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                    Log.d("Errorr", t.toString());
                }
            });
        }
    }


    public File convertBitMap(){
        return new File(mUri.getPath());
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
