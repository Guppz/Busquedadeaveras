package cr.ac.cenfotec.bsquedadeaveras.ui.ui.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cr.ac.cenfotec.bsquedadeaveras.DB.DatabaseHelper;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Usuario;
import cr.ac.cenfotec.bsquedadeaveras.R;
import cr.ac.cenfotec.bsquedadeaveras.ui.ui.activities.MapActivity;

public class LoginFragment extends Fragment {

    @BindView(R.id.textUsuario)
    EditText user;
    @BindView(R.id.textpass)
    EditText pass;
    @BindView(R.id.btn)
    MaterialButton btm;
    DatabaseHelper dbHelper;
    private static  Usuario loginUser = new Usuario();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn)
    public  void login(){
        boolean b = validatesCorreoExite();
        if (b == true){
            dbHelper.close();
            Intent myIntent = new Intent(getContext(), MapActivity.class);
            myIntent.putExtra("MyClass", loginUser);
            startActivity(myIntent);
        }

    }


    public  boolean validatesCorreoExite(){
        boolean flag = false;
        String username = user.getText().toString();
        String password = pass.getText().toString();
        dbHelper=(DatabaseHelper) OpenHelperManager.getHelper(getContext(),DatabaseHelper.class);
        RuntimeExceptionDao<Usuario,Integer> Dao = dbHelper.getRuntimeDao();
        List<Usuario> usuarios = Dao.queryForAll();
        for (Usuario u: usuarios) {
            if(username.equals(u.correo)){
                if (password.equals(u.password)){
                    flag=true;
                    loginUser = u;
                    return  flag;
                }

            }
        }

        if (flag == false){
            Toast.makeText(getContext(),"Correo o constraseña incorrecta",Toast.LENGTH_LONG).show();
        }
        return flag;
    }



}
