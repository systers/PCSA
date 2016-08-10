package com.peacecorps.pcsa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.peacecorps.pcsa.circle_of_trust.CircleIntro;
import com.peacecorps.pcsa.circle_of_trust.CircleOfTrustFragment;
import com.peacecorps.pcsa.get_help_now.ContactPostStaff;
import com.peacecorps.pcsa.policies_glossary.PoliciesFragment;
import com.peacecorps.pcsa.safety_tools.SafetyToolsFragment;
import com.peacecorps.pcsa.sexual_assault_awareness.MainFragment;
import com.peacecorps.pcsa.support_services.SupportServicesFragment;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public final static String TAG = "MainActivityFragment";
    private boolean introFinished = false;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Button circleButton = (Button) rootView.findViewById(R.id.circleButton);
        Button getHelpNowButton = (Button) rootView.findViewById(R.id.getButton);
        Button safetyToolsButton = (Button) rootView.findViewById(R.id.safetyToolsButton);
        Button supportServicesButton = (Button) rootView.findViewById(R.id.supportServicesButton);
        Button assaultAwarenessButton = (Button) rootView.findViewById(R.id.assaultAwarenessButton);
        Button policiesButton = (Button) rootView.findViewById(R.id.policiesButton);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.first_aide);

        safetyToolsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Swapping Safety Tools HomeFragment Screen into the fragment container
                Fragment safetyToolsFragment = new SafetyToolsFragment();
                MainActivity.swapFragmentIn(getActivity(),safetyToolsFragment,SafetyToolsFragment.TAG,true);
            }
        });
        getHelpNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Swapping ContactPostStaff into the fragment container dynamically
                Fragment contactPostStaffFragment = new ContactPostStaff();
                MainActivity.swapFragmentIn(getActivity(),contactPostStaffFragment,ContactPostStaff.TAG,true);
            }
        });

        supportServicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Swapping Support Services HomeFragment into the fragment container dynamically
                Fragment supportServicesFragment = new SupportServicesFragment();
                MainActivity.swapFragmentIn(getActivity(),supportServicesFragment,SupportServicesFragment.TAG,true);
            }
        });

        assaultAwarenessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Swapping Sexual Assault MainScreen into the fragment container dynamically
                Fragment assaultAwarenessFragment = new MainFragment();
                MainActivity.swapFragmentIn(getActivity(), assaultAwarenessFragment, MainFragment.TAG, true);
            }
        });

        policiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Swapping PoliciesFragment into the fragment container dynamically
                Fragment policiesFragment = new PoliciesFragment();
                MainActivity.swapFragmentIn(getActivity(),policiesFragment,PoliciesFragment.TAG,true);
            }
        });

        circleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!introFinished)
                    startActivityForResult(new Intent(getActivity(), CircleIntro.class),2);
                else
                {
                    //Swapping CircleOfTrustFragment into the container
                    CircleOfTrustFragment circleOfTrustFragment = new CircleOfTrustFragment();
                    MainActivity.swapFragmentIn(getActivity(),circleOfTrustFragment,CircleOfTrustFragment.TAG,true);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 2:
                introFinished = true;
                //Swapping CircleOfTrustFragment into the container
                CircleOfTrustFragment circleOfTrustFragment = new CircleOfTrustFragment();
                MainActivity.swapFragmentIn(getActivity(),circleOfTrustFragment,CircleOfTrustFragment.TAG,true);
        }
    }
}
