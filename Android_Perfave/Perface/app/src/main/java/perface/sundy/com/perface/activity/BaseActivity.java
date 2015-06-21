package perface.sundy.com.perface.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;

import perface.sundy.com.perface.utils.MyUtils;

/**
 * Created by sundy on 15/6/21.
 */
public class BaseActivity extends FragmentActivity {


    protected Context context;

    public BaseActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }

    protected void hideSoftInputView() {
        MyUtils.hideSoftInputView(this);
    }

    protected void setSoftInputMode() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}
