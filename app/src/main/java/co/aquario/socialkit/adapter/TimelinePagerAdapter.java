package co.aquario.socialkit.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Locale;

public class TimelinePagerAdapter extends FragmentPagerAdapter {

    public TimelinePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return NowFragment.newInstance(position + 1);
            case 1:
                return PastFragment.newInstance(position + 1);
            case 2:
                return AlltimeFragment.newInstance(position + 1);
            default:
                return NowFragment.newInstance(position + 1);
        }

    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return "Now".toUpperCase(l);
            case 1:
                return "Past".toUpperCase(l);
            case 2:
                return "All-time".toUpperCase(l);
        }
        return null;
    }
}
