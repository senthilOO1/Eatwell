package hi_focus.androiduser2.deva.com.eatwell.page;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hi_focus.androiduser2.deva.com.eatwell.Adapter.FoodItemAdp;
import hi_focus.androiduser2.deva.com.eatwell.R;


public class ItemType extends Fragment {

    View rootView;

    private ListView listView;

    private FoodItemAdp foodItemAdp;

    List<String> list = new ArrayList<String>();

    List<String> listmLayoutAddMother = new ArrayList<String>();

    List<String> listmLayoutAddPanternalGrandparents = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_itemtype, container, false);

        listView = (ListView)rootView.findViewById(R.id.foodlist);

        list.add("Idli");
        list.add("Dosa");
        list.add("Sapathi");
        list.add("Piza");
        listmLayoutAddMother.add("Rs.30");
        listmLayoutAddMother.add("Rs.50");
        listmLayoutAddMother.add("Rs.40");
        listmLayoutAddMother.add("Rs.40");
        listmLayoutAddPanternalGrandparents.add("Saravana bhavan");
        listmLayoutAddPanternalGrandparents.add("Anandha bhavan");
        listmLayoutAddPanternalGrandparents.add("Saravana bhavan");
        listmLayoutAddPanternalGrandparents.add("Saravana bhavan");


        foodItemAdp = new FoodItemAdp(getActivity(),list,listmLayoutAddMother,listmLayoutAddPanternalGrandparents);

        listView.setAdapter(foodItemAdp);
        return rootView;
    }


}
