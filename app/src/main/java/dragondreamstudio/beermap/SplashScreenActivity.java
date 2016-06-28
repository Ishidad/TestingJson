package dragondreamstudio.beermap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import dragondreamstudio.beermap.managers.VolleyManager;
import dragondreamstudio.beermap.models.BarList;

public class SplashScreenActivity extends AppCompatActivity{

    private static final String JSON_URL = "https://raw.githubusercontent.com/Ishidad/Beermap/master/bars.json";
    private Intent i = new Intent();
    private Bundle b = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        StringRequest request = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                BarList barList = new Gson().fromJson(response,BarList.class);
                //Toast.makeText(SplashScreenActivity.this, response, Toast.LENGTH_SHORT).show();
                b.putSerializable("bundleBarList", barList);
                i.putExtras(b);
                i.setClass(SplashScreenActivity.this,BarListActivity.class);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                //error
            }
        });

        VolleyManager.getInstance(this);
        VolleyManager.myVolleyManager.addToRequestQueue(request);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(i));
                finish();
            }
        }, 4000);
    }
}
