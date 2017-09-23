package com.peacecorps.pcsa;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.peacecorps.pcsa.circle_of_trust.CircleOfTrustFragment;
import com.peacecorps.pcsa.get_help_now.ContactOtherStaff;
import com.peacecorps.pcsa.get_help_now.ContactPostStaff;
import com.peacecorps.pcsa.policies_glossary.FurtherResourcesFragment;
import com.peacecorps.pcsa.policies_glossary.GlossaryFragment;
import com.peacecorps.pcsa.safety_tools.BystanderInterventionFragment;
import com.peacecorps.pcsa.safety_tools.CopingFragment;
import com.peacecorps.pcsa.safety_tools.RadarFragment;
import com.peacecorps.pcsa.safety_tools.SafetyPlanActivity;
import com.peacecorps.pcsa.safety_tools.SafetyPlanBasicsFragment;
import com.peacecorps.pcsa.safety_tools.TacticsFragment;
import com.peacecorps.pcsa.sexual_assault_awareness.CommonFragment;
import com.peacecorps.pcsa.sexual_assault_awareness.HarassmentFragment;
import com.peacecorps.pcsa.sexual_assault_awareness.HelpingFragment;
import com.peacecorps.pcsa.sexual_assault_awareness.WasFragment;
import com.peacecorps.pcsa.support_services.AfterAssaultFragment;
import com.peacecorps.pcsa.support_services.AvailableFragment;
import com.peacecorps.pcsa.support_services.ConfidentialityFragment;
import com.peacecorps.pcsa.support_services.MythbustersFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int PERMISSION_CALLBACK = 100;
    private static final int PERMISSION_SETTINGS = 101;
    public static boolean refreshList = false;
    public static String FRAGMENT_TAG = MainActivityFragment.TAG;
    private Toolbar toolbar;
    private DrawerLayout mDrawer;
    private NavDrawerListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    SharedPreferences sharedPreferences;
    private int lastExpandedGroup = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            /*
            * Check if instance of the required fragment is available
            * in the backstack and swap it into the container
            */
            Fragment unknownFragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
            if (unknownFragment != null) {
                MainActivity.swapFragmentIn(this, unknownFragment, FRAGMENT_TAG, false);
            }
        } else {
            Fragment mainActivityFragment = new MainActivityFragment();
            swapFragmentIn(this, mainActivityFragment, MainActivityFragment.TAG, false);
        }
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        expListView = (ExpandableListView) findViewById(R.id.navbar_expandable_listview);
        prepareListData();
        listAdapter = new NavDrawerListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                switch (groupPosition) {
                    case 0:
                        //Swapping ContactPostStaff into the fragment container dynamically
                        Fragment contactPostStaffFragment = new ContactPostStaff();
                        MainActivity.swapFragmentIn(MainActivity.this, contactPostStaffFragment, ContactPostStaff.TAG, true);
                        mDrawer.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        //Swapping CircleOfTrustFragment into the container
                        CircleOfTrustFragment circleOfTrustFragment = new CircleOfTrustFragment();
                        MainActivity.swapFragmentIn(MainActivity.this, circleOfTrustFragment, CircleOfTrustFragment.TAG, true);
                        mDrawer.closeDrawer(GravityCompat.START);
                        break;
                    case 6:
                        Intent intent = new Intent(MainActivity.this, UserSettingsActivity.class);
                        startActivity(intent);
                        mDrawer.closeDrawer(GravityCompat.START);
                        break;
                    case 7:
                        Toast.makeText(MainActivity.this, getString(R.string.change_name), Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedGroup != -1
                        && groupPosition != lastExpandedGroup) {
                    expListView.collapseGroup(lastExpandedGroup);
                }
                lastExpandedGroup = groupPosition;
            }
        });
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                switch (groupPosition) {
                    case 2:
                        switch (childPosition) {
                            case 0:
                                //Swapping RadarFragment into the container
                                RadarFragment radarFragment = new RadarFragment();
                                MainActivity.swapFragmentIn(MainActivity.this, radarFragment, RadarFragment.TAG, true);
                                break;
                            case 1:
                                //Swapping UnwantedAttentionStrategies into the container
                                CopingFragment copingFragment = new CopingFragment();
                                MainActivity.swapFragmentIn(MainActivity.this, copingFragment, CopingFragment.TAG, true);
                                break;
                            case 2:
                                //Swapping TacticsFragment into the container
                                TacticsFragment tacticsFragment = new TacticsFragment();
                                MainActivity.swapFragmentIn(MainActivity.this, tacticsFragment, TacticsFragment.TAG, true);
                                break;
                            case 3:
                                //Swapping Bystander Fragment into the container
                                BystanderInterventionFragment bystanderInterventionFragment = new BystanderInterventionFragment();
                                MainActivity.swapFragmentIn(MainActivity.this, bystanderInterventionFragment, BystanderInterventionFragment.TAG, true);
                                break;
                            case 4:
                                //Swapping Safety Plan Basics Fragment into the container
                                SafetyPlanBasicsFragment safetyPlanBasicsFragment = new SafetyPlanBasicsFragment();
                                MainActivity.swapFragmentIn(MainActivity.this, safetyPlanBasicsFragment, SafetyPlanBasicsFragment.TAG, true);
                                break;
                            case 5:
                                //Swapping Safety Plan Fragment into the container
                                Intent intent = new Intent(MainActivity.this, SafetyPlanActivity.class);
                                startActivity(intent);
                                break;

                        }
                        break;
                    case 3:
                        switch (childPosition) {
                            case 0:
                                SingleTextViewFragment.showSingleTextLayout(MainActivity.this, getString(R.string.benefits), getString(R.string.benefits_subtitle), getString(R.string.benefits_info));
                                break;
                            case 1:
                                AvailableFragment availableFragment = new AvailableFragment();
                                MainActivity.swapFragmentIn(MainActivity.this, availableFragment, AvailableFragment.TAG, true);
                                break;
                            case 2:
                                SingleTextViewFragment.showSingleTextLayout(MainActivity.this, getString(R.string.commitment), getString(R.string.commitment_subtitle), getString(R.string.commitment_info));
                                break;
                            case 3:
                                //Swapping AfterAssaultFragment into the container
                                AfterAssaultFragment afterAssaultFragment = new AfterAssaultFragment();
                                MainActivity.swapFragmentIn(MainActivity.this, afterAssaultFragment, AfterAssaultFragment.TAG, true);
                                break;
                            case 4:
                                //Swapping ConfidentialityFragment into the container
                                ConfidentialityFragment confidentialityFragment = new ConfidentialityFragment();
                                MainActivity.swapFragmentIn(MainActivity.this, confidentialityFragment, ConfidentialityFragment.TAG, true);
                                break;
                            case 5:
                                //Swapping MythbustersFragment into the container
                                MythbustersFragment mythbustersFragment = new MythbustersFragment();
                                MainActivity.swapFragmentIn(MainActivity.this, mythbustersFragment, MythbustersFragment.TAG, true);
                                break;
                        }
                        break;
                    case 4:
                        switch (childPosition) {
                            case 0:
                                Fragment wasFragment = new WasFragment();
                                MainActivity.swapFragmentIn(MainActivity.this, wasFragment, WasFragment.TAG, true);
                                break;
                            case 1:
                                Fragment commonFragment = new CommonFragment();
                                MainActivity.swapFragmentIn(MainActivity.this, commonFragment, CommonFragment.TAG, true);
                                break;
                            case 2:
                                SingleTextViewFragment.showSingleTextLayout(MainActivity.this, getString(R.string.impact),
                                        getString(R.string.impact_subtitle), getString(R.string.impact_sexual_assault));
                                break;
                            case 3:
                                Fragment harassmentFragment = new HarassmentFragment();
                                MainActivity.swapFragmentIn(MainActivity.this, harassmentFragment, HarassmentFragment.TAG, true);
                                break;
                            case 4:
                                Fragment helpingButton = new HelpingFragment();
                                MainActivity.swapFragmentIn(MainActivity.this, helpingButton, HelpingFragment.TAG, true);
                                break;
                        }
                        break;
                    case 5:
                        switch (childPosition) {
                            case 0:
                                SingleTextViewFragment.showSingleTextLayout(MainActivity.this, getString(R.string.policies_title), getString(R.string.subtitle_policies)
                                        , getString(R.string.policies_all));
                                break;
                            case 1:
                                GlossaryFragment glossaryFragment = new GlossaryFragment();
                                MainActivity.swapFragmentIn(MainActivity.this, glossaryFragment, GlossaryFragment.TAG, true);
                                break;
                            case 2:
                                FurtherResourcesFragment furtherResourcesFragment = new FurtherResourcesFragment();
                                MainActivity.swapFragmentIn(MainActivity.this, furtherResourcesFragment, FurtherResourcesFragment.TAG, true);
                                break;
                        }
                        break;
                }
                mDrawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    public static void swapFragmentIn(FragmentActivity activity, Fragment fragment, String TAG, boolean addToBackStack) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        // Insert the fragment by replacing any existing fragment
        FRAGMENT_TAG = TAG;
        int animEnter = R.anim.fade_in;
        int animExit = R.anim.fade_out;
        int animPopEnter = R.anim.fade_in_back;
        int animPopExit = R.anim.fade_out_back;

        if (addToBackStack) {
            if (TAG.equals(ContactOtherStaff.TAG)) {
                animEnter = R.anim.fade_in;
                animExit = R.anim.fade_out;
                animPopEnter = R.anim.fade_in_back;
                animPopExit = R.anim.fade_out_back;
            }
            fragmentManager.beginTransaction()
                    .setCustomAnimations(animEnter, animExit, animPopEnter, animPopExit)
                    .replace(R.id.fragment_container
                            , fragment, TAG)
                    .addToBackStack(TAG)
                    .commit();
        } else {
            animEnter = R.anim.fade_in;
            animExit = R.anim.fade_out;
            fragmentManager.beginTransaction()
                    .setCustomAnimations(animEnter, animExit, animPopEnter, animPopExit)
                    .replace(R.id.fragment_container
                            , fragment, TAG)
                    .commit();
        }
    }

    /**
     * Populating the expandable list of the Navigation Drawer
     */
    private void prepareListData() {

        listDataChild = new HashMap<String, List<String>>();
        listDataHeader = new LinkedList<>(Arrays.asList(getResources().getStringArray(R.array.headers)));
        String userName = sharedPreferences.getString(getString(R.string.key_name), "");
        String lastElement = listDataHeader.get(listDataHeader.size() - 1) + " " + userName.trim();
        listDataHeader.remove(listDataHeader.size() - 1);
        listDataHeader.add(lastElement);

        List<String> getHelpNow = new ArrayList<>();
        List<String> circleOfTrust = new ArrayList<>();
        List<String> settings = new ArrayList<String>();
        List<String> userLogin = new ArrayList<String>();
        List<String> safetyTools = Arrays.asList(getResources().getStringArray(R.array.safety));
        List<String> supportServices = Arrays.asList(getResources().getStringArray(R.array.support));
        List<String> sexualAssaultAwareness = Arrays.asList(getResources().getStringArray(R.array.assault));
        List<String> policiesGlossary = Arrays.asList(getResources().getStringArray(R.array.policy));

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
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        try {
            unregisterReceiver(CircleOfTrustFragment.sentReceiver);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Not registered");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(CircleOfTrustFragment.sentReceiver, new IntentFilter(CircleOfTrustFragment.SENT));
        if (refreshList) {
            refreshList = false;
            prepareListData();
            listAdapter = new NavDrawerListAdapter(this, listDataHeader, listDataChild);
            expListView.setAdapter(listAdapter);
        }
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
