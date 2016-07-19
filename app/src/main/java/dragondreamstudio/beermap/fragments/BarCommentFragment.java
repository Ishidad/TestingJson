package dragondreamstudio.beermap.fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import dragondreamstudio.beermap.BarDetailActivity;
import dragondreamstudio.beermap.R;
import dragondreamstudio.beermap.adapters.BarCommentAdapter;
import dragondreamstudio.beermap.managers.VolleyManager;
import dragondreamstudio.beermap.models.Place;

public class BarCommentFragment extends Fragment{

    private static final String TAG = BarDetailActivity.class.getSimpleName();
    private final String BUNDLE_KEY_URL = "keyString";

    VolleyManager volleyManager;
    private Place reviewList;
    private Context context;
    private View view;
    RecyclerView myRecyclerView;

    private TextView fName;
    private TextView fText;
    private RatingBar fRating;
    private ImageView fImg;

    public static Fragment newInstance() {
        return new BarCommentFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        volleyManager = VolleyManager.getInstance(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view =  inflater.inflate(R.layout.fragment_bar_comments_list, container, false);
        myRecyclerView = (RecyclerView) view.findViewById(R.id.bar_comment_list);
        context = this.getActivity();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());

        BarCommentAdapter adapter = new BarCommentAdapter(reviewList.getReviews(), context);
        myRecyclerView.setAdapter(adapter);

    }
}
