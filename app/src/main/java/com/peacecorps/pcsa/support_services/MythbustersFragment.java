package com.peacecorps.pcsa.support_services;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peacecorps.pcsa.R;

/*
 * Mythbusters and Assumptions
 *
 * @author rohan
 * @since 2016-07-18
 */
public class MythbustersFragment extends ListFragment {

    public final static String TAG = MythbustersFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_coping,container,false);

        String[] values = new String[] {
                getResources().getString(R.string.mythbusters_subtitle1),getResources().getString(R.string.mythbusters_subtitle2),
                getResources().getString(R.string.mythbusters_subtitle3),getResources().getString(R.string.mythbusters_subtitle4),
                getResources().getString(R.string.mythbusters_subtitle5),getResources().getString(R.string.mythbusters_subtitle6),
                getResources().getString(R.string.mythbusters_subtitle7),getResources().getString(R.string.mythbusters_subtitle8),
                getResources().getString(R.string.mythbusters_subtitle9)};

        String[] titles = new String[]{
                getResources().getString(R.string.mythbusters_title1),getResources().getString(R.string.mythbusters_title2),
                getResources().getString(R.string.mythbusters_title3),getResources().getString(R.string.mythbusters_title4),
                getResources().getString(R.string.mythbusters_title5),getResources().getString(R.string.mythbusters_title6),
                getResources().getString(R.string.mythbusters_title7),getResources().getString(R.string.mythbusters_title8),
                getResources().getString(R.string.mythbusters_title9)};
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.mythbusters);

        FAQArrayAdapter faqArrayAdapter = new FAQArrayAdapter(getActivity(), titles, values);
        setListAdapter(faqArrayAdapter);
        return rootView;
    }
}
