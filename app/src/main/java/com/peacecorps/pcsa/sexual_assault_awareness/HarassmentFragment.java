package com.peacecorps.pcsa.sexual_assault_awareness;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.peacecorps.pcsa.R;
import com.peacecorps.pcsa.SingleTextViewFragment;

/**
 * Sexual Harassment
 * @author rohan
 * @since 2016-07-24
 */
public class HarassmentFragment extends Fragment{

    TextView wasContent,subtitle;
    Button knowButton;
    public static final String TAG = HarassmentFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_was,container,false);
        wasContent = (TextView)rootView.findViewById(R.id.wasContent);
        subtitle = (TextView)rootView.findViewById(R.id.subtitle);
        subtitle.setText(getString(R.string.harassment_subtitle));
        knowButton = (Button) rootView.findViewById(R.id.knowButton);
        knowButton.setText(getString(R.string.harassment_subtitle));
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.harassment);
        wasContent.setText(Html.fromHtml(getString(R.string.harassment_content)));
        wasContent.setGravity(Gravity.CENTER);
        knowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Swapping Sexual Harassment  info into the fragment container
                SingleTextViewFragment.showSingleTextLayout(getActivity(),getString(R.string.harassment),
                        getString(R.string.harassment_subtitle),getString(R.string.harassment_more));
            }
        });
        return rootView;
    }
}
