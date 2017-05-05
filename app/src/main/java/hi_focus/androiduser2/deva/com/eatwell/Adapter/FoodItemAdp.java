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
 * Created by senthil on 16/3/17.
 */

public class FoodItemAdp extends ArrayAdapter<String> {

    private final Context context;
    LayoutInflater inflater;
    private List<String> list = new ArrayList<String>();
    private List<String> listName = new ArrayList<String>();
    private List<String> listImg = new ArrayList<String>();


    public FoodItemAdp(Context context, List<String> list, List<String> listName, List<String> listImg) {
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
        FoodItemAdp.Holder holder = new FoodItemAdp.Holder();
        View rowView = inflater.inflate(R.layout.food_item_list, null, true);


        return rowView;
    }


    public class Holder {
        TextView foodName,store,price;

        RelativeLayout foodImg;



    }
    public void setCallback(FoodListAdapter.FamilyHistoryCallback mCallback) {


    }

    public interface FamilyHistoryCallback {

        void setSelectOnClick(String foodName);

    }

}

