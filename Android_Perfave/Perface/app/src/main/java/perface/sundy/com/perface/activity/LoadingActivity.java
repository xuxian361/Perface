package perface.sundy.com.perface.activity;

import android.content.Intent;
import android.os.Bundle;

import perface.sundy.com.perface.R;
import perface.sundy.com.perface.utils.ActivityController;

public class LoadingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        ActivityController.addActivity(this);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }
}
