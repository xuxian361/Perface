package perface.sundy.com.perface.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
        aq.id(R.id.btn_confrim_upload).clicked(onClickListener);

        adapter = new PhotoGridAdapter(context, inflater);
        gv_photos = aq.id(R.id.gv_photos).getGridView();
        gv_photos.setAdapter(adapter);
        gv_photos.setOnItemClickListener(onItemClickListener);
        gv_photos.setOnItemLongClickListener(onItemLongClickListener);
    }

    android.widget.AdapterView.OnItemClickListener onItemClickListener = new android.widget.AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(android.widget.AdapterView<?> adapterView, View view, int i, long l) {
            rtLog(TAG, "------------>i = " + i);
            mCallback.addContent(new EditPhotoFragment());
        }
    };

    AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            rtLog(TAG, "------------>i = " + i);
            View dialog_view = inflater.inflate(R.layout.dialog_delete, null);
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
            aq1.id(R.id.btn_edit).clicked(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rtLog(TAG, "----------->URI btn_edit= ");
                    dialog.cancel();
                    dialog.dismiss();
                    mCallback.addContent(new EditPhotoFragment());
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
            return true;
        }
    };

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_confrim_upload:
                    rtLog(TAG, "------------>btn_confrim_upload");
                    View dialog_view = inflater.inflate(R.layout.dialog_confirm_upload, null);
                    final Dialog dialog = new Dialog(context, R.style.dialog);
                    dialog.setContentView(dialog_view);
                    AQuery aq1 = new AQuery(dialog_view);
                    aq1.id(R.id.btn_confirm).clicked(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
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
                    break;
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