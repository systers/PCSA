package com.peacecorps.pcsa.support_services;

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

import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.hyphen.DefaultHyphenator;
import com.bluejamesbond.text.style.JustifiedSpan;
import com.bluejamesbond.text.style.TextAlignment;
import com.bluejamesbond.text.util.ArticleBuilder;
import com.peacecorps.pcsa.R;

/**
 * Steps in Reporting
 *
 * @author Buddhiprabha Erabadda
 * @since 07-08-2015
 */
public class StepsFragment extends Fragment {

    public final static String TAG = StepsFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reporting_steps, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.after_assault);

        ArticleBuilder articleBuilder1 = new ArticleBuilder();
        articleBuilder1.append(getResources().getString(R.string.reporting_step1),
                true, new RelativeSizeSpan(1f), new JustifiedSpan());
        DocumentView reporting_step1 = addDocumentView(Html.toHtml(articleBuilder1), DocumentView.FORMATTED_TEXT);
        reporting_step1.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        reporting_step1.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.
                getInstance(DefaultHyphenator.HyphenPattern.PT));
        reporting_step1.getDocumentLayoutParams().setHyphenated(true);
        LinearLayout linearLayout1 = (LinearLayout) rootView.findViewById(R.id.reporting_step1);
        linearLayout1.addView(reporting_step1);

        ArticleBuilder articleBuilder2 = new ArticleBuilder();
        articleBuilder2.append(getResources().getString(R.string.reporting_step2),
                true, new RelativeSizeSpan(1f), new JustifiedSpan());
        DocumentView reporting_step2 = addDocumentView(Html.toHtml(articleBuilder2), DocumentView.FORMATTED_TEXT);
        reporting_step2.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        reporting_step2.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.
                getInstance(DefaultHyphenator.HyphenPattern.PT));
        reporting_step2.getDocumentLayoutParams().setHyphenated(true);
        LinearLayout linearLayout2 = (LinearLayout) rootView.findViewById(R.id.reporting_step2);
        linearLayout2.addView(reporting_step2);

        ArticleBuilder articleBuilder3 = new ArticleBuilder();
        articleBuilder3.append(getResources().getString(R.string.reporting_step3),
                true, new RelativeSizeSpan(1f), new JustifiedSpan());
        DocumentView reporting_step3 = addDocumentView(Html.toHtml(articleBuilder3), DocumentView.FORMATTED_TEXT);
        reporting_step3.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        reporting_step3.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.
                getInstance(DefaultHyphenator.HyphenPattern.PT));
        reporting_step3.getDocumentLayoutParams().setHyphenated(true);
        LinearLayout linearLayout3 = (LinearLayout) rootView.findViewById(R.id.reporting_step3);
        linearLayout3.addView(reporting_step3);

        ArticleBuilder articleBuilder4 = new ArticleBuilder();
        articleBuilder4.append(getResources().getString(R.string.reporting_step4),
                true, new RelativeSizeSpan(1f), new JustifiedSpan());
        DocumentView reporting_step4 = addDocumentView(Html.toHtml(articleBuilder4), DocumentView.FORMATTED_TEXT);
        reporting_step4.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        reporting_step4.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.
                getInstance(DefaultHyphenator.HyphenPattern.PT));
        reporting_step4.getDocumentLayoutParams().setHyphenated(true);
        LinearLayout linearLayout4 = (LinearLayout) rootView.findViewById(R.id.reporting_step4);
        linearLayout4.addView(reporting_step4);

        ArticleBuilder articleBuilder5 = new ArticleBuilder();
        articleBuilder5.append(getResources().getString(R.string.reporting_step5),
                true, new RelativeSizeSpan(1f), new JustifiedSpan());
        DocumentView reporting_step5 = addDocumentView(Html.toHtml(articleBuilder5), DocumentView.FORMATTED_TEXT);
        reporting_step5.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        reporting_step5.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.
                getInstance(DefaultHyphenator.HyphenPattern.PT));
        reporting_step5.getDocumentLayoutParams().setHyphenated(true);
        LinearLayout linearLayout5 = (LinearLayout) rootView.findViewById(R.id.reporting_step5);
        linearLayout5.addView(reporting_step5);

        ArticleBuilder articleBuilder6 = new ArticleBuilder();
        articleBuilder6.append(getResources().getString(R.string.reporting_step6),
                true, new RelativeSizeSpan(1f), new JustifiedSpan());
        DocumentView reporting_step6 = addDocumentView(Html.toHtml(articleBuilder6), DocumentView.FORMATTED_TEXT);
        reporting_step6.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        reporting_step6.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.
                getInstance(DefaultHyphenator.HyphenPattern.PT));
        reporting_step6.getDocumentLayoutParams().setHyphenated(true);
        LinearLayout linearLayout6 = (LinearLayout) rootView.findViewById(R.id.reporting_step6);
        linearLayout6.addView(reporting_step6);
        
        return rootView;
    }

    public DocumentView addDocumentView(CharSequence article, int type, boolean rtl) {
        final DocumentView documentView = new DocumentView(getActivity(), type);
        documentView.getDocumentLayoutParams().setTextColor(getResources().getColor(R.color.primary_text_default_material_dark));
        documentView.getDocumentLayoutParams().setTextTypeface(Typeface.DEFAULT);
        documentView.getDocumentLayoutParams().setTextSize(18f);
        documentView.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        documentView.getDocumentLayoutParams().setInsetPaddingLeft(10f);
        documentView.getDocumentLayoutParams().setInsetPaddingRight(10f);
        documentView.getDocumentLayoutParams().setAntialias(true);
        documentView.getDocumentLayoutParams().setInsetPaddingTop(10f);
        documentView.getDocumentLayoutParams().setInsetPaddingBottom(10f);
        documentView.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.
                getInstance(DefaultHyphenator.HyphenPattern.PT));
        documentView.getDocumentLayoutParams().setHyphenated(true);
        documentView.setText(Html.fromHtml(article.toString()));
        return documentView;
    }

    public DocumentView addDocumentView(CharSequence article, int type) {
        return addDocumentView(article, type, false);
    }
}
