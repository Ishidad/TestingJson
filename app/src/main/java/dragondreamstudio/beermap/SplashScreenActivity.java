package dragondreamstudio.beermap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import dragondreamstudio.beermap.managers.GsonRequest;
import dragondreamstudio.beermap.managers.VolleyManager;
import dragondreamstudio.beermap.models.BarList;

public class SplashScreenActivity extends AppCompatActivity{

    private static final String JSON_URL = "https://raw.githubusercontent.com/Ishidad/TestingJson/master/bars.json";
    private static final String TAG = SplashScreenActivity.class.getSimpleName();
    private Intent intent;
    private BarList barList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        intent = new Intent(this, BarListActivity.class);

        GsonRequest<BarList> request = new GsonRequest<BarList>(
                JSON_URL,
                BarList.class,
                null,
                new Response.Listener<BarList>() {
                    @Override
                    public void onResponse(BarList response) {
                        barList = response;


                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                intent.putExtra("passList", barList);
                                startActivity(new Intent(intent));
                                finish();
                            }
                        });
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error on JSON response: " + error.getMessage());
                    }
                }
        );

        VolleyManager.getInstance(this);
        VolleyManager.myVolleyManager.addToRequestQueue(request);
    }
}
