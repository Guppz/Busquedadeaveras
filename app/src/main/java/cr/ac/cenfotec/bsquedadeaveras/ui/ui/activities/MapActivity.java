package cr.ac.cenfotec.bsquedadeaveras.ui.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;

import butterknife.ButterKnife;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Usuario;
import cr.ac.cenfotec.bsquedadeaveras.manager.Adaptador.MapListAdapter;
import cr.ac.cenfotec.bsquedadeaveras.R;

public class MapActivity extends AppCompatActivity {

    public static Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewMap);
        MapListAdapter adapter = new MapListAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        //Intent i = getIntent();
        //usuario = (Usuario)i.getSerializableExtra("MyClass");
        Usuario tempUser = new Usuario("test@test.com","test","888888","1111111","123");
        usuario = tempUser;
    }
}
