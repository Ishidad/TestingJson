package dragondreamstudio.beermap.managers;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleyManager {

    public static VolleyManager myVolleyManager;
    private final Context context;
    private RequestQueue requestQueue;

    private VolleyManager(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public synchronized static VolleyManager getInstance(Context context) {
        if (myVolleyManager == null) {
            myVolleyManager = new VolleyManager(context);
        }
        return myVolleyManager;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}