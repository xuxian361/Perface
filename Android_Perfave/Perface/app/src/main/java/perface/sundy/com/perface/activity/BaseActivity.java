package perface.sundy.com.perface.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.WindowManager;

import com.androidquery.AQuery;

import perface.sundy.com.perface.utils.MyConstant;
import perface.sundy.com.perface.utils.MyUtils;

/**
 * Created by sundy on 15/6/21.
 */
public class BaseActivity extends FragmentActivity {

    protected Context context;
    protected AQuery aq;

    public BaseActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        aq = new AQuery(this);
    }

    protected void hideSoftInputView() {
        MyUtils.hideSoftInputView(this);
    }

    protected void setSoftInputMode() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void rtLog(String tag, String msg) {
        if (MyConstant.IsDebug) {
            Log.e(tag, msg);
        }
    }

}
