package dragondreamstudio.beermap.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import dragondreamstudio.beermap.BarInfoActivity;
import dragondreamstudio.beermap.R;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private final String BUNDLE_KEY_NAME = "keyName";
    private final String BUNDLE_KEY_LAT = "keyLat";
    private final String BUNDLE_KEY_LNG = "keyLng";

    private static GoogleMap mMap;
    private static LatLng mPosition;
    private String barName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bar_map, null);

        barName = getArguments().getString(BUNDLE_KEY_NAME);
        mPosition = new LatLng(getArguments().getDouble(BUNDLE_KEY_LAT), getArguments().getDouble(BUNDLE_KEY_LNG));

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        MapFragment mapFragment = new MapFragment();
        ft.replace(R.id.frameLayout, mapFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_NONE);
        ft.addToBackStack(null);
        ft.commit();

        setUpMapIfNeeded();

        return view;
    }

    public static void setUpMapIfNeeded() {
        //set up map here
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

    /**** The mapfragment's id must be removed from the FragmentManager
     **** or else if the same it is passed on the next time then
     **** app will crash ****/
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mMap != null) {
            BarInfoActivity.fragmentManager.beginTransaction()
                    .remove(BarInfoActivity.fragmentManager.findFragmentById(R.id.lite_map_fragment)).commit();
            mMap = null;
        }
    }
}
