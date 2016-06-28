package dragondreamstudio.beermap.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dragondreamstudio.beermap.R;
import dragondreamstudio.beermap.models.Bar;

public class BarListAdapter extends  RecyclerView.Adapter<BarListAdapter.MyViewHolder> {

    List<Bar> mBarList;

    public BarListAdapter(List<Bar> barList){
        mBarList = barList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bar_list_content,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i){
        myViewHolder.mBarName.setText(mBarList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mBarList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView mBarName;

        public MyViewHolder(View itemView) {
            super(itemView);
            mBarName = (TextView) itemView.findViewById(R.id.bar_name);

        }
    }
}
