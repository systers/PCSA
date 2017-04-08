package com.peacecorps.pcsa.support_services;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.peacecorps.pcsa.R;
import com.peacecorps.pcsa.safety_tools.FormattedSafetyPlanBasicsContentFragment;
import com.peacecorps.pcsa.safety_tools.SafetyPlanBasicsContentFragment;

/*
 * CONFIDENTIALITY
 *
 * @author rohan
 * @since 2016-07-18
 */
public class ConfidentialityFragment extends Fragment {

    public final static String TAG = ConfidentialityFragment.class.getSimpleName();
    Button staffButton,communityButton,assaultedButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_confidentiality,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.title_activity_confidentiality);
        staffButton = (Button)rootView.findViewById(R.id.staffButton);
        communityButton = (Button)rootView.findViewById(R.id.communityButton);
        assaultedButton = (Button)rootView.findViewById(R.id.assaultedButton);
        staffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SafetyPlanBasicsContentFragment.showDialog(getActivity(),getString(R.string.role_staff),getString(R.string.confidentiality_role_staff));
            }
        });

        communityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SafetyPlanBasicsContentFragment.showDialog(getActivity(),getString(R.string.community_title),getString(R.string.confidentiality_community));
            }
        });

        assaultedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FormattedSafetyPlanBasicsContentFragment.showDialog(getActivity(),
                        getString(R.string.assaulted_title),
                        getString(R.string.confidentiality_assault));

            }
        });
        return rootView;
    }
}

