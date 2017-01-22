package com.peacecorps.pcsa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


/**
 * This class handles showing splash screen for 2500 ms when the application is launched
 * @author rohan
 * @since 2016-06-10.
 */
public class SplashScreenActivity extends Activity {

    private static int SPLASH_TIME_OUT = 2500;

    /**
     * Instance of Handler class is used to load the main screen after 2500 ms
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, SignupActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
