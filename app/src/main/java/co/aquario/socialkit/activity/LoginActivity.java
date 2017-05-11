package co.aquario.socialkit.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import co.aquario.socialkit.MainApplication;
import co.aquario.socialkit.R;
import co.aquario.socialkit.event.UpdateProfileEvent;
import co.aquario.socialkit.fragment.LoginFragment;
import co.aquario.socialkit.handler.ApiBus;
import co.aquario.socialkit.model.UserProfile;
import co.aquario.socialkit.util.PrefManager;

public class LoginActivity extends Activity {

    public PrefManager prefManager;
    public boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        prefManager = MainApplication.get(this).getPrefManager();
        setContentView(R.layout.activity_login);

        isLogin = prefManager.isLogin().getOr(false);


        //prefManager.isLogin().put(false).commit();
        Log.e("isLogin","::"+isLogin);

        if (savedInstanceState == null && !isLogin) {
            getFragmentManager().beginTransaction().add(R.id.login_container, new LoginFragment(),"LOGIN").commit();
        } else {
            Intent main = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(main);
            ApiBus.getInstance().post(new UpdateProfileEvent(new UserProfile()));
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_login, menu);
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

}
