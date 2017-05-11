package co.aquario.socialkit.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.nispok.snackbar.Snackbar;

import co.aquario.socialkit.MainApplication;
import co.aquario.socialkit.R;
import co.aquario.socialkit.fragment.BaseFragment;
import co.aquario.socialkit.fragment.MainFragment;
import co.aquario.socialkit.handler.ApiBus;


public class MainActivity extends ActionBarActivity {

    private Drawer.Result result = null;
    private Context context;
    private Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        activity = this;
        //new Drawer().withActivity(this).build();


        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        result = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHeader(R.layout.header)
                .withActionBarDrawerToggle(true)
                .withTranslucentStatusBar(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Channels").withIcon(FontAwesome.Icon.faw_terminal),
                        new PrimaryDrawerItem().withName("Social").withIcon(FontAwesome.Icon.faw_users),
                        new PrimaryDrawerItem().withName("Videos").withIcon(FontAwesome.Icon.faw_video_camera),
                        new PrimaryDrawerItem().withName("Photos").withIcon(FontAwesome.Icon.faw_camera_retro),
                        new SectionDrawerItem().withName("Menu"),
                        new SecondaryDrawerItem().withName("Home").withIcon(FontAwesome.Icon.faw_home),
                        new SecondaryDrawerItem().withName("Live History").withIcon(FontAwesome.Icon.faw_history),
                        new SecondaryDrawerItem().withName("Setting").withIcon(FontAwesome.Icon.faw_cog),
                        new SecondaryDrawerItem().withName("Maxpoint").withIcon(FontAwesome.Icon.faw_btc),
                        new SecondaryDrawerItem().withName("Tattoo Store").withIcon(FontAwesome.Icon.faw_shopping_cart).setEnabled(false),
                        new SecondaryDrawerItem().withName("Term & Policies").withIcon(FontAwesome.Icon.faw_terminal),
                        new SecondaryDrawerItem().withName("Log Out").withIcon(FontAwesome.Icon.faw_sign_out)

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        if (drawerItem instanceof Nameable) {
                            Snackbar.with(getApplicationContext()).text(((Nameable) drawerItem).getName()).show(activity);
                        }
                        if(((Nameable) drawerItem).getName().equals("Log Out")) {
                            MainApplication.logout();
                            Intent login = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(login);
                            finish();
                        }
                        MainApplication.logout();
                        Intent login = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(login);
                        finish();
                    }
                }).build();


        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
            Snackbar.with(this).text("finish login").show(this);



            //ApiBus.getInstance().post(new SomeEvent("var1",
              //      2));
        }
    }

    @Override public void onResume() {
        super.onResume();
        ApiBus.getInstance().register(this);
    }

    @Override public void onPause() {
        super.onPause();
        ApiBus.getInstance().unregister(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends BaseFragment {

        //@InjectView(R.id.imageView)

        //public ImageView randomView;
        //public TextView author;

        public PlaceholderFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            /*
            randomView = (ImageView) rootView.findViewById(R.id.imageView);
            author = (TextView) rootView.findViewById(R.id.textView);

            randomView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long unixTime = System.currentTimeMillis() / 1000L;
                    int randomTime = (int) unixTime;
                    ApiBus.getInstance().post(new SomeEvent("var1",
                            randomTime));
                }
            });
            */

            //ButterKnife.inject(getActivity(),rootView);
            return rootView;
        }


        /*

        @Subscribe
        public void onSomeSuccess(LoginSuccessEvent event) {
            //SomeData imageData = event.getSomeResponse();
            //Log.e("HEY3!",imageData.src);
            //Picasso.with(getActivity()).load(imageData.src+"?fit=crop&fm=jpg&h=480&q=80&w=640").fit().centerInside().into(randomView);
            //author.setText(imageData.author);

            //ActionBarActivity actionBarActivity = (ActionBarActivity)getActivity();
            //ActionBar actionBar = actionBarActivity.getSupportActionBar();

            //actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(imageData.color)));

        }

        @Subscribe
        public void onSomeFailed(LoginFailedEvent event) {
            Snackbar.with(getActivity()).text("Failed to load images").show(getActivity());
        }

        */
    }
}
