package perface.sundy.com.perface.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;

import perface.sundy.com.perface.R;

/**
 * Created by sundy on 15/6/28.
 */
public class EditPhotoFragment extends BaseFragment {

    private final String TAG = "EditPhotoFragment";
    private View v;
    private Fragment fragment;
    private String url;

    public EditPhotoFragment() {
    }

    public EditPhotoFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public EditPhotoFragment(String url) {
        this.url = url;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        v = inflater.inflate(R.layout.edit_photo, container, false);
        aq = new AQuery(v);

        init();
        return v;
    }

    private void init() {
        aq.id(R.id.txt_title).text("女王装").visible();

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

