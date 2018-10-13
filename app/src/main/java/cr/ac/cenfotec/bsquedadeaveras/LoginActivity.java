package cr.ac.cenfotec.bsquedadeaveras;

import android.app.Dialog;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewParent;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import cr.ac.cenfotec.bsquedadeaveras.Adaptador.ViewPagerAdapter;
import cr.ac.cenfotec.bsquedadeaveras.Fragments.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    private static final int ERROR_DIALOG_REQUEST = 9001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DotsIndicator dotsIndicator = (DotsIndicator) findViewById(R.id.dots_indicator);
        ViewPager  viewPager = (ViewPager) findViewById(R.id.view);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        dotsIndicator.setViewPager(viewPager);
    }
}
