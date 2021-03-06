package perface.sundy.com.perface.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;

import perface.sundy.com.perface.R;
import perface.sundy.com.perface.utils.MyConstant;

/**
 * Created by sundy on 15/6/22.
 */
public class BaseFragment extends Fragment implements View.OnClickListener {

    private final String TAG = "BaseFragment";
    protected OnListListener mCallback;
    protected FragmentActivity context;
    protected LayoutInflater inflater;
    protected AQuery aq;

    public BaseFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnListListener) (context = (FragmentActivity) activity);
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnHeadlineSelectedListener");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aq = new AQuery(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
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

    @Override
    public void onDetach() {
        super.onDetach();
    }

    // Container Activity must implement this interface
    public interface OnListListener {
        public void onLoading();

        public void finishLoading();

        public void switchContent(android.support.v4.app.Fragment fragment);

        public void addContent(android.support.v4.app.Fragment fragment);

        public void showSlidingMenu();

        public void onBack();

        public void reloadActivity();

//        public void popBackStack();

        public void switchContent(int rid);

    }

    @Override
    public void onClick(View view) {
        if (mCallback == null)
            return;
        switch (view.getId()) {
            case R.id.btnMenu:
                Log.i(TAG, "---------->btnMenu");
                mCallback.showSlidingMenu();
                break;
            case R.id.btnBack:
                Log.i(TAG, "---------->btnBack");
                mCallback.onBack();
                break;
        }
    }

    public void rtLog(String tag, String msg) {
        if (MyConstant.IsDebug) {
            Log.e(tag, msg);
        }
    }
}

