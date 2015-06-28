package perface.sundy.com.perface.fragment;

import android.app.Dialog;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.androidquery.AQuery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import perface.sundy.com.perface.MyApp;
import perface.sundy.com.perface.R;
import perface.sundy.com.perface.utils.MyConstant;
import perface.sundy.com.perface.utils.Rotate3dAnimation;
import perface.sundy.com.perface.utils.custom_view.LazyScrollView;
import perface.sundy.com.perface.vo.DuitangInfo;

/**
 * Created by sundy on 15/6/27.
 */
public class PhotoDetailFragment extends BaseFragment implements LazyScrollView.OnScrollListener {

    private final String TAG = "PhotoDetailFragment";
    private String url;
    private View v;
    private Fragment fragment;
    private LayoutInflater mInflater;
    private RelativeLayout rootView;
    private LazyScrollView rootScroll;

    private static final int COLUMNCOUNT = 3;
    private int columnWidth = 250;// 每个item的宽度
    private int itemHeight = 0;
    private int rowCountPerScreen = 3;
    private int cols = 4;// 当前总列数
    private ArrayList<Integer> colYs = new ArrayList<Integer>();
    private List<DuitangInfo> infos = new ArrayList<DuitangInfo>();
    List<Point> lostPoint = new ArrayList<Point>();// 用于记录空白块的位置

    public PhotoDetailFragment() {
    }

    public PhotoDetailFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public PhotoDetailFragment(String url) {
        this.url = url;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        v = inflater.inflate(R.layout.photp_detail, container, false);
        aq = new AQuery(v);

        init();
        return v;
    }

    private void init() {
        aq.id(R.id.txt_title).text("女王装").visible();
        rootView = (RelativeLayout) aq.id(R.id.rootView).getView();
        rootView.setPersistentDrawingCache(ViewGroup.PERSISTENT_ANIMATION_CACHE);
        rootScroll = (LazyScrollView) aq.id(R.id.rootScroll).getView();
        rootScroll.setOnScrollListener(this);
        rootScroll.getView();

        Display display = context.getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        Configuration cf = this.getResources().getConfiguration();

        if (cf.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rowCountPerScreen = 3;
        } else {
            rowCountPerScreen = 6;
        }

        columnWidth = width / COLUMNCOUNT;
        itemHeight = height / rowCountPerScreen;

        for (int i = 0; i < 4; i++) {
            colYs.add(0);
        }

        getMyBookMarks();
    }

    private void getMyBookMarks() {
        String tag_json_obj = "json_mybookmark";
        String url = MyConstant.HTTP_BASE + "/album/1733789/masn/p/1/24/";
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("sundy", "------->result = " + object.toString());
                        try {
                            infos = parseNewsJSON(object);
                            if (infos != null) {
                                Random r = new Random();
                                for (int i = 0; i < infos.size(); i++) {
                                    View v = inflater.inflate(R.layout.image_item, null);
                                    int nextInt = r.nextInt(50);
                                    // 模拟分为三种情况
                                    if (nextInt > 40) {
                                        // 跨两列两行
                                        android.widget.RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                                                columnWidth * 2, itemHeight * 2);
                                        v.setLayoutParams(params);
                                    } else if (nextInt > 30) {
                                        // 跨一列两行
                                        android.widget.RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                                                columnWidth, itemHeight * 2);

                                        v.setLayoutParams(params);
                                    } else if (nextInt > 25) {
                                        // 跨两列一行
                                        android.widget.RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                                                columnWidth * 2, itemHeight);

                                        v.setLayoutParams(params);
                                    } else {
                                        android.widget.RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                                                columnWidth, itemHeight);

                                        v.setLayoutParams(params);
                                    }
                                    addView(v, infos.get(i));

                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

