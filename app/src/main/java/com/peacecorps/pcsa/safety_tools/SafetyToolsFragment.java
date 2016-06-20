package com.peacecorps.pcsa.safety_tools;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peacecorps.pcsa.R;

/*
 * Safety Resources main fragment 
 *
 * @author calistus
 * @since 2015-08-18
 */
public class SafetyToolsFragment extends Fragment {

    public SafetyToolsFragment() {
    }
    /**
     * Create the view for this fragment, using the arguments given to it. 
     * 
     * @param inflater inflate any views in the fragment
     * @param container if non-null, this is the parent view that the fragment's UI should be attached to. .
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state 
     * @return the properly constructed view object
     */
     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment                         
        return inflater.inflate(R.layout.fragment_safety_resources, container, false);
    }
}