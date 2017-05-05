package hi_focus.androiduser2.deva.com.eatwell;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import hi_focus.androiduser2.deva.com.eatwell.Adapter.FoodListAdapter;
import hi_focus.androiduser2.deva.com.eatwell.Adapter.NavigationAdapter;
import hi_focus.androiduser2.deva.com.eatwell.firebase.Config;
import hi_focus.androiduser2.deva.com.eatwell.firebase.NotificationUtils;
import hi_focus.androiduser2.deva.com.eatwell.page.ItemType;
import hi_focus.androiduser2.deva.com.eatwell.profile.Profile;


/**
 * Created by androiduser2 on 10/11/16.
 */

public class Home extends AppCompatActivity implements FoodListAdapter.FamilyHistoryCallback {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ListView listmenuMain1,foodList;
    private NavigationAdapter mAdapter;
    private FoodListAdapter foodListAdapter;
    private boolean isNavigationViewClicked;
    private int pos, fragementPosition;
    private ImageView filterImge;
    private Fragment mFragment;
    private TextView textView;
    private SearchView Search;
    List<String> list = new ArrayList<String>();
    List<String> listmLayoutAddMother = new ArrayList<String>();
    List<String> listmLayoutAddPanternalGrandparents = new ArrayList<String>();

    private static final String TAG = Home.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private TextView txtRegId, txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doshboard);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        textView = (TextView)findViewById(R.id.toolbar_title);
        textView.setText("Menu");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

                    txtMessage.setText(message);
                }
            }
        };

        displayFirebaseRegId();

        mAdapter = new NavigationAdapter(this);
        mAdapter.menuType(1,null);
        ListView listmenuMain = (ListView) findViewById(R.id.list);
        listmenuMain.setAdapter(mAdapter);
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

        listmenuMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                isNavigationViewClicked = true;
               // getSearchView().setVisibility(View.GONE);
                FragmentManager fm = getSupportFragmentManager();

                fragementPosition = position;

                switch (position) {

                    case 0:
                        pos = 1;
                        getSupportFragmentManager().beginTransaction().remove(mFragment).commit();
                        textView.setText("Menu");
                        drawerLayout.closeDrawers();

                        break;

                    case 1:
                        mFragment = new FoodDetailsFragment();
                        setFragment(mFragment, "FoodDetails");
                        break;
                    case 2:
                        mFragment = new Profile();
                        setFragment(mFragment, "FoodDetails");
                        textView.setText("Profile");
                        break;

                    default:
                        Toast.makeText(getApplicationContext(), "Happy to coming soon", Toast.LENGTH_SHORT).show();
                        break;

                }

            }
        });
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override

            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

                Log.d("nnnn","llll");
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };
        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        foodList = (ListView) findViewById(R.id.foodList);

        foodListAdapter = new FoodListAdapter(this,list,listmLayoutAddMother,listmLayoutAddPanternalGrandparents);

        foodListAdapter.setCallback(this);

        foodList.setAdapter(foodListAdapter);
    }
    public void setFragment(final Fragment fragment, final String title) {


        FragmentManager fm = getSupportFragmentManager();
        //  Log.v("fragment countssssss","========"+fm.getBackStackEntryCount());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransactionManager = fragmentManager.beginTransaction();
                fragmentTransactionManager.replace(R.id.frame, fragment, title);


                fragmentTransactionManager.addToBackStack("" + fragment).commit();
                drawerLayout.closeDrawers();
            }
        });




    }

    @Override
    public void setSelectOnClick(String foodName) {
        mFragment = new ItemType();
        setFragment(mFragment, foodName);
    }
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e(TAG, "Firebase reg id: " + regId);

       /* if (!TextUtils.isEmpty(regId))
           // txtRegId.setText("Firebase Reg Id: " + regId);
        else
           // txtRegId.setText("Firebase Reg Id is not received yet!");*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }
}
