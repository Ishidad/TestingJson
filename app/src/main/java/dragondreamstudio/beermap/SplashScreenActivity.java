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

    private static final String JSON_URL = "https://raw.githubusercontent.com/Ishidad/Beermap/master/bars.json";
    private static final String TAG = SplashScreenActivity.class.getSimpleName();
    private Intent intent;
    private Bundle bundle;
    private BarList barList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        GsonRequest<BarList> request = new GsonRequest<BarList>(
                JSON_URL,
                BarList.class,
                null,
                new Response.Listener<BarList>() {
                    @Override
                    public void onResponse(BarList response) {
                        barList = response;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());
                    }
                }
        );

        VolleyManager.getInstance(this);
        VolleyManager.myVolleyManager.addToRequestQueue(request);

        intent = new Intent(this, BarListActivity.class);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //bundle.putSerializable("bundle", barList);
                intent.putExtra("passList", barList);
                //intent.setClass(SplashScreenActivity.this,BarListActivity.class);
                startActivity(new Intent(intent));
                finish();
            }
        }, 5000);
    }
}
