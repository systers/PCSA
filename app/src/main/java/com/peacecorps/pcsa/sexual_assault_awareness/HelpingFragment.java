package com.peacecorps.pcsa.sexual_assault_awareness;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peacecorps.pcsa.R;

/**
 * Helping Friends and community members
 * @author rohan
 * @since 2016-07-24
 */
public class HelpingFragment extends Fragment {

    public static final String TAG = HelpingFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_reporting_steps,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.helping_others);
        TextView subtitle = (TextView)rootView.findViewById(R.id.reporting_title_steps);
        subtitle.setText(getString(R.string.helping_subtitle));
        TextView reporting_step1 = (TextView) rootView.findViewById(R.id.reporting_step1);
        TextView reporting_step2 = (TextView) rootView.findViewById(R.id.reporting_step2);
        TextView reporting_step3 = (TextView) rootView.findViewById(R.id.reporting_step3);
        TextView reporting_step4 = (TextView) rootView.findViewById(R.id.reporting_step4);
        TextView reporting_step5 = (TextView) rootView.findViewById(R.id.reporting_step5);
        TextView reporting_step6 = (TextView) rootView.findViewById(R.id.reporting_step6);

        reporting_step1.setText(Html.fromHtml(getResources().getString(R.string.helping1)));
        reporting_step2.setText(Html.fromHtml(getResources().getString(R.string.helping2)));
        reporting_step3.setText(Html.fromHtml(getResources().getString(R.string.helping3)));
        reporting_step4.setText(Html.fromHtml(getResources().getString(R.string.helping4)));
        reporting_step5.setText(Html.fromHtml(getResources().getString(R.string.helping5)));
        reporting_step6.setVisibility(View.GONE);
        return rootView;
    }
}
