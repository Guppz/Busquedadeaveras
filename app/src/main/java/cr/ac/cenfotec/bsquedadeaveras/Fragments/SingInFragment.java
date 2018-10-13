package cr.ac.cenfotec.bsquedadeaveras.Fragments;

import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import cr.ac.cenfotec.bsquedadeaveras.R;

public class SingInFragment extends Fragment {

    @BindView(R.id.textUsuario)
    EditText user;
    @BindView(R.id.textpass)
    EditText pass;
    @BindView(R.id.btn)
    MaterialButton btm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_signin, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
