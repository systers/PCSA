package com.peacecorps.pcsa.safety_resources;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peacecorps.pcsa.R;

/*
 * Activity for Safety Resources fragment container
 *
 * @author calistus
 * @since 2015-08-18
 */


/**
 * A placeholder fragment containing a simple view.
 */
public class SafetyResourcesFragment extends Fragment {

    public SafetyResourcesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_safety_resources, container, false);
    }
}
