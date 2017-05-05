package hi_focus.androiduser2.deva.com.eatwell.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

import hi_focus.androiduser2.deva.com.eatwell.R;


/**
 * Created by androiduser2 on 9/11/16.
 */

public class FoodListAdapter extends ArrayAdapter<String> {
    FamilyHistoryCallback mCallback;
    private final Context context;
    LayoutInflater inflater;
    private List<String> list = new ArrayList<String>();
    private List<String> listName = new ArrayList<String>();
    private List<String> listImg = new ArrayList<String>();


    public FoodListAdapter(Context context, List<String> list, List<String> listName, List<String> listImg) {
        super(context, R.layout.foodlist, list.size());
        // TODO Auto-generated constructor stub
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
        this.listName = listName;
        this.listImg = listImg;


    }


    @Override
    public int getCount() {
        return list.size();
    }

    public View getView(final int position, View view, ViewGroup parent) {

        final Boolean[] value = {true};
        Holder holder = new Holder();
        View rowView = inflater.inflate(R.layout.foodlist, null, true);

        holder.foodLayout = (LinearLayout) rowView.findViewById(R.id.foodLayout);

        holder.foodName = (TextView) rowView.findViewById(R.id.foodName);

        holder.store = (TextView) rowView.findViewById(R.id.store);

        holder.price = (TextView) rowView.findViewById(R.id.price);

        holder.foodImg = (RelativeLayout) rowView.findViewById(R.id.foodImg);

        holder.foodName.setText(list.get(position));

        holder.price.setText(listName.get(position));

        holder.store.setText(listImg.get(position));
        if(position == 0){
            holder.foodImg.setBackgroundResource(R.drawable.idli);
        }else if(position == 1){
            holder.foodImg.setBackgroundResource(R.drawable.dosas);
        }else if(position == 2){
            holder.foodImg.setBackgroundResource(R.drawable.sapathi);
        }else if(position == 3){
            holder.foodImg.setBackgroundResource(R.drawable.piza);
        }

        holder.foodLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.setSelectOnClick(list.get(position));
            }
        });

        return rowView;
    }


    public class Holder {
        TextView foodName,store,price;

        RelativeLayout foodImg;

        LinearLayout foodLayout;

    }
    public void setCallback(FamilyHistoryCallback mCallback) {
        this.mCallback = mCallback;

    }

    public interface FamilyHistoryCallback {

        void setSelectOnClick(String foodName);

    }

}
