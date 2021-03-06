package dragondreamstudio.beermap.json;

import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Requestor {
    public static JSONObject sendJsonRequest(RequestQueue requestQueue, String url) {
        JSONObject response = null;
        RequestFuture<JSONObject> requestFuture = RequestFuture.newFuture();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url,
                null, requestFuture, requestFuture);
        try {
            response = requestFuture.get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Log.e(e + "", "");
        } catch (ExecutionException e) {
            Log.e(e + "", "");
        } catch (TimeoutException e) {
            Log.e(e + "", "");
        }
        requestQueue.add(request);
        return response;
    }
}
