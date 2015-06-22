package perface.sundy.com.perface.activity;

import android.os.Bundle;

import perface.sundy.com.perface.R;
import perface.sundy.com.perface.utils.ActivityController;

/**
 * Created by sundy on 15/6/21.
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ActivityController.addActivity(this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }

}
