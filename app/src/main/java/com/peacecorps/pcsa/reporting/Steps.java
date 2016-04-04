package com.peacecorps.pcsa.reporting;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.peacecorps.pcsa.R;

/**
 * Steps in Reporting
 *
 * @author Buddhiprabha Erabadda
 * @since 07-08-2015
 */
public class Steps extends Activity {

    TextView reporting_step1;
    TextView reporting_step2;
    TextView reporting_step3;
    TextView reporting_step4;
    TextView reporting_step5;
    TextView reporting_step6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting_steps);

        reporting_step1 = (TextView) findViewById(R.id.reporting_step1);
        reporting_step2 = (TextView) findViewById(R.id.reporting_step2);
        reporting_step3 = (TextView) findViewById(R.id.reporting_step3);
        reporting_step4 = (TextView) findViewById(R.id.reporting_step4);
        reporting_step5 = (TextView) findViewById(R.id.reporting_step5);
        reporting_step6 = (TextView) findViewById(R.id.reporting_step6);

        reporting_step1.setText(Html.fromHtml(getResources().getString(R.string.reporting_step1)));
        reporting_step2.setText(Html.fromHtml(getResources().getString(R.string.reporting_step2)));
        reporting_step3.setText(Html.fromHtml(getResources().getString(R.string.reporting_step3)));
        reporting_step4.setText(Html.fromHtml(getResources().getString(R.string.reporting_step4)));
        reporting_step5.setText(Html.fromHtml(getResources().getString(R.string.reporting_step5)));
        reporting_step6.setText(Html.fromHtml(getResources().getString(R.string.reporting_step6)));

    }

}
