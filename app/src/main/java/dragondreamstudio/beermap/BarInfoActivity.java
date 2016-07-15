package dragondreamstudio.beermap;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import dragondreamstudio.beermap.fragments.BarCommentFragment;
import dragondreamstudio.beermap.fragments.BarDetailsFragment;
import dragondreamstudio.beermap.managers.GsonRequest;
import dragondreamstudio.beermap.managers.VolleyManager;
import dragondreamstudio.beermap.models.Places;

public class BarInfoActivity extends AppCompatActivity {

    private static final String TAG = SplashScreenActivity.class.getSimpleName();
    private final String BUNDLE_KEY_NAME = "keyName";
    private final String BUNDLE_KEY_URL = "keyString";
    private final String BUNDLE_KEY_LAT = "keyLat";
    private final String BUNDLE_KEY_LNG = "keyLng";
    public static FragmentManager fragmentManager;
    private String barId;
    private String name;
    private Double lat;
    private Double lng;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_info);

        fragmentManager = getSupportFragmentManager();
        String API_KEY = "&key=AIzaSyAImBQiqvaXOQtqeK8VC-9I96kMmB6Mz7I";
        String API_URL = "https://maps.googleapis.com/maps/api/place/details/json?placeid=";

        Intent intent = getIntent();
        if(intent != null){
            barId = (String) intent.getSerializableExtra("placeId");
            lat = (Double) intent.getSerializableExtra("Lat");
            lng = (Double) intent.getSerializableExtra("Lng");
            name = (String) intent.getSerializableExtra("Name");
        }

        String jsonUrl = API_URL +barId+ API_KEY;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        ViewPager mViewPager = (ViewPager) findViewById(R.id.bar_info_container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.bar_info_tabs);
        tabLayout.setupWithViewPager(mViewPager);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_URL, jsonUrl);
        bundle.putDouble(BUNDLE_KEY_LAT, lat);
        bundle.putDouble(BUNDLE_KEY_LNG, lng);
        bundle.putString(BUNDLE_KEY_NAME, name);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar_info, menu);
        return true;
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

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_bar_details, container, false);
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    BarDetailsFragment barDetailsFragment = new BarDetailsFragment();
                    barDetailsFragment.setArguments(bundle);
                    return barDetailsFragment;
                /*
                case 1:
                    BarCommentFragment barCommentFragment = new BarCommentFragment();
                    barCommentFragment.setArguments(bundle);
                    return barCommentFragment;
                */
                default:
                    break;
            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "INFO";
                case 1:
                    return "COMMENTS";
            }
            return null;
        }
    }
}
