package com.peacecorps.pcsa;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.peacecorps.pcsa.circle_of_trust.CircleOfTrustFragment;
import com.peacecorps.pcsa.policies_glossary.GlossaryFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout mDrawer;
    private NavDrawerListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private static final String TAG = MainActivity.class.getSimpleName();
    public static String FRAGMENT_TAG = MainActivityFragment.TAG;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null)
        {
            /*
            * Check if instance of the required fragment is available
            * in the backstack and swap it into the container
            */
            Fragment unknownFragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
            MainActivity.swapFragmentIn(this,unknownFragment,FRAGMENT_TAG,false);
        }
        else
        {
            Fragment mainActivityFragment = new GlossaryFragment();
            swapFragmentIn(this,mainActivityFragment,MainActivityFragment.TAG,false);
        }
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();
        expListView = (ExpandableListView) findViewById(R.id.navbar_expandable_listview);
        prepareListData();
        listAdapter = new NavDrawerListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
    }

    public static void swapFragmentIn(FragmentActivity activity, Fragment fragment, String TAG, boolean addToBackStack)
    {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        // Insert the fragment by replacing any existing fragment
        FRAGMENT_TAG = TAG;
        if(addToBackStack){
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.push_down_in,R.anim.push_down_out,R.anim.fade_in,R.anim.fade_out)
                    .replace(R.id.fragment_container
                            , fragment,TAG)
                    .addToBackStack(TAG)
                    .commit();
        }
        else
        {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out)
                    .replace(R.id.fragment_container
                            , fragment,TAG)
                    .commit();
        }
    }

    /**
     * Populating the expandable list of the Navigation Drawer
     */
    private void prepareListData() {

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        listDataHeader.add(getString(R.string.get_help));
        listDataHeader.add(getString(R.string.circle_title));
        listDataHeader.add(getString(R.string.safety_tools));
        listDataHeader.add(getString(R.string.support_services));
        listDataHeader.add(getString(R.string.sexual_assault_awareness));
        listDataHeader.add(getString(R.string.policies_glossary));
        listDataHeader.add(getString(R.string.settings));
        listDataHeader.add(getString(R.string.user_login));

        List<String> getHelpNow = new ArrayList<>();
        List<String> circleOfTrust = new ArrayList<>();
        List<String> safetyTools = new ArrayList<String>();
        List<String> settings = new ArrayList<String>();
        List<String> userLogin = new ArrayList<String>();
        safetyTools.add(getString(R.string.radar));
        safetyTools.add(getString(R.string.unwanted_attention));
        safetyTools.add(getString(R.string.commonalities));
        safetyTools.add(getString(R.string.bystander_intervention));
        safetyTools.add(getString(R.string.safety_plan_basics));
        safetyTools.add(getString(R.string.safety_plan_title));

        List<String> supportServices = new ArrayList<String>();
        supportServices.add(getString(R.string.benefits));
        supportServices.add(getString(R.string.available_services));
        supportServices.add(getString(R.string.commitment));
        supportServices.add(getString(R.string.after_assault));
        supportServices.add(getString(R.string.title_activity_confidentiality));
        supportServices.add(getString(R.string.mythbusters));

        List<String> sexualAssaultAwareness = new ArrayList<String>();
        sexualAssaultAwareness.add(getString(R.string.was_assault));
        sexualAssaultAwareness.add(getString(R.string.common_questions));
        sexualAssaultAwareness.add(getString(R.string.impact));
        sexualAssaultAwareness.add(getString(R.string.harassment));
        sexualAssaultAwareness.add(getString(R.string.helping_others));

        List<String> policiesGlossary = new ArrayList<String>();
        policiesGlossary.add(getString(R.string.summary_sheet));
        policiesGlossary.add(getString(R.string.glossary));
        policiesGlossary.add(getString(R.string.further_resources));

        listDataChild.put(listDataHeader.get(0), getHelpNow);
        listDataChild.put(listDataHeader.get(1), circleOfTrust);
        listDataChild.put(listDataHeader.get(2), safetyTools);
        listDataChild.put(listDataHeader.get(3), supportServices);
        listDataChild.put(listDataHeader.get(4), sexualAssaultAwareness);
        listDataChild.put(listDataHeader.get(5), policiesGlossary);
        listDataChild.put(listDataHeader.get(6), settings);
        listDataChild.put(listDataHeader.get(7), userLogin);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        try{
            unregisterReceiver(CircleOfTrustFragment.sentReceiver);
        }catch (IllegalArgumentException e){
            Log.e(TAG,"Not registered");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(CircleOfTrustFragment.sentReceiver, new IntentFilter(CircleOfTrustFragment.SENT));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_settings:
                Intent intent = new Intent(this, UserSettingsActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
