package com.peacecorps.pcsa.policies_glossary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peacecorps.pcsa.R;

/**
 * Resources
 * @author rohan
 * @since 2016-07-31
 */
public class FurtherResourcesFragment extends Fragment{

    public static final String TAG = FurtherResourcesFragment.class.getSimpleName();
    TextView resources;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_resources,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.policies_glossary);
        resources = (TextView)rootView.findViewById(R.id.resources_content);

        return rootView;
    }
}
