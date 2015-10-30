package com.peacecorps.pcsa.reporting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.peacecorps.pcsa.R;

/**
 * @author Buddhiprabha Erabadda
 *
 * Landing page of Reporting which has links for Reporting functinalities
 */
public class HomeScreen extends Activity {

    Button reportingSteps;
    Button reportingTypes;
    Button reportingMoreResources;
    Button reportingConfidentiality;
    Button reportingContactStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting_homescreen);

        reportingSteps = (Button) findViewById(R.id.reporting_steps);
        reportingTypes = (Button) findViewById(R.id.reporting_types);
        reportingMoreResources = (Button) findViewById(R.id.reporting_resources);
        reportingConfidentiality = (Button) findViewById(R.id.reporting_confidentiality);
        reportingContactStaff = (Button) findViewById(R.id.reporting_contact);

        reportingSteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent steps = new Intent(HomeScreen.this, Steps.class);
                startActivity(steps);
            }
        });

        reportingTypes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent types = new Intent(HomeScreen.this, Types.class);
                startActivity(types);
            }
        });

        reportingMoreResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent types = new Intent(HomeScreen.this, Resources.class);
                startActivity(types);
            }
        });

        reportingConfidentiality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent types = new Intent(HomeScreen.this, Confidentiality.class);
                startActivity(types);
            }
        });

        reportingContactStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent types = new Intent(HomeScreen.this, ContactPostStaff.class);
                startActivity(types);
            }
        });
    }
}
