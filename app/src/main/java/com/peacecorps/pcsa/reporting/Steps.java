package com.peacecorps.pcsa.reporting;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.peacecorps.pcsa.R;

/**
 * @author Buddhiprabha Erabadda
 *
 * Steps in Reporting
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_steps, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
