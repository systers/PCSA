package com.peacecorps.pcsa.sexual_assault_awareness;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peacecorps.pcsa.R;
import com.peacecorps.pcsa.support_services.FAQArrayAdapter;

/*
 * Sexual Assault Common Questions
 * @author rohan
 * @since 2016-07-24
 */
public class CommonFragment extends ListFragment {

    public static final String TAG = CommonFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_coping,container,false);
        TextView subtitle = (TextView)rootView.findViewById(R.id.subtitle);
        subtitle.setText(getString(R.string.common_questions));

        String[] values = new String[] {
                getResources().getString(R.string.common_as1),getResources().getString(R.string.common_as2),
                getResources().getString(R.string.common_as3),getResources().getString(R.string.common_as4),
                getResources().getString(R.string.common_as5)};

        String[] titles = new String[]{
                getResources().getString(R.string.common_qs_title1),getResources().getString(R.string.common_qs_title2),
                getResources().getString(R.string.common_qs_title3),getResources().getString(R.string.common_qs_title4),
                getResources().getString(R.string.common_qs_title5)};

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.sexual_assault_awareness);

        FAQArrayAdapter faqArrayAdapter = new FAQArrayAdapter(getActivity(), titles, values);
        setListAdapter(faqArrayAdapter);
        return rootView;
    }
}

