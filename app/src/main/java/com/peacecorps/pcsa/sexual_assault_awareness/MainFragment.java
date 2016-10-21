package com.peacecorps.pcsa.sexual_assault_awareness;

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

/**
 * Sexual Assault Awareness Main Screen
 * @author rohan
 * @since 2016-07-24
 */
public class MainFragment extends Fragment{

    public static final String TAG = MainFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_assault_awareness,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.sexual_assault_awareness);

        Button wasButton = (Button) rootView.findViewById(R.id.wasButton);
        Button commonButton = (Button) rootView.findViewById(R.id.commonButton);
        Button impactButton = (Button) rootView.findViewById(R.id.impactButton);
        Button harassmentButton = (Button) rootView.findViewById(R.id.harassmentButton);
        Button helpingButton = (Button) rootView.findViewById(R.id.helpingButton);

        wasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment wasFragment = new WasFragment();
                MainActivity.swapFragmentIn(getActivity(),wasFragment,WasFragment.TAG,true);
            }
        });

        commonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment commonFragment = new CommonFragment();
                MainActivity.swapFragmentIn(getActivity(),commonFragment,CommonFragment.TAG,true);
            }
        });

        impactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleTextViewFragment.showSingleTextLayout(getActivity(),getString(R.string.impact),
                        getString(R.string.impact_subtitle),getString(R.string.impact_sexual_assault));
            }
        });

        harassmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment harassmentFragment = new HarassmentFragment();
                MainActivity.swapFragmentIn(getActivity(),harassmentFragment,HarassmentFragment.TAG,true);
            }
        });

        helpingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment helpingButton = new HelpingFragment();
                MainActivity.swapFragmentIn(getActivity(),helpingButton,HelpingFragment.TAG,true);
            }
        });


        return rootView;
    }


}
