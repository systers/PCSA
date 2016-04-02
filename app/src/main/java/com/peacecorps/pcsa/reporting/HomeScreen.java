package com.peacecorps.pcsa.reporting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.peacecorps.pcsa.R;

/**
 * @author Buddhiprabha Erabadda
 *
 * Landing page of Reporting which has links for Reporting functinalities
 */
public class HomeScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting_homescreen);

        Button reportingSteps = (Button) findViewById(R.id.reporting_steps);
        Button reportingTypes = (Button) findViewById(R.id.reporting_types);
        Button reportingMoreResources = (Button) findViewById(R.id.reporting_resources);
        Button reportingConfidentiality = (Button) findViewById(R.id.reporting_confidentiality);
        Button reportingContactStaff = (Button) findViewById(R.id.reporting_contact);

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
                /* Temporarily commented out until the functionality is implemented
                Intent types = new Intent(HomeScreen.this, Resources.class);
                startActivity(types);
                */

                Toast.makeText(HomeScreen.this, getString(R.string.unavailable_function), Toast.LENGTH_SHORT).show();
            }
        });

        reportingConfidentiality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Temporarily commented out until the functionality is implemented.
                Intent types = new Intent(HomeScreen.this, Confidentiality.class);
                startActivity(types);
                */
                
                Toast.makeText(HomeScreen.this, getString(R.string.unavailable_function), Toast.LENGTH_SHORT).show();
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
