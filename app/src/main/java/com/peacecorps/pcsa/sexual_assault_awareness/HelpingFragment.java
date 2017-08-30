package com.peacecorps.pcsa.sexual_assault_awareness;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.peacecorps.pcsa.JustificationUtil;
import com.peacecorps.pcsa.R;

/*
 * Helping Friends and community members
 * @author rohan
 * @since 2016-07-24
 */
public class HelpingFragment extends Fragment {

    public static final String TAG = HelpingFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_reporting_steps, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.helping_others);
        TextView subtitle = (TextView) rootView.findViewById(R.id.reporting_title_steps);
        subtitle.setText(getString(R.string.helping_subtitle));

        JustificationUtil util = new JustificationUtil(getActivity().getApplicationContext());
        ArticleBuilder articleBuilder1 = new ArticleBuilder();
        articleBuilder1.append(getResources().getString(R.string.helping1),
                true, new RelativeSizeSpan(1f), new JustifiedSpan());
        DocumentView reporting_step1 = util.addDocumentView(Html.toHtml(articleBuilder1),
                DocumentView.FORMATTED_TEXT, false,null, getActivity());
        reporting_step1.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        reporting_step1.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.
                getInstance(DefaultHyphenator.HyphenPattern.PT));
        reporting_step1.getDocumentLayoutParams().setHyphenated(true);
        LinearLayout linearLayout1 = (LinearLayout) rootView.findViewById(R.id.reporting_step1);
        linearLayout1.addView(reporting_step1);

        ArticleBuilder articleBuilder2 = new ArticleBuilder();
        articleBuilder2.append(getResources().getString(R.string.helping2),
                true, new RelativeSizeSpan(1f), new JustifiedSpan());
        DocumentView reporting_step2 = util.addDocumentView(Html.toHtml(articleBuilder2), DocumentView.FORMATTED_TEXT, false,
                null,getActivity());
        reporting_step2.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        reporting_step2.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.
                getInstance(DefaultHyphenator.HyphenPattern.PT));
        reporting_step2.getDocumentLayoutParams().setHyphenated(true);
        LinearLayout linearLayout2 = (LinearLayout) rootView.findViewById(R.id.reporting_step2);
        linearLayout2.addView(reporting_step2);

        ArticleBuilder articleBuilder3 = new ArticleBuilder();
        articleBuilder3.append(getResources().getString(R.string.helping3),
                true, new RelativeSizeSpan(1f), new JustifiedSpan());
        DocumentView reporting_step3 = util.addDocumentView(Html.toHtml(articleBuilder3), DocumentView.FORMATTED_TEXT, false,
                null,getActivity());
        reporting_step3.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        reporting_step3.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.
                getInstance(DefaultHyphenator.HyphenPattern.PT));
        reporting_step3.getDocumentLayoutParams().setHyphenated(true);
        LinearLayout linearLayout3 = (LinearLayout) rootView.findViewById(R.id.reporting_step3);
        linearLayout3.addView(reporting_step3);

        ArticleBuilder articleBuilder4 = new ArticleBuilder();
        articleBuilder4.append(getResources().getString(R.string.helping4),
                true, new RelativeSizeSpan(1f), new JustifiedSpan());
        DocumentView reporting_step4 = util.addDocumentView(Html.toHtml(articleBuilder4), DocumentView.FORMATTED_TEXT, false,
                null,getActivity());
        reporting_step4.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        reporting_step4.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.
                getInstance(DefaultHyphenator.HyphenPattern.PT));
        reporting_step4.getDocumentLayoutParams().setHyphenated(true);
        LinearLayout linearLayout4 = (LinearLayout) rootView.findViewById(R.id.reporting_step4);
        linearLayout4.addView(reporting_step4);

        ArticleBuilder articleBuilder5 = new ArticleBuilder();
        articleBuilder5.append(getResources().getString(R.string.helping5),
                true, new RelativeSizeSpan(1f), new JustifiedSpan());
        DocumentView reporting_step5 = util.addDocumentView(Html.toHtml(articleBuilder5), DocumentView.FORMATTED_TEXT, false,
                null,getActivity());
        reporting_step5.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        reporting_step5.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.
                getInstance(DefaultHyphenator.HyphenPattern.PT));
        reporting_step5.getDocumentLayoutParams().setHyphenated(true);
        LinearLayout linearLayout5 = (LinearLayout) rootView.findViewById(R.id.reporting_step5);
        linearLayout5.addView(reporting_step5);

        LinearLayout linearLayout6 = (LinearLayout) rootView.findViewById(R.id.reporting_step6);
        linearLayout6.setVisibility(View.GONE);
        return rootView;
    }
}
