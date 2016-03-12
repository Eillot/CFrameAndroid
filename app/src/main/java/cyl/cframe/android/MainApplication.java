package cyl.cframe.android;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

import cyl.cframe.android.common.PreferencesCookieStore;
import cyl.cframe.android.utils.LruBitmapCache;
import cyl.cframe.library.database.DBConfig;
import cyl.cframe.library.database.DBHelper;
import cyl.cframe.library.utils.LogUtil;

/**
 * Created by jerry on 2016/3/8.
 */
public class MainApplication extends Application {

    public static final String TAG = MainApplication.class
            .getSimpleName();
    private static MainApplication mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    public static boolean isUpdateHomedata = true;

    public static synchronized MainApplication getInstance() {
        return mInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        LogUtil.isDebug = BuildConfig.DEBUG;
        //HttpUtils.getInstance(this).setDebug(true);

//        DBConfig dbConfig = new DBConfig.Builder(this)
//                .dbName("cao.db")
//                .dbVersion(1)
//                .debug(true)
//                .build();
//        DBHelper.getInstance().init(dbConfig);
//        DBHelper.getInstance().setUpgradeListener(new DBHelper.DbUpgradeListener() {
//            @Override
//            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//            }
//        });
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            //非持久化存储(内存存储) BasicCookieStore | 持久化存储 PreferencesCookieStore
            CookieStore cookieStore = new PreferencesCookieStore(this);
            httpClient.setCookieStore(cookieStore);
            HttpStack httpStack = new HttpClientStack(httpClient);
            mRequestQueue = Volley.newRequestQueue(getApplicationContext(), httpStack);
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
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
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}
