package perface.sundy.com.perface.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.androidquery.AQuery;

import perface.sundy.com.perface.R;
import perface.sundy.com.perface.adapter.BookmarkGridAdapter;
import perface.sundy.com.perface.utils.custom_view.FixedGridLayout;

/**
 * Created by sundy on 15/6/23.
 */
public class MainFragment extends BaseFragment {

    private final String TAG = "MainFragment";
    private View v;
    private Fragment fragment;
    private LayoutInflater mInflater;
    private GridView gv_photos;
    private BookmarkGridAdapter adapter;

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
        aq.id(R.id.txt_more).clicked(onClickListener);
        adapter = new BookmarkGridAdapter(context, inflater);
        gv_photos = aq.id(R.id.gv_photos).getGridView();
        gv_photos.setAdapter(adapter);
        gv_photos.setOnItemClickListener(onItemClickListener);
        gv_photos.setOnItemLongClickListener(onItemLongClickListener);
    }

    android.widget.AdapterView.OnItemClickListener onItemClickListener = new android.widget.AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(android.widget.AdapterView<?> adapterView, View view, int i, long l) {
            rtLog(TAG, "------------>i = " + i);
            mCallback.addContent(new PhotoDetailFragment(MainFragment.this));
        }
    };

    AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
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
            return true;
        }
    };

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.txt_more:
                    rtLog(TAG, "----------->txt_more");
                    View dialog_view = inflater.inflate(R.layout.dialog_more_option, null);
                    final Dialog dialog = new Dialog(context, R.style.dialog);
                    dialog.setContentView(dialog_view);
                    AQuery aq1 = new AQuery(dialog_view);
                    FixedGridLayout fixedv_style = (FixedGridLayout) aq1.id(R.id.fixedv_style).getView();
                    if (fixedv_style.getChildCount() > 0) {
                        fixedv_style.removeAllViews();
                    }
                    DisplayMetrics dm = new DisplayMetrics();
                    context.getWindowManager().getDefaultDisplay().getMetrics(dm);
                    if (dm.heightPixels == 480 && dm.widthPixels == 800) {
                        fixedv_style.setCountpuls(4);
                    } else {
                        fixedv_style.setCountpuls(5);
                    }

                    if (dm.heightPixels == 1080 && dm.widthPixels == 1920) {
                        fixedv_style.setCellHeight(90);
                        fixedv_style.setmCelllong(350);
                    } else if (dm.heightPixels == 720 && dm.widthPixels == 1196 || dm.widthPixels == 1184) {
                        fixedv_style.setmCelllong(220);
                    }
                    for (int i = 0; i < 103; i++) {
                        TextView text = new TextView(context);
                        text.setText("style " + i);
                        text.setPadding(10, 10, 10, 10);
                        text.setBackgroundResource(R.color.white_bg);
                        text.setClickable(true);
                        text.setTextColor(Color.rgb(0, 0, 0));
                        fixedv_style.addView(text);

                        final int finalI = i;
                        text.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //To change body of implemented methods use File | Settings | File Template.
                                rtLog(TAG, "----------->URI finalI= " + finalI);

                            }
                        });
                    }
                    aq1.id(R.id.btn_confirm).clicked(new View.OnClickListener() {
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
