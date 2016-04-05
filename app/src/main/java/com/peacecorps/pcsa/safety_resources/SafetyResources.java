package com.peacecorps.pcsa.safety_resources;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.peacecorps.pcsa.R;

/*
 * Activity for Safety Resources fragment container
 *
 * @author calistus
 * @since 2015-08-18
 */
public class SafetyResources extends AppCompatActivity {
    /** 
     * Called when the activity is first created and load the layout specified in the activity_safety_resources.xml
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_resources);
    }

}
