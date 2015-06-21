package perface.sundy.com.perface;

import android.app.Activity;
import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import perface.sundy.com.perface.utils.LruBitmapCache;

/**
 * Created by sundy on 15/6/21.
 */
public class MyApp extends Application {

    public static final String TAG = MyApp.class.getSimpleName();

    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    private static MyApp myApp;

    public static List<Activity> activityList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public static synchronized MyApp getInstance() {
        return myApp;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return requestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (imageLoader == null) {
            imageLoader = new ImageLoader(this.requestQueue,
                    new LruBitmapCache());
        }
        return this.imageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }

    /* Handle Activity start */
    public static void addActivity(Activity activity) {
        if (activity != null) {
            activityList.add(activity);
        }
    }

    public static void finishAllActivity() {
        if (activityList != null && activityList.size() != 0) {
            for (Activity activity : activityList) {
                activity.finish();
            }
            activityList.clear();
            System.exit(0);
        }
    }
    /* Handle Activity end */

}
