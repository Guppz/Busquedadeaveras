package cr.ac.cenfotec.bsquedadeaveras.ui.ui.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.gson.Gson;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cr.ac.cenfotec.bsquedadeaveras.DB.DatabaseHelper;
import cr.ac.cenfotec.bsquedadeaveras.R;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Usuario;

import static android.content.Context.MODE_PRIVATE;

public class SingInFragment extends Fragment {

    @BindView(R.id.textUsuario)
    EditText user;
    @BindView(R.id.textpass)
    EditText pass;
    @BindView(R.id.nombreText)
    EditText nombre;
    @BindView(R.id.telText)
    EditText telefono;
    @BindView(R.id.cedulaText)
    EditText cedula;

    @BindView(R.id.btn_registro)
    MaterialButton btm;
    DatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_signin, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_registro)
    public void submit(View view) {
        boolean espacioEnBlancoFalg = false;
        boolean mailValidaror = false;
        String username = user.getText().toString();
        String password = pass.getText().toString();
        String nombreUser = nombre.getText().toString();
        String tel = telefono.getText().toString();
        String cedulaUser = cedula.getText().toString();
        espacioEnBlancoFalg = validatesEspaciosEnBlanco();
        mailValidaror= validatesCorreo();
        boolean mailExite = validatesCorreoExite();


        if (espacioEnBlancoFalg == false && mailValidaror == false && mailExite==false){
            Usuario usuario = new Usuario(username,nombreUser,tel,cedulaUser,password);
            dbHelper=(DatabaseHelper) OpenHelperManager.getHelper(getContext(),DatabaseHelper.class);
            RuntimeExceptionDao<Usuario,Integer> contactDao = dbHelper.getRuntimeDao();
            contactDao.create(usuario);
            dbHelper.close();
        }
    }

    public  boolean validatesEspaciosEnBlanco(){
        boolean errorFlag = false;
        String username = user.getText().toString();
        String password = pass.getText().toString();
        String nombreUser = nombre.getText().toString();
        String tel = telefono.getText().toString();
        String cedulaUser = cedula.getText().toString();

        if(username.equals("")){
            errorFlag=true;
            user.setError("No dejar correo en blanco");
        }
        if(password.equals("")){
            errorFlag=true;
            pass.setError("No dejar contraseña en blanco");
        }

        if(nombreUser.equals("")){
            errorFlag=true;
            nombre.setError("No dejar nombre en blanco");
        }

        if(tel.equals("")){
            errorFlag=true;
            telefono.setError("No dejar telefono en blanco");
        }

        if(cedulaUser.equals("")){
            errorFlag=true;
            cedula.setError("No dejar cedula en blanco");
        }
        return errorFlag;
    }

    public  boolean validatesCorreo(){
        boolean errorFlag = false;
        String regex = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
        String username = user.getText().toString();


        if(!username.matches(regex)){
            errorFlag=true;
            user.setError("Correo no valido");
        }
        return errorFlag;
    }

    public  boolean validatesCorreoExite(){
        boolean errorFlag = false;
        String username = user.getText().toString();
        dbHelper=(DatabaseHelper) OpenHelperManager.getHelper(getContext(),DatabaseHelper.class);
        RuntimeExceptionDao<Usuario,Integer> Dao = dbHelper.getRuntimeDao();
        List<Usuario> usuarios = Dao.queryForAll();
        for (Usuario u: usuarios) {
            if(username.equals(u.correo)){
                errorFlag=true;
                user.setError("Correo ya exite");
                return  errorFlag;
            }

        }
        return errorFlag;
    }
}
