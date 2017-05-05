package hi_focus.androiduser2.deva.com.eatwell;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;


public class FoodDetailsFragment extends Fragment {

    int mFlipping = 0 ;

    private View rootView;

    private ViewFlipper flipper;

    private ImageView One,two,Three;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_food_details, container, false);

        flipper = (ViewFlipper) rootView.findViewById(R.id.flipper1);

        One = (ImageView) rootView.findViewById(R.id.firstDot);
        two = (ImageView) rootView.findViewById(R.id.secondDot);
        Three = (ImageView)rootView.findViewById(R.id.thridDot);

        if(mFlipping==0){
            /** Start Flipping */
            flipper.startFlipping();
            mFlipping=1;
        }
        flipper.addOnLayoutChangeListener(onLayoutChangeListener_viewFlipper);

        return rootView;
    }

    View.OnLayoutChangeListener onLayoutChangeListener_viewFlipper = new View.OnLayoutChangeListener() {
        @Override
        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            Log.d("test", "change to flipper_page1" + flipper.getDisplayedChild());
            One.setImageResource(R.drawable.nondot);
            two.setImageResource(R.drawable.nondot);
            Three.setImageResource(R.drawable.nondot);
            if(flipper.getDisplayedChild() == 0)
            {
                One.setImageResource(R.drawable.dot);
            }else if(flipper.getDisplayedChild() == 1)
            {
                two.setImageResource(R.drawable.dot);
            }else {
                Three.setImageResource(R.drawable.dot);
            }

        }
    };
}
