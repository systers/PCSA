package com.peacecorps.pcsa.safety_tools;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peacecorps.pcsa.R;
import com.peacecorps.pcsa.support_services.FAQArrayAdapter;

/*
 * Coping with unwanted attention strategies fragment
 *
 * @author rohan
 * @since 2016-07-08
 */
public class CopingFragment extends ListFragment{

    public final static String TAG = CopingFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_coping,container,false);

        String[] values = new String[] {
                getResources().getString(R.string.coping_subtitle1),getResources().getString(R.string.coping_subtitle2),
                getResources().getString(R.string.coping_subtitle3),getResources().getString(R.string.coping_subtitle4),
                getResources().getString(R.string.coping_subtitle5),getResources().getString(R.string.coping_subtitle6),
                getResources().getString(R.string.coping_subtitle7),getResources().getString(R.string.coping_subtitle8),
                getResources().getString(R.string.coping_subtitle9),getResources().getString(R.string.coping_subtitle10)
        };

        String[] titles = new String[]{
                getResources().getString(R.string.coping_title1),getResources().getString(R.string.coping_title2),
                getResources().getString(R.string.coping_title3),getResources().getString(R.string.coping_title4),
                getResources().getString(R.string.coping_title5),getResources().getString(R.string.coping_title6),
                getResources().getString(R.string.coping_title7),getResources().getString(R.string.coping_title8),
                getResources().getString(R.string.coping_title9),getResources().getString(R.string.coping_title10)
        };
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.coping_title);

        FAQArrayAdapter faqArrayAdapter = new FAQArrayAdapter(getActivity(), titles, values);
        setListAdapter(faqArrayAdapter);
        return rootView;
    }
}
