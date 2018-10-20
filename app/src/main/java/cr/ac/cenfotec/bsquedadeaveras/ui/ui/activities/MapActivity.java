package cr.ac.cenfotec.bsquedadeaveras.ui.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Usuario;
import cr.ac.cenfotec.bsquedadeaveras.manager.Adaptador.MapListAdapter;
import cr.ac.cenfotec.bsquedadeaveras.R;

public class MapActivity extends AppCompatActivity {

    @BindView(R.id.btn_agregar)
    FloatingActionButton fab;
    public static Usuario usuario;
    public static Usuario usuarioP;
    public static int METODO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewMap);
        MapListAdapter adapter = new MapListAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        Intent i = getIntent();
        usuario = (Usuario)i.getSerializableExtra("MyClass");
        if (usuario != null){
            usuarioP = usuario;
        }
        //Usuario tempUser = new Usuario("test@test.com","test","888888","1111111","123");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @OnClick(R.id.btn_agregar)
    public void agregarAveria(){
        Intent myIntent = new Intent(this, CrearActivity.class);
        startActivity(myIntent);
    }
}
