package cr.ac.cenfotec.bsquedadeaveras.manager.Adaptador;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cr.ac.cenfotec.bsquedadeaveras.ui.ui.Fragments.LoginFragment;
import cr.ac.cenfotec.bsquedadeaveras.ui.ui.Fragments.SingInFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    LoginFragment lF;
    SingInFragment sF;


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        lF = new LoginFragment();
        sF=new SingInFragment();
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return lF;
        } else if (i == 1) {
            return sF;
        }
        return null;
    }

    @Override
    public int getCount(){return 2;}

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) {
            return "Login";
        }else{
            return "Sing in";
        }
    }

}
