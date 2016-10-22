package com.peacecorps.pcsa;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * This class has to be instantiated for all layouts which have a single text view
 * @author rohan
 * @since 2016-07-18
 */
public class SingleTextViewFragment extends Fragment {

    public static final String TAG = SingleTextViewFragment.class.getSimpleName();
    public static final String TOOLBAR_KEY = "TOOLBAR";
    public static final String CONTENT_KEY = "CONTENT";
    public static final String SUBTITLE_KEY = "SUBTITLE";
    TextView subTitle, content;
    String toolbarTitle, subtitle, contentString;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_single_textview, container, false);
        subTitle = (TextView)rootView.findViewById(R.id.layout_subtitle);
        content = (TextView)rootView.findViewById(R.id.layout_content);
        toolbarTitle = getArguments().getString(TOOLBAR_KEY);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(toolbarTitle);
        subtitle = getArguments().getString(SUBTITLE_KEY);
        subTitle.setText(subtitle);
        contentString = getArguments().getString(CONTENT_KEY);
        content.setText(Html.fromHtml(contentString));
        content.setMovementMethod(new ScrollingMovementMethod());
        return rootView;
    }

    /**
     * Populates the required data for the layout which appears
     * @param subTitle subtitle of the layout
     * @param contentToShow  data to be displayed
     * @param toolbarString displayed on the toolbar
     */
    public static void showSingleTextLayout(FragmentActivity mainActivity, String toolbarString, String subTitle, String contentToShow)
    {
        SingleTextViewFragment singleTextViewFragment = new SingleTextViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SingleTextViewFragment.TOOLBAR_KEY,toolbarString);
        bundle.putString(SingleTextViewFragment.SUBTITLE_KEY,subTitle);
        bundle.putString(SingleTextViewFragment.CONTENT_KEY,contentToShow);
        singleTextViewFragment.setArguments(bundle);

        //Swapping Single Textview Fragment into the fragment container
        MainActivity.swapFragmentIn(mainActivity,singleTextViewFragment,SingleTextViewFragment.TAG,true);
    }
}
