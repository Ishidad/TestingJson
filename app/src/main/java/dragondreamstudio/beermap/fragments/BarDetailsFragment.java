package dragondreamstudio.beermap.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import dragondreamstudio.beermap.BarDetailActivity;
import dragondreamstudio.beermap.R;
import dragondreamstudio.beermap.managers.GsonRequest;
import dragondreamstudio.beermap.managers.VolleyManager;
import dragondreamstudio.beermap.models.Places;

public class BarDetailsFragment extends Fragment {

    private static final String TAG = BarDetailActivity.class.getSimpleName();
    private final String BUNDLE_KEY_URL = "keyString";

    VolleyManager volleyManager;
    private Places places;

    private String requestUrl;
    private TextView fAddress;
    private TextView fPhone;
    private RatingBar fRating;
    private TextView fWeb;
    private ProgressBar fSpinner;

    public static Fragment newInstance() {
        return new BarDetailsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        volleyManager = VolleyManager.getInstance(getActivity().getApplicationContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bar_details, null);

        requestUrl = getArguments().getString(BUNDLE_KEY_URL);

        fRating = (RatingBar) view.findViewById(R.id.ratingBar);
        fAddress = (TextView) view.findViewById(R.id.bar_address_text);
        fPhone = (TextView) view.findViewById(R.id.bar_phone_text);
        fWeb = (TextView) view.findViewById(R.id.bar_url_text);
        fSpinner = (ProgressBar) view.findViewById(R.id.progressSpinner);

        view.setVisibility(View.GONE);
        fSpinner.setVisibility(View.VISIBLE);

        requestData();

        return view;
    }

    private void requestData(){
        GsonRequest<Places> request = new GsonRequest<Places>(
                requestUrl,
                Places.class,
                null,
                new Response.Listener<Places>() {
                    @Override
                    public void onResponse(Places response) {
                        if(response!=null) {
                            places = response;

                            fRating.setRating(places.getPlace().getRating());
                            fAddress.setText(places.getPlace().getFormatted_address());
                            if(fAddress.getText() != null){
                                fSpinner.setVisibility(View.GONE);
                            }
                            fPhone.setText(places.getPlace().getFormatted_phone_number());
                            fWeb.setText(places.getPlace().getWebsite());
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
        request.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        request.setShouldCache(false);
        volleyManager.addToRequestQueue(request);
    }
}
