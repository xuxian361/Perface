package perface.sundy.com.perface.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.androidquery.AQuery;

import org.json.JSONObject;

import perface.sundy.com.perface.MyApp;
import perface.sundy.com.perface.R;
import perface.sundy.com.perface.adapter.BookmarkGridAdapter;
import perface.sundy.com.perface.utils.MyConstant;

/**
 * Created by sundy on 15/6/23.
 */
public class MyBookmarkFragment extends BaseFragment {

    private final String TAG = "MyBookmarkFragment";
    private View v;
    private Fragment fragment;
    private LayoutInflater mInflater;
    private GridView gv_photos;
    private BookmarkGridAdapter adapter;

    public MyBookmarkFragment() {
    }

    public MyBookmarkFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        v = inflater.inflate(R.layout.my_bookmark, container, false);
        aq = new AQuery(v);

        init();
        return v;
    }

    private void init() {
        aq.id(R.id.txt_title).text(R.string.my_bookmark).visible();
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
            mCallback.addContent(new PhotoDetailFragment());
        }
    };

    AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            rtLog(TAG, "------------>i = " + i);
            return true;
        }
    };


    private void getMyBookMarks() {
        String tag_json_obj = "json_mybookmark";
        String url = MyConstant.HTTP_BASE + "/album/1733789/masn/p/1/24/";
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("sundy", "------->result = " + object.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

        MyApp.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

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
