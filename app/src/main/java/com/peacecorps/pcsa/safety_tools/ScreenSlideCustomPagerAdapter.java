package com.peacecorps.pcsa.safety_tools;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
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
 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
 * sequence.
 *
 * @author rohan
 * @since 08-07-2016.
 */
public class ScreenSlideCustomPagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    int no_of_pages;
    private int[] steps;

    public ScreenSlideCustomPagerAdapter(Context context, int[] steps, int no_of_pages) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.steps = steps;
        this.no_of_pages = no_of_pages;
    }

    @Override
    public int getCount() {
        return no_of_pages;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.fragment_viewpager, container, false);

        ArticleBuilder articleBuilder = new ArticleBuilder();
        articleBuilder.append(mContext.getString(steps[position]),
                true, new RelativeSizeSpan(1f), new JustifiedSpan());
        DocumentView textView = addDocumentView(Html.toHtml(articleBuilder), DocumentView.FORMATTED_TEXT);
        textView.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        textView.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.
                getInstance(DefaultHyphenator.HyphenPattern.PT));
        textView.getDocumentLayoutParams().setHyphenated(true);
        LinearLayout cAssault = (LinearLayout) itemView.findViewById(R.id.text_to_show);
        cAssault.addView(textView);

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public DocumentView addDocumentView(CharSequence article, int type, boolean rtl) {
        final DocumentView documentView = new DocumentView(mContext, type);
        documentView.getDocumentLayoutParams().setTextColor(mContext.getResources().getColor(R.color.primary_text_default_material_dark));
        documentView.getDocumentLayoutParams().setTextTypeface(Typeface.DEFAULT);
        documentView.getDocumentLayoutParams().setTextSize(18f);
        documentView.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        documentView.getDocumentLayoutParams().setInsetPaddingLeft(10);
        documentView.getDocumentLayoutParams().setInsetPaddingRight(10);
        documentView.getDocumentLayoutParams().setAntialias(true);
        documentView.getDocumentLayoutParams().setInsetPaddingTop(10);
        documentView.getDocumentLayoutParams().setInsetPaddingBottom(10);
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