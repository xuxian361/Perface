package perface.sundy.com.perface.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androidquery.AQuery;

import perface.sundy.com.perface.R;
import perface.sundy.com.perface.utils.ActivityController;

/**
 * Created by sundy on 15/6/21.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ActivityController.addActivity(this);
        aq = new AQuery(this);

        aq.id(R.id.btnRegister).clicked(this);
        aq.id(R.id.btn_login).clicked(this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                goRegister();
                break;
            case R.id.btn_login:
                goMain();
                break;
        }
    }

    private void goMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void goRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
