package perface.sundy.com.perface.activity;

import android.content.Intent;
import android.os.Bundle;

import perface.sundy.com.perface.MyApp;
import perface.sundy.com.perface.R;

public class LoadingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        MyApp.addActivity(this);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
