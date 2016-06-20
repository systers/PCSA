package com.peacecorps.pcsa.support_services;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peacecorps.pcsa.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class SupportServicesFragment extends Fragment {

    public SupportServicesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_support_services, container, false);
    }
}
