package com.peacecorps.pcsa.circle_of_trust;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.peacecorps.pcsa.MainActivity;
import com.peacecorps.pcsa.R;

/*
  * Activity of loading Circle of Trusts' introductory views
  *
  * @author codingblazer
  * @since 2016-12-10
  */

public class CircleIntroActivity extends AppCompatActivity {
    private ViewPager slideViewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private LinearLayout indicatingDotsContainer;
    private TextView[] dots;
    private int[] introSlides;
    private Button btnSkip,btnDone;
    private ImageButton btnNext;
    public SharedPreferences preferences;
    public boolean isFirstRun;
    View horizontalLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = getSharedPreferences("prefs", 0);
        isFirstRun = preferences.getBoolean("firstRun", true);
        if (!isFirstRun)
            loadMainActivity();
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_circle_intro);
        getWindow().setBackgroundDrawable(null);
        slideViewPager = (ViewPager) findViewById(R.id.view_pager);
        indicatingDotsContainer = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnDone = (Button) findViewById(R.id.btn_done);
        btnNext = (ImageButton) findViewById(R.id.btn_next);
        horizontalLine = findViewById(R.id.horizontal_line);

        introSlides = new int[]{
                R.layout.intro,
                R.layout.intro2,
                R.layout.intro3,
                R.layout.intro4};

        addBottomDots(0); //adds indicating dots to given slide i.e current slide


        viewPagerAdapter = new ViewPagerAdapter();
        slideViewPager.setAdapter(viewPagerAdapter);
        slideViewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMainActivity();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current_slide = getItem();
                if (current_slide < introSlides.length) {
                    slideViewPager.setCurrentItem(current_slide);
                } else {
                    loadMainActivity();
                }
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMainActivity();
            }
        });
    }


    /**
     * Adds the indicating dots at the bottom of given introduction slide.
     * <p>
     * This method dynamically adds circular indicating dots at the bottom.
     * The no. of dots are same as no. of total introduction slides. The
     * dot corresponding to given slide is given i.e active dot is given
     * white color while other dots are given background color.
     *
     * @param  current_slide The index of Introduction slide being currently shown
     * @return
     */
    private void addBottomDots(int current_slide) {
        dots = new TextView[introSlides.length];
        int dot_colorActive = ContextCompat.getColor(getApplicationContext(), R.color.selected_dot);
        int[] dot_colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);
        indicatingDotsContainer.removeAllViews();

        //number of dots added to container equals number of slides
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(dot_colorsInactive[current_slide]);
            indicatingDotsContainer.addView(dots[i]);
        }

        //dot corresponding to current slide is given active color i.e white color
        if (dots.length > 0) {
            dots[current_slide].setTextColor(dot_colorActive);
            horizontalLine.setBackgroundColor(dot_colorsInactive[current_slide]);
        }
    }


    /**
     * Returns the index of next slide to current slide.
     * The index of next slide if exist can be obtained
     * by adding 1 to index of current slide.
     * @return index of next slide in the viewpager
     */
    private int getItem() {
        return slideViewPager.getCurrentItem() + 1;
    }

    /**
     * Loads the Main Circle of Trust Activity and sets the
     * preference firstRun false so that Introduction is not
     * shown in subsequent runs.
     *
     * <p>
     * This method finishes the Introduction Activity and thus the
     * main Circle of Trust Activity loads which was already in backstack.
     *
     * @return
     */
    private void loadMainActivity() {

        preferences = getSharedPreferences("prefs", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("firstRun", false);
        editor.apply();
        Intent intent = new Intent();
        setResult(2, intent);
        finish();//finishing activity
    }

    @Override
    public void onBackPressed() {
        if (isFirstRun){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {


        //invoked when slide is changed
        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            if (position == introSlides.length - 1) {
                btnSkip.setVisibility(View.GONE);
                btnNext.setVisibility(View.GONE);
                btnDone.setVisibility(View.VISIBLE);
            } else {
                btnSkip.setVisibility(View.VISIBLE);
                btnNext.setVisibility(View.VISIBLE);
                btnDone.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    public class ViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(introSlides[position], container, false);
            container.addView(view);

            return view;
        }


        @Override
        public int getCount() {

            return introSlides.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {

            return view == obj;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
