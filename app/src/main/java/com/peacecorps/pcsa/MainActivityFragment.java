package com.peacecorps.pcsa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.peacecorps.pcsa.circle_of_trust.CircleIntro;
import com.peacecorps.pcsa.getHelpNow.ContactPostStaff;
import com.peacecorps.pcsa.reporting.HomeScreen;
import com.peacecorps.pcsa.safety_tools.SafetyTools;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

        public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Button circleButton = (Button) rootView.findViewById(R.id.circleButton);
        Button getHelpNowButton = (Button) rootView.findViewById(R.id.getButton);
        Button safetyButton = (Button) rootView.findViewById(R.id.safetyButton);
        Button supportButton = (Button) rootView.findViewById(R.id.supportButton);
        Button sexualButton = (Button) rootView.findViewById(R.id.sexualButton);
        Button policyButton = (Button) rootView.findViewById(R.id.policyButton);

        getHelpNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Starting ContactPostStaff Activity
                Intent intent = new Intent(getActivity(), ContactPostStaff.class);
                startActivity(intent);
            }
        });

        circleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CircleIntro.class));
            }
        });

        safetyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //safetyTools does not have any functionality yet.
                Toast.makeText(getActivity(), getString(R.string.unavailable_function), Toast.LENGTH_SHORT).show();
            }
        });

        supportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Temporarily commented out until the functionality is implemented.
                //startActivity(new Intent(getActivity(), SupportServices.class));
                Toast.makeText(getActivity(), getString(R.string.unavailable_function), Toast.LENGTH_SHORT).show();
            }
        });

        sexualButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sexualButton does not have any functionality yet.
                Toast.makeText(getActivity(), getString(R.string.unavailable_function), Toast.LENGTH_SHORT).show();
            }
        });

        policyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //policyButton does not have any functionality yet.
                Toast.makeText(getActivity(), getString(R.string.unavailable_function), Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

}
