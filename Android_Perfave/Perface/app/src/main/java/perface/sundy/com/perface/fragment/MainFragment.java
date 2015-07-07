package perface.sundy.com.perface.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.androidquery.AQuery;

import jp.wasabeef.recyclerview.animators.adapters.AlphaInAnimationAdapter;
import perface.sundy.com.perface.R;
import perface.sundy.com.perface.adapter.MainGridViewAdapter;

/**
 * Created by sundy on 15/6/23.
 */
public class MainFragment extends BaseFragment {

    private final String TAG = "MainFragment";
    private View v;
    private Fragment fragment;
    private LayoutInflater mInflater;
    private RecyclerView gv_photos;
    private MainGridViewAdapter adapter;

    public MainFragment() {
    }

    public MainFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        v = inflater.inflate(R.layout.main, container, false);
        aq = new AQuery(v);

        init();
        return v;
    }

    private void init() {
        aq.id(R.id.txt_title).text(R.string.home).visible();
        adapter = new MainGridViewAdapter(context, mCallback, null);
        gv_photos = (RecyclerView) aq.id(R.id.gv_photos).getView();
        gv_photos.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapter);
        alphaAdapter.setDuration(1000);
        alphaAdapter.setFirstOnly(false);
        gv_photos.setAdapter(alphaAdapter);

        adapter.setOnItemClickListener(new MainGridViewAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                rtLog(TAG, "------->i = " + i);
                mCallback.addContent(new PhotoDetailFragment(MainFragment.this));
            }
        });
        adapter.setOnItemLongClickListener(new MainGridViewAdapter.onItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int i) {
                rtLog(TAG, "------------>i = " + i);
                View dialog_view = inflater.inflate(R.layout.dialog_delete_myphoto, null);
                final Dialog dialog = new Dialog(context, R.style.dialog);
                dialog.setContentView(dialog_view);
                AQuery aq1 = new AQuery(dialog_view);
                aq1.id(R.id.btn_delete).clicked(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        rtLog(TAG, "----------->URI btn_delete= ");
                        dialog.cancel();
                        dialog.dismiss();

                    }
                });
                aq1.id(R.id.btn_update_name).clicked(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        rtLog(TAG, "----------->URI btn_update_name= ");
                        dialog.cancel();
                        dialog.dismiss();

                    }
                });
                aq1.id(R.id.btn_cancel).clicked(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            return true;
        }
    };

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {

            }
        }
    };

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
