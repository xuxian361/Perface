package perface.sundy.com.perface.activity;

import android.os.Bundle;
import android.view.View;

import com.androidquery.AQuery;

import perface.sundy.com.perface.R;
import perface.sundy.com.perface.utils.ActivityController;

/**
 * Created by sundy on 15/6/22.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        ActivityController.addActivity(this);
        aq = new AQuery(this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

}
