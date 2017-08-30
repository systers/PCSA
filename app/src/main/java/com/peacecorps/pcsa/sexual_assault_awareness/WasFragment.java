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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.hyphen.DefaultHyphenator;
import com.bluejamesbond.text.style.JustifiedSpan;
import com.bluejamesbond.text.style.TextAlignment;
import com.bluejamesbond.text.util.ArticleBuilder;
import com.peacecorps.pcsa.JustificationUtil;
import com.peacecorps.pcsa.R;
import com.peacecorps.pcsa.SingleTextViewFragment;

/*
 * Was it Sexual Assault?
 * @author rohan
 * @since 2016-07-24
 */
public class WasFragment extends Fragment {

    public static final String TAG = WasFragment.class.getSimpleName();
    TextView subtitle;
    DocumentView wasContent;
    Button knowButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_was, container, false);
        //wasContent = (DocumentView) rootView.findViewById(R.id.wasContent);
        subtitle = (TextView) rootView.findViewById(R.id.subtitle);
        subtitle.setText(getString(R.string.was_assault));
        knowButton = (Button) rootView.findViewById(R.id.knowButton);
        knowButton.setText(getString(R.string.sexual_assault));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.harassment);

        ArticleBuilder articleBuilder = new ArticleBuilder();
        articleBuilder.append(getString(R.string.was_content), true, new RelativeSizeSpan(1f), new JustifiedSpan());
        JustificationUtil util = new JustificationUtil(getActivity().getApplicationContext());
        wasContent = util.addDocumentView(Html.toHtml(articleBuilder), DocumentView.FORMATTED_TEXT, false,null,
                getActivity());
        wasContent.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        wasContent.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.
                getInstance(DefaultHyphenator.HyphenPattern.PT));
        wasContent.getDocumentLayoutParams().setHyphenated(true);
        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.was_content);
        linearLayout.addView(wasContent);

        knowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Swapping Sexual Assault  info into the fragment container
                SingleTextViewFragment.showSingleTextLayout(getActivity(), getString(R.string.was_assault),
                        getString(R.string.sexual_assault), getString(R.string.sexual_assault_info));
            }
        });
        return rootView;
    }
}
