package dragondreamstudio.beermap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import dragondreamstudio.beermap.managers.GsonRequest;
import dragondreamstudio.beermap.managers.VolleyManager;
import dragondreamstudio.beermap.models.Places;

public class BarDetailActivity extends AppCompatActivity {

    private static final String TAG = SplashScreenActivity.class.getSimpleName();
    private final String API_URL = "https://maps.googleapis.com/maps/api/place/details/json?placeid=";
    private final String API_KEY = "&key=AIzaSyAImBQiqvaXOQtqeK8VC-9I96kMmB6Mz7I";
    private String barId;
    private Places places;

    private TextView fName;
    private TextView fAddress;
    private TextView fPhone;
    private RatingBar fRating;
    private TextView fWeb;
    private ImageView fUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_detail);
        View view = getWindow().getDecorView();

        Intent intent = getIntent();
        if(intent != null){
            barId = (String) intent.getSerializableExtra("placeId");
        }

        String jsonUrl = API_URL+barId+API_KEY;

        fName = (TextView) this.findViewById(R.id.bar_name_text);
        fRating = (RatingBar) this.findViewById(R.id.ratingBar);
        fAddress = (TextView) this.findViewById(R.id.bar_address_text);
        fPhone = (TextView) this.findViewById(R.id.bar_phone_text);
        fWeb = (TextView) this.findViewById(R.id.bar_url_text);
        fUrl = (ImageView) this.findViewById(R.id.google_map_button);

        GsonRequest<Places> request = new GsonRequest<Places>(
                jsonUrl,
                Places.class,
                null,
                new Response.Listener<Places>() {
                    @Override
                    public void onResponse(Places response) {
                        if(response!=null) {
                            places = response;

                            fName.setText(places.getPlace().getName());
                            fRating.setRating(places.getPlace().getRating());
                            fAddress.setText(places.getPlace().getFormatted_address());
                            fPhone.setText(places.getPlace().getFormatted_phone_number());
                            fWeb.setText(places.getPlace().getWebsite());
                            fUrl.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(places.getPlace().getUrl()));
                                    intent.setPackage("com.google.android.apps.maps");
                                    startActivity(intent);
                                }
                            });
                        }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            navigateUpTo(new Intent(this, BarListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
