package co.aquario.socialkit.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import co.aquario.socialkit.MainApplication;
import co.aquario.socialkit.R;
import co.aquario.socialkit.activity.LoginActivity;
import co.aquario.socialkit.event.UpdateProfileEvent;
import co.aquario.socialkit.util.EndpointManager;
import co.aquario.socialkit.util.PrefManager;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainFragment extends BaseFragment {

    //@InjectView(R.id.profile_image)
    public CircleImageView avatar;

    //@InjectView(R.id.profile_name)
    public TextView name;

    //@InjectView(R.id.btn_logout)
    public LinearLayout btnLogout;

    public MainFragment() {

    }

    private PrefManager pref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = MainApplication.get(getActivity().getApplicationContext()).getPrefManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        //ButterKnife.inject(getActivity(),rootView);

        avatar = (CircleImageView) rootView.findViewById(R.id.profile_image);
        name = (TextView) rootView.findViewById(R.id.profile_name);

        btnLogout = (LinearLayout) rootView.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("HEYHA!","Logout Clicked");
                logout();
            }
        });

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment mFragment = null;
                FragmentManager mFragmentManager = getActivity().getFragmentManager();
                mFragment = new FeedFragment().newInstance("haha");


            if (mFragment != null){
                mFragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
            }
                /*
                Intent video = new Intent(getActivity(), VideoViewDemo.class);
                getActivity().startActivity(video);
                */
            }
        });

        updateProfileView();

        return rootView;
    }

    //@OnClick(R.id.btn_logout)
    public void logout() {
        MainApplication.logout();
        Intent login = new Intent(getActivity(), LoginActivity.class);
        startActivity(login);
        getActivity().finish();

    }

    @Subscribe public void onUpdateProfile(UpdateProfileEvent event) {

        updateProfileView();
        Log.d("HEYHA!","Now i'm at profile page");

    }

    private void updateProfileView() {
        String avatarPath = EndpointManager.getPath(pref.avatar().getOr(""));
        String username = pref.username().getOr("");

        Log.e("avatarPath",avatarPath);

        if(!avatarPath.equals(""))
            Picasso.with(getActivity().getApplicationContext()).load(avatarPath).into(avatar);
        else
            Log.e("HEYHA!","Can't get avatar path");

        name.setText(username);
    }

}
