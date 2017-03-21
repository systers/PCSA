package com.peacecorps.pcsa.support_services;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peacecorps.pcsa.R;

import java.util.HashSet;


/**
 * The customized ArrayAdapter for the list used in FAQ page
 *
 * @author Buddhiprabha Erabadda
 * @since 07-08-2015
 */
public class FAQArrayAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] faqTitles;
    private final String[] faqDescptions;
    private HashSet<Integer> faqVisibilities;

    public FAQArrayAdapter(Context context, String[] faqTitles, String[] faqDesc) {
        super(context, R.layout.faq_layout, faqDesc);
        this.context = context;
        this.faqTitles = faqTitles;
        this.faqDescptions = faqDesc;
        this.faqVisibilities = new HashSet<>();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View faq = inflater.inflate(R.layout.faq_layout, parent, false);

        final TextView faqTitle = (TextView) faq.findViewById(R.id.faqtitle);
        final TextView faqDesc = (TextView) faq.findViewById(R.id.faqdescription);
        final LinearLayout faq_title_and_image = (LinearLayout) faq.findViewById(R.id.faq_title_and_image);
        final TextView viewMoreArrow = (TextView) faq.findViewById(R.id.arrow);

        faqTitle.setText(String.valueOf(faqTitles[position]));
        faqDesc.setText(String.valueOf(faqDescptions[position]));
        if(faqVisibilities.contains(position)) {
            setVisibility(faqDesc, faq_title_and_image, viewMoreArrow, true);
        } else {
            setVisibility(faqDesc, faq_title_and_image, viewMoreArrow, false);
        }

        faq_title_and_image.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (faqDesc.getVisibility() == View.INVISIBLE) {
                    setVisibility(faqDesc, faq_title_and_image, viewMoreArrow, true);
                    faqVisibilities.add(position);
                } else {
                    setVisibility(faqDesc, faq_title_and_image, viewMoreArrow, false);
                    if(faqVisibilities.contains(position)) {
                        faqVisibilities.remove(position);
                    }
                }
            }
        });

        return faq;
    }

    /**
     * Sets the parameters of the View based on whether the description is visible or not
     * @param faqDesc description of the row item
     * @param faq_title_and_image rectangular box around the row item
     * @param viewMoreArrow arrow head
     * @param visibility the visibility of the description
     */
    private void setVisibility(TextView faqDesc, LinearLayout faq_title_and_image, TextView viewMoreArrow, boolean visibility) {
        if(visibility) {
            faqDesc.setVisibility(View.VISIBLE);
            faqDesc.setMaxLines(Integer.MAX_VALUE);
            faq_title_and_image.setBackgroundResource(R.drawable.bg_textview_faq_rounded_upper);
            viewMoreArrow.setRotation(180);
        } else {
            faqDesc.setVisibility(View.INVISIBLE);
            faqDesc.setMaxLines(0);
            faq_title_and_image.setBackgroundResource(R.drawable.bg_textview_rounded_rectangle);
            viewMoreArrow.setRotation(90);
        }
    }
}
