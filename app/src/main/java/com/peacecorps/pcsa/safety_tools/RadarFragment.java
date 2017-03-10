package com.peacecorps.pcsa.safety_tools;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.peacecorps.pcsa.MainActivity;
import com.peacecorps.pcsa.R;

/*
 * Radar Fragment, Safety Tools
 *
 * @author rohan
 * @since 2016-07-08
 */
public class RadarFragment extends Fragment {

    public static final int NUM_PAGES = 5;
    public static final String TAG = RadarFragment.class.getSimpleName();
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private ImageView nextStep, prevStep;
    private TextView stepIndicator;
    int[] steps_content = new int[]{R.string.radar_step1,R.string.radar_step2,R.string.radar_step3,R.string.radar_step4,R.string.radar_step5};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_radar,container,false);
        mPager = (ViewPager) rootView.findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlideCustomPagerAdapter(getActivity(),steps_content,NUM_PAGES);
        mPager.setAdapter(mPagerAdapter);

        nextStep = (ImageView)rootView.findViewById(R.id.next_step);
        prevStep = (ImageView)rootView.findViewById(R.id.prev_step);
        final int[] steps = new int[]{R.string.step_1,R.string.step_2,R.string.step_3,R.string.step_4,R.string.step_5};
        stepIndicator = (TextView) rootView.findViewById(R.id.steps_text);
        stepIndicator.setText(Html.fromHtml(getString(steps[0])));
        prevStep.setVisibility(View.INVISIBLE);

        prevStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPager.getCurrentItem() == 1)
                    prevStep.setVisibility(View.INVISIBLE);
                if(nextStep.getVisibility() == View.INVISIBLE)
                    nextStep.setVisibility(View.VISIBLE);
                mPager.setCurrentItem(mPager.getCurrentItem()-1);
                stepIndicator.setText(Html.fromHtml(getString(steps[mPager.getCurrentItem()])));
            }
        });

        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPager.getCurrentItem() == 3)
                    nextStep.setVisibility(View.INVISIBLE);
                if(prevStep.getVisibility() == View.INVISIBLE)
                    prevStep.setVisibility(View.VISIBLE);
                mPager.setCurrentItem(mPager.getCurrentItem()+1);
                stepIndicator.setText(Html.fromHtml(getString(steps[mPager.getCurrentItem()])));
            }
        });

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                stepIndicator.setText(Html.fromHtml(getString(steps[position])));
                if(position == 0)
                    prevStep.setVisibility(View.INVISIBLE);
                else if(position == 4)
                    nextStep.setVisibility(View.INVISIBLE);
                else if(position == 1)
                    prevStep.setVisibility(View.VISIBLE);
                else if(position == 3) {
                    nextStep.setVisibility(View.VISIBLE);
                    prevStep.setVisibility(View.VISIBLE);
                }
                else if(position == 2)
                    prevStep.setVisibility(View.VISIBLE);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.radar);
        return rootView;
    }
}
