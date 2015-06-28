package perface.sundy.com.perface.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.androidquery.AQuery;

import perface.sundy.com.perface.R;
import perface.sundy.com.perface.adapter.PhotoGridAdapter;

/**
 * Created by sundy on 15/6/27.
 */
public class UploadPhotoFragment extends BaseFragment {

    private final String TAG = "UploadPhotoFragment";
    private View v;
    private Fragment fragment;
    private GridView gv_photos;
    private PhotoGridAdapter adapter;

    public UploadPhotoFragment() {
    }

    public UploadPhotoFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        v = inflater.inflate(R.layout.upload_photo, container, false);
        aq = new AQuery(v);

        init();
        return v;
    }

    private void init() {
        aq.id(R.id.txt_title).text(R.string.my_photo).visible();
        adapter = new PhotoGridAdapter(context, inflater);
        gv_photos = aq.id(R.id.gv_photos).getGridView();
        gv_photos.setAdapter(adapter);
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