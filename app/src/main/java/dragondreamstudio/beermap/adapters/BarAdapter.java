package dragondreamstudio.beermap.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dragondreamstudio.beermap.models.Bar;
import dragondreamstudio.beermap.R;

public class BarAdapter extends ArrayAdapter<Bar> {

    Context mContext;
    int mLayoutResourceId;
    List<Bar> mData = null;

    public BarAdapter(Context context, int resource, List<Bar> data) {
        super(context, resource, data);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mData = data;
    }

    @Override
    public Bar getItem(int position){
        return super.getItem(position);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View row = convertView;
        PlaceHolder holder = null;

        //If there is no row view to reuse
        if(row == null) {
            //Create a new View
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(mLayoutResourceId, parent, false);

            holder = new PlaceHolder();

            holder.nameView = (TextView) row.findViewById(R.id.bar_name);
            holder.imageView = (ImageView) row.findViewById(R.id.bar_logo);

            row.setTag(holder);
        }else{
            //If not use the existing one
            holder = (PlaceHolder) row.getTag();
        }

        //get the data from the array
        Bar bar = mData.get(position);

        //Set the view to reflect the data we need to display
        //This set the logo of the bar
        holder.nameView.setText(bar.getName());
        //This set the src of the image
        int resId = mContext.getResources().getIdentifier(bar.getLogo_src(),"drawable",mContext.getPackageName());
        holder.imageView.setImageResource(resId);

        //Returning the row view
        return row;
    }

    private static class PlaceHolder{
        TextView nameView;
        ImageView imageView;
    }
}
