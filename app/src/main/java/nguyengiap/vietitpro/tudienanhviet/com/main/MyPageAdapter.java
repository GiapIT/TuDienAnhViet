package nguyengiap.vietitpro.tudienanhviet.com.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by GiapNN on 6/15/2016.
 */
public class MyPageAdapter  extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    public MyPageAdapter(FragmentManager fm, List<Fragment> fragment) {
        super(fm);
        this.fragments=fragment;
    }
    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);

    }

    @Override
    public int getCount() {
        return this.fragments.size();

    }
}
