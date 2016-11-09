package com.peacecorps.pcsa.support_services;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.peacecorps.pcsa.R;
import com.peacecorps.pcsa.UserSettingsActivity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

/**
 * Frequently Asked Questions (FAQ) page implemented as a list
 *
 * @author Buddhiprabha Erabadda
 * @since 07-08-2015
 */
public class FAQFragment extends AppCompatActivity  {

    private Toolbar toolbar;
    public final static String TAG = FAQFragment.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_coping);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.reporting_types);

        TextView subtitle = (TextView)findViewById(R.id.subtitle);
        ListView listview=(ListView)findViewById(android.R.id.list);
        subtitle.setText(getString(R.string.title_activity_faq));


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


        FAQArrayAdapter faqArrayAdapter = new FAQArrayAdapter(this, titles, values);
        listview.setAdapter(faqArrayAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                this.finish();
                break;
            case R.id.menu_settings:
                Intent intent_settings = new Intent(this, UserSettingsActivity.class);
                startActivity(intent_settings);
                break;
        }
        return true;
    }
}


