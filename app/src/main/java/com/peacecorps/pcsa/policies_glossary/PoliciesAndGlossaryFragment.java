package com.peacecorps.pcsa.policies_glossary;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peacecorps.pcsa.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PoliciesAndGlossaryFragment extends Fragment {

    public PoliciesAndGlossaryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_policies_and_glossary, container, false);
    }
}
