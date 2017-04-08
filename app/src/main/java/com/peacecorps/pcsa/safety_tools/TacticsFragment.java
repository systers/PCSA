package com.peacecorps.pcsa.safety_tools;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.hyphen.DefaultHyphenator;
import com.peacecorps.pcsa.R;

/*
 * Tactics of Sexual Predators, Safety Tools
 *
 * @author rohan
 * @since 2016-07-08
 */
public class TacticsFragment extends Fragment {

    public final static String TAG = TacticsFragment.class.getSimpleName();
    private DocumentView characteristicsAssault;
    private DocumentView tacticsAssault;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tactics, container, false);
        characteristicsAssault = (DocumentView) rootView.findViewById(R.id.tactics_characteristics_assault);
        tacticsAssault = (DocumentView) rootView.findViewById(R.id.tactics_tactics_assault);
        //characteristicsAssault.setMovementMethod(new ScrollingMovementMethod());
        //tacticsAssault.setMovementMethod(new ScrollingMovementMethod());
        characteristicsAssault.setText(Html.fromHtml(getActivity().getString(R.string.tactics_1)));
        tacticsAssault.setText(Html.fromHtml(getActivity().getString(R.string.tactics_2)));

        characteristicsAssault.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.
                getInstance(DefaultHyphenator.HyphenPattern.PT));
        characteristicsAssault.getDocumentLayoutParams().setHyphenated(true);
        tacticsAssault.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.
                getInstance(DefaultHyphenator.HyphenPattern.PT));
        tacticsAssault.getDocumentLayoutParams().setHyphenated(true);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.tactics_title);
        return rootView;
    }
}
