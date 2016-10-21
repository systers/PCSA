package com.peacecorps.pcsa.safety_tools;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.peacecorps.pcsa.R;

/**
 * Workplace fragment, a member of the Tabbed Layout
 *
 * @author rohan
 * @since 2016-07-08
 */
public class WorkplaceFragment extends Fragment {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    int[] contentToDisplay = new int[]{R.string.safety_plan_3_concerns,R.string.safety_plan_3_action};
    Button actionButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_safety_plan_common, container, false);
        mPager = (ViewPager) rootView.findViewById(R.id.safety_plan_pager);
        actionButton = (Button) rootView.findViewById(R.id.actionButton);
        mPagerAdapter = new ScreenSlideCustomPagerAdapter(getActivity(), contentToDisplay, SafetyPlanActivity.PAGES);
        mPager.setAdapter(mPagerAdapter);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(actionButton.getText().toString().equals(getString(R.string.see_action)))
                {
                    actionButton.setText(Html.fromHtml(getString(R.string.see_concerns)));
                    mPager.setCurrentItem(1);
                }
                else
                {
                    actionButton.setText(Html.fromHtml(getString(R.string.see_action)));
                    mPager.setCurrentItem(0);
                }
            }
        });

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if(position == 1)
                    actionButton.setText(Html.fromHtml(getString(R.string.see_concerns)));
                else
                    actionButton.setText(Html.fromHtml(getString(R.string.see_action)));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return rootView;
    }


}
