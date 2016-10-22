package com.peacecorps.pcsa.support_services;

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
 * What to do after an assault?
 *
 * @author rohan
 * @since 2016-07-18
 */
public class AfterAssaultFragment extends Fragment{

    public final static String TAG = AfterAssaultFragment.class.getSimpleName();
    Button stepsButton,ongoingButton,immediateButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_after_assault,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.after_assault);
        stepsButton = (Button) rootView.findViewById(R.id.stepsButton);
        ongoingButton = (Button) rootView.findViewById(R.id.ongoingButton);
        immediateButton = (Button) rootView.findViewById(R.id.immediateButton);
        stepsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Swapping StepsFragment into the container
                StepsFragment stepsFragment = new StepsFragment();
                MainActivity.swapFragmentIn(getActivity(),stepsFragment,StepsFragment.TAG,true);

            }
        });
        ongoingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleTextViewFragment.showSingleTextLayout(getActivity(),getString(R.string.after_assault),
                        getString(R.string.ongoing_title),getString(R.string.ongoing_info));
            }
        });

        immediateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleTextViewFragment.showSingleTextLayout(getActivity(),getString(R.string.after_assault),
                        getString(R.string.immediate_title),getString(R.string.immediate_support_info));
            }
        });
        return rootView;
    }
}

