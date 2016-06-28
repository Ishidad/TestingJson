package dragondreamstudio.beermap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;


import java.util.List;

import dragondreamstudio.beermap.adapters.BarListAdapter;
import dragondreamstudio.beermap.managers.VolleyManager;
import dragondreamstudio.beermap.models.Bar;
import dragondreamstudio.beermap.models.BarList;

public class BarListActivity extends AppCompatActivity {

    public static final String TAG = BarListActivity.class.getSimpleName();
    //private VolleyManager volleyManager;
    //private boolean mTwoPane;
    private BarList barList;
    private RecyclerView myRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //volleyManager = VolleyManager.getInstance(getApplicationContext());
        setContentView(R.layout.activity_bar_list);
        myRecyclerView = (RecyclerView) findViewById(R.id.bar_list);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        Bundle b = this.getIntent().getExtras();
        if(b != null){
            barList = (BarList) b.getSerializable("bundleBarList");
        }

        /*
        View recyclerView = findViewById(R.id.bar_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
        */

        BarListAdapter adapter = new BarListAdapter(barList.getBars());
        myRecyclerView.setAdapter(adapter);

        /*
        if (findViewById(R.id.bar_detail_container) != null) {
            mTwoPane = true;
        }
        */
    }

    /*
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter());
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<DummyContent.DummyItem> mValues;

        public SimpleItemRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.bar_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).content);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(BarDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        BarDetailFragment fragment = new BarDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.bar_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, BarDetailActivity.class);
                        intent.putExtra(BarDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public DummyContent.DummyItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.bar_name);
                mContentView = (TextView) view.findViewById(R.id.bar_subtext);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
    */
}
