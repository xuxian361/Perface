package perface.sundy.com.perface.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.androidquery.AQuery;

import perface.sundy.com.perface.R;

/**
 * Created by sundy on 15/6/24.
 */
public class UserInfo extends BaseFragment implements View.OnClickListener {

    private final String TAG = "UserInfo";
    private View v;
    private Fragment fragment;
    private LinearLayout linear_password;

    public UserInfo() {
    }

    public UserInfo(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        v = inflater.inflate(R.layout.userinfo, container, false);
        aq = new AQuery(v);

        init();
        return v;
    }

    private void init() {
        aq.id(R.id.txt_title).text(R.string.my_info).visible();
        linear_password = (LinearLayout) aq.id(R.id.linear_password).getView();
        aq.id(R.id.img_arrow).clicked(this);
        aq.id(R.id.btn_confirm).clicked(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.img_arrow:
                if (linear_password.getVisibility() == View.VISIBLE) {
                    linear_password.setVisibility(View.GONE);
                    aq.id(R.id.btn_confirm).gone();
                    aq.id(R.id.img_arrow).image(R.mipmap.arrow_down_black2);
                } else {
                    linear_password.setVisibility(View.VISIBLE);
                    aq.id(R.id.btn_confirm).visible();
                    aq.id(R.id.img_arrow).image(R.mipmap.arrow_up_black2);
                }
                break;
            case R.id.btn_confirm:
                linear_password.setVisibility(View.GONE);
                aq.id(R.id.btn_confirm).gone();
                aq.id(R.id.img_arrow).image(R.mipmap.arrow_down_black2);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        rtLog(TAG, "------------>onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        rtLog(TAG, "------------>onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        rtLog(TAG, "------------>onPause");
    }
}
