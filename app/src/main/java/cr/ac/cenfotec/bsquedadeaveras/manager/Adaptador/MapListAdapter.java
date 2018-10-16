package cr.ac.cenfotec.bsquedadeaveras.manager.Adaptador;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cr.ac.cenfotec.bsquedadeaveras.ui.ui.Fragments.ListFragment;
import cr.ac.cenfotec.bsquedadeaveras.ui.ui.Fragments.MapFragment;

public class MapListAdapter extends FragmentPagerAdapter {

    MapFragment mf;
    ListFragment lf;

    public MapListAdapter(FragmentManager fm) {
        super(fm);
        mf= new MapFragment();
        lf = new ListFragment();
}

    @Override
    public Fragment getItem(int i) {
        if (i == 0){
            return mf;
        }else {
            return lf;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return "Map";
        }else {
            return "List";
        }
    }

}
