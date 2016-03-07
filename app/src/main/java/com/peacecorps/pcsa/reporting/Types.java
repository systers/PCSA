package com.peacecorps.pcsa.reporting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.peacecorps.pcsa.R;

/**
 * @author Buddhiprabha Erabadda
 *
 * Reporting Services available that are available
 * under the reporting types
 */
public class Types extends Activity {

    TextView descBoth;
    TextView descStandard;

    Button faq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting_types);

        descBoth = (TextView) findViewById(R.id.reporting_both);
        descStandard = (TextView) findViewById(R.id.reporting_standard);
        faq = (Button) findViewById(R.id.reporting_faq);

        descBoth.setMovementMethod(new ScrollingMovementMethod());
        descStandard.setMovementMethod(new ScrollingMovementMethod());

        //TODO:Get values from the server
        descBoth.setText(R.string.reporting_desc_standard);
        descStandard.setText(R.string.reporting_desc_restricted);

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent faqPage = new Intent(Types.this, FAQ.class);
                startActivity(faqPage);
            }
        });
    }
}
