package com.example.lolipop.materialbannerpager;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


/**import com.android.volley.toolbox.Volley;
 * Created by lolipop on 3/25/16.
 */
public class AppController extends Application {

    public RequestQueue requestQueue;
    private static AppController mInstance;
    public static Context context;



    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        AppController.context = getApplicationContext();

        //OneSignal.startInit(this).autoPromptLocation(true).inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification).init();
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if (requestQueue==null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? "" : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag("");
        getRequestQueue().add(req);
    }

}
