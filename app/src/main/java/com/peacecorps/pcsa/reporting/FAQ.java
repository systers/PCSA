package com.peacecorps.pcsa.reporting;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ScrollView;

import com.peacecorps.pcsa.R;

/**
 * @author Buddhiprabha Erabadda
 *
 * Frequently Asked Questions (FAQ) page implemented as a list
 */
public class FAQ extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        String[] values = new String[] {
                getResources().getString(R.string.reporting_faq1),getResources().getString(R.string.reporting_faq2),
                getResources().getString(R.string.reporting_faq3),getResources().getString(R.string.reporting_faq4),
                getResources().getString(R.string.reporting_faq5),getResources().getString(R.string.reporting_faq6)
        };

        String[] titles = new String[]{
                getResources().getString(R.string.reporting_faq1_header), getResources().getString(R.string.reporting_faq2_header),
                getResources().getString(R.string.reporting_faq3_header), getResources().getString(R.string.reporting_faq4_header),
                getResources().getString(R.string.reporting_faq5_header), getResources().getString(R.string.reporting_faq6_header)
        };

        LayoutInflater layoutInflater = getLayoutInflater();
        ScrollView titleView = (ScrollView) layoutInflater.inflate(R.layout.activity_reporting_faq,null);
        this.getListView().addHeaderView(titleView);

        FAQArrayAdapter faqArrayAdapter = new FAQArrayAdapter(this, titles, values);
        setListAdapter(faqArrayAdapter);

    }
}
