package com.peacecorps.pcsa;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;


import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<SplashScreenActivity> mActivityTestRule = new ActivityTestRule<>(SplashScreenActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.edit_name), isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.edit_name), isDisplayed()));
        appCompatEditText2.perform(replaceText("Rosé"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.edit_name), withText("Rosé"), isDisplayed()));
        appCompatEditText3.perform(pressImeActionButton());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner_country), isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withText("Tunisia"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.loginButton), withText("Login"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.menu_settings), withContentDescription("Settings"), isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(childAtPosition(
                        withId(android.R.id.list),
                        1),
                        isDisplayed()));
        linearLayout.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(android.R.id.text1), withText("Uganda"),
                        childAtPosition(
                                allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                                        withParent(withClassName(is("android.widget.FrameLayout")))),
                                0),
                        isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction imageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        imageButton.perform(click());

        ViewInteraction imageButton2 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        imageButton2.perform(click());

       pressBack();

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.getButton), withText("Get Help now")));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.post_staff_pcmo), withText("Contact PCMO")));
        appCompatButton3.perform(scrollTo(), click());

        pressBack();

        ViewInteraction appCompatImageView = onView(
                withId(R.id.link_to_other_staff));
        appCompatImageView.perform(scrollTo(), click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.ova), withText("Office of Victim Advocacy")));
        appCompatButton4.perform(scrollTo(), click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.contact_now), withText("Contact Now")));
        appCompatButton5.perform(scrollTo(), click());

        pressBack();

        pressBack();

        pressBack();

        pressBack();

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.circleButton), withText("Circle of Trust")));
        appCompatButton6.perform(scrollTo(), click());

        ViewInteraction imageButton4 = onView(
                allOf(withId(R.id.next), isDisplayed()));
        imageButton4.perform(click());

        ViewInteraction imageButton5 = onView(
                allOf(withId(R.id.next), isDisplayed()));
        imageButton5.perform(click());

        ViewInteraction imageButton6 = onView(
                allOf(withId(R.id.next), isDisplayed()));
        imageButton6.perform(click());

        ViewInteraction button = onView(
                allOf(withId(R.id.done), withText("Done"), isDisplayed()));
        button.perform(click());

        ViewInteraction appCompatImageButton = onView(
                withId(R.id.requestButton));
        appCompatImageButton.perform(scrollTo(), click());

        ViewInteraction linearLayout3 = onView(
                allOf(childAtPosition(
                        withId(R.id.dialog_listview),
                        1),
                        isDisplayed()));
        linearLayout3.perform(click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        withParent(allOf(withClassName(is("com.android.internal.widget.ButtonBarLayout")),
                                withParent(withClassName(is("android.widget.LinearLayout"))))),
                        isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                withId(R.id.editButton));
        appCompatImageButton2.perform(scrollTo(), click());

        ViewInteraction appCompatEditText4 = onView(
                withId(R.id.comrade1EditText));
        appCompatEditText4.perform(scrollTo(), click());

        ViewInteraction appCompatEditText5 = onView(
                withId(R.id.comrade1EditText));
        appCompatEditText5.perform(scrollTo(), replaceText("9986180939"), closeSoftKeyboard());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.okButton), withText("Save")));
        appCompatButton8.perform(scrollTo(), click());

        pressBack();

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.safetyToolsButton), withText("Safety Tools")));
        appCompatButton9.perform(scrollTo(), click());

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.radarButton), withText("RADAR")));
        appCompatButton10.perform(scrollTo(), click());

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.next_step),
                        withParent(withId(R.id.radar_controller)),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        ViewInteraction appCompatImageView3 = onView(
                allOf(withId(R.id.next_step),
                        withParent(withId(R.id.radar_controller)),
                        isDisplayed()));
        appCompatImageView3.perform(click());

        ViewInteraction appCompatImageView4 = onView(
                allOf(withId(R.id.next_step),
                        withParent(withId(R.id.radar_controller)),
                        isDisplayed()));
        appCompatImageView4.perform(click());

        ViewInteraction appCompatImageView5 = onView(
                allOf(withId(R.id.next_step),
                        withParent(withId(R.id.radar_controller)),
                        isDisplayed()));
        appCompatImageView5.perform(click());

        ViewInteraction appCompatImageView6 = onView(
                allOf(withId(R.id.prev_step),
                        withParent(withId(R.id.radar_controller)),
                        isDisplayed()));
        appCompatImageView6.perform(click());

        ViewInteraction appCompatImageView7 = onView(
                allOf(withId(R.id.prev_step),
                        withParent(withId(R.id.radar_controller)),
                        isDisplayed()));
        appCompatImageView7.perform(click());

        ViewInteraction appCompatImageView8 = onView(
                allOf(withId(R.id.prev_step),
                        withParent(withId(R.id.radar_controller)),
                        isDisplayed()));
        appCompatImageView8.perform(click());

        ViewInteraction appCompatImageView9 = onView(
                allOf(withId(R.id.prev_step),
                        withParent(withId(R.id.radar_controller)),
                        isDisplayed()));
        appCompatImageView9.perform(click());

        pressBack();

        ViewInteraction appCompatButton11 = onView(
                allOf(withId(R.id.unwantedAttentionButton), withText("Unwanted Attention")));
        appCompatButton11.perform(scrollTo(), click());

        ViewInteraction linearLayout21 = onView(
                allOf(withId(R.id.faq_title_and_image),
                        withParent(childAtPosition(
                                withId(android.R.id.list),
                                0)),
                        isDisplayed()));
        linearLayout21.perform(click());

        ViewInteraction linearLayout31 = onView(
                allOf(withId(R.id.faq_title_and_image),
                        withParent(childAtPosition(
                                withId(android.R.id.list),
                                1)),
                        isDisplayed()));
        linearLayout31.perform(click());

        pressBack();

        ViewInteraction appCompatButton12 = onView(
                allOf(withId(R.id.safetyPlanButton), withText("Safety Plan")));
        appCompatButton12.perform(scrollTo(), click());

        ViewInteraction appCompatButton13 = onView(
                allOf(withId(R.id.actionButton), withText("View Actions"), isDisplayed()));
        appCompatButton13.perform(click());

        ViewInteraction appCompatButton14 = onView(
                allOf(withId(R.id.actionButton), withText("View Concerns"), isDisplayed()));
        appCompatButton14.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withText("Workplace"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withText("Workplace"), isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction imageButton7 = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        imageButton7.perform(click());

        ViewInteraction appCompatButton15 = onView(
                allOf(withId(R.id.assaultAwarenessButton), withText("Sexual Assault Awareness")));
        appCompatButton15.perform(scrollTo(), click());

        ViewInteraction appCompatButton16 = onView(
                allOf(withId(R.id.wasButton), withText("Was it Sexual Assault?")));
        appCompatButton16.perform(scrollTo(), click());

        pressBack();

        pressBack();

        ViewInteraction appCompatButton18 = onView(
                allOf(withId(R.id.supportServicesButton), withText("Support Services")));
        appCompatButton18.perform(scrollTo(), click());

        ViewInteraction appCompatButton19 = onView(
                allOf(withId(R.id.mythButton), withText("Peace Corps Mythbusters")));
        appCompatButton19.perform(scrollTo(), click());

        ViewInteraction linearLayout6 = onView(
                allOf(withId(R.id.faq_title_and_image),
                        withParent(childAtPosition(
                                withId(android.R.id.list),
                                0)),
                        isDisplayed()));
        linearLayout6.perform(click());

        pressBack();

        ViewInteraction appCompatButton20 = onView(
                allOf(withId(R.id.benefitsButton), withText("Benefits of Seeking Staff Support")));
        appCompatButton20.perform(scrollTo(), click());

        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(R.id.layout_content), withText("• So that appropriate and timely medical care can be provided.\n\t\t\t • HIV and hepatitis testing, including HIV PEP\n\t\t\t • STI screening\n\t\t\t • Pregnancy testing and prophylaxis, if applicable \n\n• So the Volunteer gets emotional support and mental health care\n\n• Access to long-term health care through worker’s compensation after COS, if needed.\n\n• To be informed about legal options and assistance.\n\n• Help ensure the safety of the Volunteer and fellow Volunteers.\n\n• Help ensure the safety of future Volunteers."), isDisplayed()));
        appCompatTextView4.perform(click());

        pressBack();
        pressBack();

        ViewInteraction appCompatButton21 = onView(
                allOf(withId(R.id.assaultAwarenessButton), withText("Sexual Assault Awareness")));
        appCompatButton21.perform(scrollTo(), click());

        ViewInteraction appCompatButton22 = onView(
                allOf(withId(R.id.wasButton), withText("Was it Sexual Assault?")));
        appCompatButton22.perform(scrollTo(), click());

        pressBack();

        ViewInteraction appCompatButton23 = onView(
                allOf(withId(R.id.helpingButton), withText("Helping Others")));
        appCompatButton23.perform(scrollTo(), click());

        pressBack();

        pressBack();

        ViewInteraction appCompatButton24 = onView(
                allOf(withId(R.id.policiesButton), withText("Policies and Glossary")));
        appCompatButton24.perform(scrollTo(), click());

        ViewInteraction appCompatButton25 = onView(
                allOf(withId(R.id.policiesButton), withText("Peace Corps Policies")));
        appCompatButton25.perform(scrollTo(), click());

        pressBack();

        ViewInteraction appCompatButton26 = onView(
                allOf(withId(R.id.furtherButton), withText("Further Resources")));
        appCompatButton26.perform(scrollTo(), click());

        ViewInteraction appCompatTextView9 = onView(
                allOf(withId(R.id.resources_content), withText("•\t\t\tMen can stop Rape (http://www.mencanstoprape.org/)\n\n •\t\t\tSafe HelpLine (https://safehelpline.org/): Mobile app and website that allows sexual assault survivors to create customized self-care plans. Intended for active duty military personnel but the self-care portion is applicable to all\n\n •\t\t\tSexual Violence in Lesbian, Gay, Bisexual, Transgender, Intersex, or Queer (LGBTIQ) Communities (http://www.vawnet.org/special-collections/SVLGBTIQ.php#100)\n\n Additional Resources for Unwanted Attention and Harassment: \n\n •\t\t\tStop Street Harassment(http://www.stopstreetharassment.org/) “Assertive Responses” ideas and Tool Kits\n\n •\t\t\tHollaback(http://www.ihollaback.org/about/) An anti-street harassment movement.\n\n •\t\t\tHuffington Post article: “International Anti- Street Harassment week: Ten Things You can do to Stop Street harassment” (http://www.huffingtonpost.com/soraya-chemaly/international-street-harassment-week_b_1228198.html)\n\n •\t\t\tTED talk by Jackson Katz, “Violence Against Women- It’s a Men’s Issue” (http://www.ted.com/talks/jackson_katz_violence_against_women_it_s_a_men_s_issue)"), isDisplayed()));
        appCompatTextView9.perform(click());

        pressBack();

        ViewInteraction appCompatButton27 = onView(
                allOf(withId(R.id.glossaryButton), withText("Glossary")));
        appCompatButton27.perform(scrollTo(), click());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.inputSearch), isDisplayed()));
        appCompatEditText6.perform(replaceText("sexual"), closeSoftKeyboard());

        pressBack();
        pressBack();

        ViewInteraction imageButton9 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        imageButton9.perform(click());

        pressBack();
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
