package dragondreamstudio.beermap;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

public class BarDetailActivity extends AppCompatActivity {

    private final String API_URL = "https://maps.googleapis.com/maps/api/place/details/json?placeid=";
    private final String API_KEY = "&key=AIzaSyAImBQiqvaXOQtqeK8VC-9I96kMmB6Mz7I";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(BarDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(BarDetailFragment.ARG_ITEM_ID));
            BarDetailFragment fragment = new BarDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.bar_detail_container, fragment)
                    .commit();
        }
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
