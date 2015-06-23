package perface.sundy.com.perface.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;

import perface.sundy.com.perface.R;

/**
 * Created by sundy on 15/6/23.
 */
public class MenuFragment extends Fragment implements View.OnClickListener {

    private final String TAG = "MenuFragment";
    private AQuery aq;
    private LayoutInflater layoutinflater;
    private View v;
    private OnListListener mCallback;
    public OnListListener MenuCallBack;
    protected FragmentActivity context;
    public int curRadioId = R.id.btn_home;
    private BaseFragment home, my_photo, my_bookmark;

    public MenuFragment() {
    }

    public MenuFragment(int radioId) {
        if (radioId > 0)
            curRadioId = radioId;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnListListener) (context = (FragmentActivity) activity);
            MenuCallBack = mCallback;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.layoutinflater = inflater;
        v = layoutinflater.inflate(R.layout.menu, container, false);
        aq = new AQuery(v);

        init();

        return v;
    }

    private void init() {
        try {
            if (mCallback == null)
                return;
            mCallback.switchContent(getFragmentItem(curRadioId));

            //replace fragment
            aq.id(R.id.btn_home).clicked(onClickListener);
            aq.id(R.id.btn_my_photo).clicked(onClickListener);
            aq.id(R.id.btn_my_bookmark).clicked(onClickListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final int id = v.getId();
            Fragment fragment = getFragmentItem(id);
            if (fragment == null)
                return;
            if (mCallback != null)
                mCallback.switchContent(fragment);
        }
    };

    private Fragment getFragmentItem(final int index) {
        Fragment fragment = null;
        switch (index) {
            case R.id.btn_home:
                if (home == null)
                    home = new MainFragment(MenuFragment.this);
                fragment = home;
                curRadioId = index;
                break;
            case R.id.btn_my_photo:
                if (my_photo == null)
                    my_photo = new MyPhotoFragment(MenuFragment.this);
                fragment = my_photo;
                curRadioId = index;
                break;
            case R.id.btn_my_bookmark:
                if (my_bookmark == null)
                    my_bookmark = new MyBookmarkFragment(MenuFragment.this);
                fragment = my_bookmark;
                curRadioId = index;
                break;
        }
        return fragment;
    }

    @Override
    public void onClick(View view) {
        if (mCallback == null)
            return;
        int id = view.getId();
        switch (id) {
            case R.id.btn_home:
                if (curRadioId == id) {
                    mCallback.showSlidingMenu();
                }
                break;
            case R.id.btnMenu:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
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
    }

    // Container Activity must implement this interface
    public interface OnListListener {
        public void onLoading();

        public void finishLoading();

        public void switchContent(Fragment fragment);

        public void addContent(Fragment fragment);

        public void showSlidingMenu();
    }

}
