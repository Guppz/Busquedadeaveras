package cr.ac.cenfotec.bsquedadeaveras.ui.ui.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import cr.ac.cenfotec.bsquedadeaveras.manager.Adaptador.ViewPagerAdapter;
import cr.ac.cenfotec.bsquedadeaveras.R;

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
