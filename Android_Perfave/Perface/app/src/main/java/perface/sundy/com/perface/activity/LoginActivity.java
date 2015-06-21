package perface.sundy.com.perface.activity;

import android.os.Bundle;

import perface.sundy.com.perface.MyApp;
import perface.sundy.com.perface.R;

/**
 * Created by sundy on 15/6/21.
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        MyApp.addActivity(this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
