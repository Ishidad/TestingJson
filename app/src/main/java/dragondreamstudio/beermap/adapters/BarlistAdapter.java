package dragondreamstudio.beermap.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import dragondreamstudio.beermap.BarDetailActivity;
import dragondreamstudio.beermap.R;
import dragondreamstudio.beermap.models.Bar;

public class BarListAdapter extends  RecyclerView.Adapter<BarListAdapter.MyViewHolder> {

    private List<Bar> mBarList;
    private Context adapterContext;

    public BarListAdapter(List<Bar> barList, Context context){
        mBarList = barList;
        adapterContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bar_list_content,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int i){
        myViewHolder.mBarName.setText(mBarList.get(i).getName());
        int resId = adapterContext.getResources().getIdentifier(mBarList.get(i).getLogo_src(),"drawable", adapterContext.getPackageName());
        myViewHolder.mBarLogo.setImageResource(resId);

        myViewHolder.mView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(adapterContext, BarDetailActivity.class);
                intent.putExtra("placeId", mBarList.get(i).getPlace_id());
                intent.putExtra("Lat", mBarList.get(i).getLat());
                intent.putExtra("Lng", mBarList.get(i).getLng());
                adapterContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBarList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        View mView;
        TextView mBarName;
        ImageView mBarLogo;

        MyViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mBarName = (TextView) itemView.findViewById(R.id.bar_name);
            mBarLogo = (ImageView) itemView.findViewById(R.id.bar_logo);
        }
    }
}
