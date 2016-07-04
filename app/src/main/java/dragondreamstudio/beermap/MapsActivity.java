package dragondreamstudio.beermap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import dragondreamstudio.beermap.models.BarList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private BarList barList;
    private LatLng mRosario = new LatLng(-32.947282, -60.6498837);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();
        if(intent != null){
            barList = (BarList) intent.getSerializableExtra("passList");
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        customMapGenerator(googleMap);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(mRosario));
    }

    private void customMapGenerator(GoogleMap gm){
        LatLng markerLatLng;
        BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.custom_marker);
        assert drawable != null;
        Bitmap bitmap = drawable.getBitmap();

        if (barList.getBars().size() > 0){
            for(int i = 0; i < barList.getBars().size(); i++){
                markerLatLng = new LatLng(barList.getBars().get(i).getLat(),barList.getBars().get(i).getLng());
                Marker marker = gm.addMarker(new MarkerOptions()
                        .title(barList.getBars().get(i).getName())
                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                        .position(markerLatLng)
                        .draggable(false));
            }
        }
    }
}