        MyApp.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private List<DuitangInfo> parseNewsJSON(JSONObject newsObject) throws IOException {
        List<DuitangInfo> duitangs = new ArrayList<DuitangInfo>();
        try {
            if (null != newsObject) {
                JSONObject data = newsObject.getJSONObject("data");
                JSONArray blogs = data.getJSONArray("blogs");

                for (int i = 0; i < blogs.length(); i++) {
                    JSONObject item = blogs
                            .getJSONObject(i);
                    DuitangInfo info = new DuitangInfo();
                    info.setAlbid(item.isNull("albid") ? "" : item.getString("albid"));
                    info.setIsrc(item.isNull("isrc") ? "" : item.getString("isrc"));
                    info.setMsg(item.isNull("msg") ? "" : item.getString("msg"));
                    duitangs.add(info);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return duitangs;
    }

    private synchronized void addView(View view, DuitangInfo info) {
        try {
            final String uri = info.getIsrc();
            placeBrick(view);
            ImageView picView = (ImageView) view.findViewById(R.id.imageView);
            picView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rtLog(TAG, "----------->URI = " + uri);
                    mCallback.addContent(new PhotoScaleDetailFragment(uri));
                }
            });
            picView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    View dialog_view = inflater.inflate(R.layout.dialog_operation, null);
                    final Dialog dialog = new Dialog(context, R.style.dialog);
                    dialog.setContentView(dialog_view);
                    AQuery aq1 = new AQuery(dialog_view);
                    aq1.id(R.id.btn_facebook).clicked(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            rtLog(TAG, "----------->URI btn_facebook= " + uri);
                            dialog.cancel();
                            dialog.dismiss();
                        }
                    });
                    aq1.id(R.id.btn_qq).clicked(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            rtLog(TAG, "----------->URI btn_qq= " + uri);
                            dialog.cancel();
                            dialog.dismiss();
                        }
                    });
                    aq1.id(R.id.btn_qq_zone).clicked(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            rtLog(TAG, "----------->URI btn_qq_zone= " + uri);
                            dialog.cancel();
                            dialog.dismiss();
                        }
                    });
                    aq1.id(R.id.btn_wechat).clicked(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            rtLog(TAG, "----------->URI btn_wechat= " + uri);
                            dialog.cancel();
                            dialog.dismiss();
                        }
                    });
                    aq1.id(R.id.btn_wechat_favorite).clicked(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            rtLog(TAG, "----------->URI btn_wechat_favorite= " + uri);
                            dialog.cancel();
                            dialog.dismiss();
                        }
                    });
                    aq1.id(R.id.btn_wechat_moment).clicked(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            rtLog(TAG, "----------->URI btn_wechat_moment= " + uri);
                            dialog.cancel();
                            dialog.dismiss();
                        }
                    });
                    if (fragment != null && fragment instanceof MyPhotoFragment) {
                        aq1.id(R.id.btn_cancel_bookmark).gone();
                    } else if (fragment != null && fragment instanceof MyBookmarkFragment) {
                        aq1.id(R.id.btn_cancel_bookmark).visible();
                    }
                    aq1.id(R.id.btn_cancel_bookmark).clicked(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            rtLog(TAG, "----------->URI btn_delete= " + uri);
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
            });
            rootView.addView(view);
            startAnim(view);
            ImageLoader imageLoader = MyApp.getInstance().getImageLoader();
            imageLoader.get(uri, ImageLoader.getImageListener(picView, R.mipmap.pho_symble, R.mipmap.empty_photo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 原理：动态规划
     *
     * @param view
     */
    private void placeBrick(View view) {
        RelativeLayout.LayoutParams brick = (RelativeLayout.LayoutParams) view.getLayoutParams();
        int groupCount, colSpan, rowSpan;
        List<Integer> groupY = new ArrayList<Integer>();
        List<Integer> groupColY = new ArrayList<Integer>();
        colSpan = (int) Math.ceil(brick.width / this.columnWidth);// 计算跨几列
        colSpan = Math.min(colSpan, this.cols);// 取最小的列数
        rowSpan = (int) Math.ceil(brick.height / this.itemHeight);
        if (colSpan == 1) {
            groupY = this.colYs;
            // 如果存在白块则从添加到白块中
            if (lostPoint.size() > 0 && rowSpan == 1) {
                Point point = lostPoint.get(0);
                int pTop = point.y;
                int pLeft = this.columnWidth * point.x;// 放置的left
                android.widget.RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        brick.width, brick.height);
                params.leftMargin = pLeft;
                params.topMargin = pTop;
                view.setLayoutParams(params);
                lostPoint.remove(0);
                return;
            }

        } else {// 说明有跨列
            groupCount = this.cols + 1 - colSpan;// 添加item的时候列可以填充的列index
            for (int j = 0; j < groupCount; j++) {
                groupColY = this.colYs.subList(j, j + colSpan);
                groupY.add(j, Collections.max(groupColY));// 选择几个可添加的位置
            }
        }
        int minimumY;

        minimumY = Collections.min(groupY);// 取出几个可选位置中最小的添加
        int shortCol = 0;
        int len = groupY.size();
        for (int i = 0; i < len; i++) {
            if (groupY.get(i) == minimumY) {
                shortCol = i;// 获取到最小y值对应的列值
                break;
            }
        }
        int pTop = minimumY;// 这是放置的Top
        int pLeft = this.columnWidth * shortCol;// 放置的left
        android.widget.RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                brick.width, brick.height);
        params.leftMargin = pLeft;
        params.topMargin = pTop;
        view.setLayoutParams(params);
        if (colSpan != 1) {
            for (int i = 0; i < this.cols; i++) {
                if (minimumY > this.colYs.get(i)) {// 出现空行
                    int y = minimumY - this.colYs.get(i);
                    for (int j = 0; j < y / itemHeight; j++) {
                        lostPoint.add(new Point(i, this.colYs.get(i)
                                + itemHeight * j));
                    }
                }
            }
        }
        int setHeight = minimumY + brick.height, setSpan = this.cols + 1 - len;
        for (int i = 0; i < setSpan; i++) {
            this.colYs.set(shortCol + i, setHeight);
        }
    }

    private void startAnim(View v) {
        final float centerX = columnWidth / 2.0f;
        final float centerY = itemHeight / 2.0f;
        Rotate3dAnimation rotation = new Rotate3dAnimation(10, 0, centerX, centerY);
        rotation.setDuration(1000);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new DecelerateInterpolator());

        v.startAnimation(rotation);
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

    @Override
    public void onBottom() {

    }

    @Override
    public void onAutoScroll(int l, int t, int oldl, int oldt) {

    }

}

