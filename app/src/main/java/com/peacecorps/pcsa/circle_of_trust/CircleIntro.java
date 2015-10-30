package com.peacecorps.pcsa.circle_of_trust;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.github.paolorotolo.appintro.AppIntro;
import com.peacecorps.pcsa.circle_of_trust.slides.FirstSlide;
import com.peacecorps.pcsa.circle_of_trust.slides.FourthSlide;
import com.peacecorps.pcsa.circle_of_trust.slides.SecondSlide;
import com.peacecorps.pcsa.circle_of_trust.slides.ThirdSlide;


public class CircleIntro extends AppIntro {
    public SharedPreferences settings;
    public boolean firstRun;

    @Override
    public void init(Bundle savedInstanceState) {

        settings = getSharedPreferences("prefs", 0);
        firstRun = settings.getBoolean("firstRun", true);
        if (firstRun) {
            addSlide(new FirstSlide(), getApplicationContext());
            addSlide(new SecondSlide(), getApplicationContext());
            addSlide(new ThirdSlide(), getApplicationContext());
            addSlide(new FourthSlide(), getApplicationContext());

            setFadeAnimation();

        } else{ loadMainActivity();}
    }

    private void loadMainActivity(){

        settings = getSharedPreferences("prefs", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("firstRun", false);
        editor.commit();
        Intent intent = new Intent(this, CircleOfTrust.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSkipPressed() {
        loadMainActivity();
//        Toast.makeText(getApplicationContext(),getString(R.strig.skip),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDonePressed() {
        loadMainActivity();
    }

    public void getStarted(View v){
        loadMainActivity();
    }
}
