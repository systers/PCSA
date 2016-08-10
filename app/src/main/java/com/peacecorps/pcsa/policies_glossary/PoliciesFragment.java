package com.peacecorps.pcsa.policies_glossary;

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
import com.peacecorps.pcsa.SingleTextViewFragment;

/*
 * Policies and Glossary, Main Screen
 * @author rohan
 * @since 2016-07-24
 */
public class PoliciesFragment extends Fragment {

    public static final String TAG = PoliciesFragment.class.getSimpleName();
    private Button policyButton, glossaryButton, furtherButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_policies,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.policies_glossary);
        policyButton = (Button)rootView.findViewById(R.id.policiesButton);
        glossaryButton = (Button)rootView.findViewById(R.id.glossaryButton);
        furtherButton = (Button)rootView.findViewById(R.id.furtherButton);

        policyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleTextViewFragment.showSingleTextLayout(getActivity(),getString(R.string.policies_title),getString(R.string.subtitle_policies)
                        ,getString(R.string.policies_all));
            }
        });

        glossaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Swapping GlossaryFragment into the container
                GlossaryFragment glossaryFragment = new GlossaryFragment();
                MainActivity.swapFragmentIn(getActivity(),glossaryFragment,GlossaryFragment.TAG,true);
            }
        });

        furtherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Swapping GlossaryFragment into the container
                FurtherResourcesFragment furtherResourcesFragment = new FurtherResourcesFragment();
                MainActivity.swapFragmentIn(getActivity(),furtherResourcesFragment,FurtherResourcesFragment.TAG,true);
            }
        });

        return rootView;
    }
}

