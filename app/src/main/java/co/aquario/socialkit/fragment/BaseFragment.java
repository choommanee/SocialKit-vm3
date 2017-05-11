package co.aquario.socialkit.fragment;


import android.app.Fragment;

import co.aquario.socialkit.BusProvider;
import co.aquario.socialkit.handler.ApiBus;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onResume() {
        ApiBus.getInstance().register(this);
        BusProvider.getInstance().register(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        ApiBus.getInstance().unregister(this);
        BusProvider.getInstance().unregister(this);
        super.onPause();
    }




}



