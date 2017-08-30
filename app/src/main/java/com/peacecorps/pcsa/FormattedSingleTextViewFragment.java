package com.peacecorps.pcsa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.hyphen.DefaultHyphenator;
import com.bluejamesbond.text.style.JustifiedSpan;
import com.bluejamesbond.text.style.TextAlignment;
import com.bluejamesbond.text.util.ArticleBuilder;

/**
 * Created by ashu on 5/2/17.
 */

public class FormattedSingleTextViewFragment extends Fragment {
    public static final String TAG = FormattedSingleTextViewFragment.class.getSimpleName();
    public static final String TOOLBAR_KEY = "TOOLBAR";
    public static final String CONTENT_KEY = "CONTENT";
    public static final String SUBTITLE_KEY = "SUBTITLE";
    TextView subTitle;
    DocumentView content;
    String toolbarTitle, subtitle, contentString;

    /**
     * Populates the required data for the layout which appears
     *
     * @param subTitle      subtitle of the layout
     * @param contentToShow data to be displayed
     * @param toolbarString displayed on the toolbar
     */
    public static void showSingleTextLayout(FragmentActivity mainActivity, String toolbarString, String subTitle, String contentToShow) {
        FormattedSingleTextViewFragment singleTextViewFragment = new FormattedSingleTextViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SingleTextViewFragment.TOOLBAR_KEY, toolbarString);
        bundle.putString(SingleTextViewFragment.SUBTITLE_KEY, subTitle);
        bundle.putString(SingleTextViewFragment.CONTENT_KEY, contentToShow);
        singleTextViewFragment.setArguments(bundle);

        //Swapping Single Textview Fragment into the fragment container
        MainActivity.swapFragmentIn(mainActivity, singleTextViewFragment, FormattedSingleTextViewFragment.TAG, true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_formatted_single_textview, container, false);
        subTitle = (TextView) rootView.findViewById(R.id.layout_subtitle);
        //content = (DocumentView) rootView.findViewById(R.id.layout_content);
        JustificationUtil util = new JustificationUtil(getActivity().getApplicationContext());
        toolbarTitle = getArguments().getString(TOOLBAR_KEY);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(toolbarTitle);
        subtitle = getArguments().getString(SUBTITLE_KEY);
        subTitle.setText(subtitle);
        contentString = getArguments().getString(CONTENT_KEY);

        ArticleBuilder articleBuilder = new ArticleBuilder();
        articleBuilder.append(contentString, true, new RelativeSizeSpan(1f), new JustifiedSpan());
        content = util.addDocumentView(Html.toHtml(articleBuilder), DocumentView.FORMATTED_TEXT, false,null,
                getActivity());
        content.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        content.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.
                getInstance(DefaultHyphenator.HyphenPattern.PT));
        content.getDocumentLayoutParams().setHyphenated(true);
        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.layout_content);
        linearLayout.addView(content);
        return rootView;
    }
}
