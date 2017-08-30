package com.peacecorps.pcsa.support_services;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.hyphen.DefaultHyphenator;
import com.peacecorps.pcsa.MainActivity;
import com.peacecorps.pcsa.R;

/**
 * Available Services after a Sexual Assault
 *
 * @author Buddhiprabha Erabadda
 * @since 07-08-2015
 */
public class AvailableFragment extends Fragment {

    public static final String TAG = AvailableFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reporting_types, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.available_services_custom);

        DocumentView descBoth = (DocumentView) rootView.findViewById(R.id.reporting_both);
        DocumentView descStandard = (DocumentView) rootView.findViewById(R.id.reporting_standard);
        final Button faq = (Button) rootView.findViewById(R.id.reporting_faq);

        //descBoth.setMovementMethod(new ScrollingMovementMethod());
        //descStandard.setMovementMethod(new ScrollingMovementMethod());

        descBoth.setText(getActivity().getString(R.string.reporting_desc_standard));
        descStandard.setText(getActivity().getString(R.string.reporting_desc_restricted));

        descBoth.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.
                getInstance(DefaultHyphenator.HyphenPattern.PT));
        descBoth.getDocumentLayoutParams().setHyphenated(true);
        descStandard.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.
                getInstance(DefaultHyphenator.HyphenPattern.PT));
        descStandard.getDocumentLayoutParams().setHyphenated(true);

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_activity_faq);
                Fragment faqFragment = new FAQFragment();
                MainActivity.swapFragmentIn(getActivity(), faqFragment, FAQFragment.TAG, true);

            }
        });
        return rootView;
    }

}
