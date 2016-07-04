package dragondreamstudio.beermap;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import dragondreamstudio.beermap.adapters.BarListAdapter;
import dragondreamstudio.beermap.models.BarList;

public class BarListActivity extends AppCompatActivity {

    private BarList barList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bar_list);
        RecyclerView myRecyclerView = (RecyclerView) findViewById(R.id.bar_list);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        Intent intent = getIntent();
        if(intent != null){
            barList = (BarList) intent.getSerializableExtra("passList");
        }

        BarListAdapter adapter = new BarListAdapter(barList.getBars(), this);
        myRecyclerView.setAdapter(adapter);

        FloatingActionButton mapButton = (FloatingActionButton) findViewById(R.id.map_button);
        if (mapButton != null) {
            mapButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(BarListActivity.this, MapsActivity.class);
                    intent.putExtra("passList", barList);
                    startActivity(intent);
                }
            });
        }
    }


}
