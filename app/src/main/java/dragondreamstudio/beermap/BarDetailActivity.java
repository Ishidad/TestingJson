package dragondreamstudio.beermap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import dragondreamstudio.beermap.managers.GsonRequest;
import dragondreamstudio.beermap.managers.VolleyManager;
import dragondreamstudio.beermap.models.Places;

public class BarDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = SplashScreenActivity.class.getSimpleName();
    private String barId;
    private Places places;
    private LatLng mPosition;
    private String barName;

    private TextView fName;
    private TextView fAddress;
    private TextView fPhone;
    private RatingBar fRating;
    private TextView fWeb;
    private ProgressBar fSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_detail);

        Intent intent = getIntent();
        if(intent != null){
            barId = (String) intent.getSerializableExtra("placeId");
            mPosition = new LatLng((Double) intent.getSerializableExtra("Lat"),(Double) intent.getSerializableExtra("Lng"));
        }

        String API_KEY = "&key=AIzaSyAImBQiqvaXOQtqeK8VC-9I96kMmB6Mz7I";
        String API_URL = "https://maps.googleapis.com/maps/api/place/details/json?placeid=";
        String jsonUrl = API_URL +barId+ API_KEY;

        fName = (TextView) this.findViewById(R.id.bar_name_text);
        fRating = (RatingBar) this.findViewById(R.id.ratingBar);
        fAddress = (TextView) this.findViewById(R.id.bar_address_text);
        fPhone = (TextView) this.findViewById(R.id.bar_phone_text);
        fWeb = (TextView) this.findViewById(R.id.bar_url_text);
        fSpinner = (ProgressBar) findViewById(R.id.progressSpinner);

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
                            barName = places.getPlace().getName();
                            if(fName.getText() != null){ fSpinner.setVisibility(View.GONE); }
                            fRating.setRating(places.getPlace().getRating());
                            fAddress.setText(places.getPlace().getFormatted_address());
                            fPhone.setText(places.getPlace().getFormatted_phone_number());
                            fWeb.setText(places.getPlace().getWebsite());
                            /*
                            fMap.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(places.getPlace().getUrl()));
                                    intent.setPackage("com.google.android.apps.maps");
                                    startActivity(intent);
                                }
                            });*/
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

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.liteMap);
        supportMapFragment.getMapAsync(this);
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.getUiSettings().setCompassEnabled(false);
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        googleMap.getUiSettings().setMapToolbarEnabled(false);

        BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.custom_marker);
        assert drawable != null;
        Bitmap bitmap = drawable.getBitmap();

        Marker marker = googleMap.addMarker(new MarkerOptions()
                .title(barName)
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                .position(mPosition)
                .draggable(false));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(mPosition));
    }
}
