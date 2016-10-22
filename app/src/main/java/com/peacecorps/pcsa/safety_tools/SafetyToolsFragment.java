package com.peacecorps.pcsa.safety_tools;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.peacecorps.pcsa.MainActivity;
import com.peacecorps.pcsa.R;
import com.peacecorps.pcsa.circle_of_trust.CircleOfTrustFragment;

/*
 * Safety Tools, main fragment
 *
 * @author rohan
 * @since 2016-07-08
 */
public class SafetyToolsFragment extends Fragment {

    public final static String TAG = SafetyToolsFragment.class.getSimpleName();
    Button radarButton,unwantedAttentionButton,tacticsButton,bystanderButton,
        safetyPlanButton,safetyPlanBasicsButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_safety_tools,container,false);
        radarButton = (Button)rootView.findViewById(R.id.radarButton);
        unwantedAttentionButton = (Button)rootView.findViewById(R.id.unwantedAttentionButton);
        tacticsButton = (Button)rootView.findViewById(R.id.tacticsButton);
        bystanderButton = (Button)rootView.findViewById(R.id.bystanderButton);
        safetyPlanButton = (Button)rootView.findViewById(R.id.safetyPlanButton);
        safetyPlanBasicsButton = (Button)rootView.findViewById(R.id.safetyPlanBasicsButton);

        radarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Swapping RadarFragment into the container
                RadarFragment radarFragment = new RadarFragment();
                MainActivity.swapFragmentIn(getActivity(),radarFragment,RadarFragment.TAG,true);
            }
        });

        unwantedAttentionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Swapping UnwantedAttentionStrategies into the container
                CopingFragment radarFragment = new CopingFragment();
                MainActivity.swapFragmentIn(getActivity(),radarFragment,CopingFragment.TAG,true);
            }
        });

        tacticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Swapping TacticsFragment into the container
                TacticsFragment tacticsFragment = new TacticsFragment();
                MainActivity.swapFragmentIn(getActivity(),tacticsFragment,TacticsFragment.TAG,true);
            }
        });

        bystanderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Swapping Bystander Fragment into the container
                BystanderInterventionFragment bystanderInterventionFragment = new BystanderInterventionFragment();
                MainActivity.swapFragmentIn(getActivity(),bystanderInterventionFragment,BystanderInterventionFragment.TAG,true);
            }
        });

        safetyPlanBasicsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Swapping Safety Plan Basics Fragment into the container
                SafetyPlanBasicsFragment safetyPlanBasicsFragment = new SafetyPlanBasicsFragment();
                MainActivity.swapFragmentIn(getActivity(),safetyPlanBasicsFragment,SafetyPlanBasicsFragment.TAG,true);
            }
        });

        safetyPlanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Swapping Safety Plan Fragment into the container
                Intent intent  = new Intent(getActivity(),SafetyPlanActivity.class);
                startActivity(intent);
            }
        });
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.safety_tools);
        return rootView;
    }
}
