package com.peacecorps.pcsa.safety_tools;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peacecorps.pcsa.R;

/**
 * Tactics of Sexual Predators, Safety Tools
 *
 * @author rohan
 * @since 2016-07-08
 */
public class TacticsFragment extends Fragment {

    private TextView characteristicsAssault,tacticsAssault;
    public final static String TAG = TacticsFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_tactics,container,false);
        characteristicsAssault = (TextView) rootView.findViewById(R.id.tactics_characteristics_assault);
        tacticsAssault = (TextView) rootView.findViewById(R.id.tactics_tactics_assault);
        characteristicsAssault.setMovementMethod(new ScrollingMovementMethod());
        tacticsAssault.setMovementMethod(new ScrollingMovementMethod());
        characteristicsAssault.setText(Html.fromHtml(getActivity().getString(R.string.tactics_1)));
        tacticsAssault.setText(Html.fromHtml(getActivity().getString(R.string.tactics_2)));
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.tactics_title);
        return rootView;
    }
}
