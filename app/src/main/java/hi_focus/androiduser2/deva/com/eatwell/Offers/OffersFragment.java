package hi_focus.androiduser2.deva.com.eatwell.Offers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hi_focus.androiduser2.deva.com.eatwell.R;


public class OffersFragment extends Fragment {

    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_offers, container, false);

    return rootView;
    }


}
