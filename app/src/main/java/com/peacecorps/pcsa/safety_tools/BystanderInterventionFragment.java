package com.peacecorps.pcsa.safety_tools;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.peacecorps.pcsa.MainActivity;
import com.peacecorps.pcsa.R;
import com.peacecorps.pcsa.SingleTextViewFragment;

/*
 * Bystander Intervention main fragment
 *
 * @author rohan
 * @since 2016-07-08
 */
public class BystanderInterventionFragment extends Fragment {

    public static final String TAG = BystanderInterventionFragment.class.getSimpleName();
    Button potentialVictim,potentialOffender,tacticsForBoth;
    TextView safetyText;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_bystander,container,false);
        potentialOffender = (Button) rootView.findViewById(R.id.potentialOffender);
        potentialVictim = (Button) rootView.findViewById(R.id.potentialVictim);
        tacticsForBoth = (Button) rootView.findViewById(R.id.tacticsForBoth);
        safetyText = (TextView) rootView.findViewById(R.id.bystander_text);
        safetyText.setText(Html.fromHtml(getActivity().getString(R.string.safety_text_bystander)));
        safetyText.setTextSize(TypedValue.COMPLEX_UNIT_SP,26);
        safetyText.setGravity(Gravity.CENTER);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.bystander_intervention);


        potentialOffender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleTextViewFragment.showSingleTextLayout(getActivity(),TAG,getString(R.string.bystander_potential_offender),getString(R.string.bystander_verbal_offender));
            }
        });

        potentialVictim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleTextViewFragment.showSingleTextLayout(getActivity(),TAG,getString(R.string.bystander_potential_victim),getString(R.string.bystander_verbal_victim));
            }
        });

        tacticsForBoth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleTextViewFragment.showSingleTextLayout(getActivity(),TAG,getString(R.string.bystander_tactics_both),getString(R.string.bystander_non_verbal));
            }
        });

        return rootView;
    }
}
