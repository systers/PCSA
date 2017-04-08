package com.peacecorps.pcsa.get_help_now;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.hyphen.DefaultHyphenator;
import com.bluejamesbond.text.style.JustifiedSpan;
import com.bluejamesbond.text.style.TextAlignment;
import com.bluejamesbond.text.util.ArticleBuilder;
import com.peacecorps.pcsa.JustificationUtil;
import com.peacecorps.pcsa.R;

/**
 * Shows details of the Other Staff members to contact in case of crime
 * Allows user to call/send SMS to Other Staff members
 *
 * @author Buddhiprabha Erabadda
 * @since 07-08-2015
 */
public class OtherStaffContent extends Fragment implements AdapterView.OnItemClickListener {

    public static final String CONTACT_NUMBER = "contactNumber";
    public static final String CONTACT_NAME = "contactName";
    public static final String CONTACT_DESC_PART1 = "contatDescPart1";
    public static final String CONTACT_DESC_PART2 = "contatDescPart2";
    public static final String CONTACT_DESC_PART3 = "contatDescPart3";
    public static final String URL1 = "url1";
    public static final String URL2 = "url2";
    public static final String TAG = OtherStaffContent.class.getSimpleName();
    static String contactNumber;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reporting_other_staff_content, container, false);
        final Bundle details = getArguments();
        JustificationUtil util = new JustificationUtil(getActivity().getApplicationContext());
        TextView contactName = (TextView) rootView.findViewById(R.id.reporting_contact_other_content);

        ArticleBuilder articleBuilder = new ArticleBuilder();
        articleBuilder.append(details.getString(CONTACT_DESC_PART1), true, new RelativeSizeSpan(1f), new JustifiedSpan());
        DocumentView contactDescription = util.addDocumentView(Html.toHtml(articleBuilder),
                DocumentView.FORMATTED_TEXT, true, getActivity());
        contactDescription.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        contactDescription.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.
                getInstance(DefaultHyphenator.HyphenPattern.PT));
        contactDescription.getDocumentLayoutParams().setHyphenated(true);
        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.reporting_contact_description);
        linearLayout.addView(contactDescription);

        ArticleBuilder articleBuilder1 = new ArticleBuilder();
        articleBuilder1.append(details.getString(CONTACT_DESC_PART2), true, new RelativeSizeSpan(1f), new JustifiedSpan());
        DocumentView contactDescription1 = util.addDocumentView(Html.toHtml(articleBuilder1),
                DocumentView.FORMATTED_TEXT, true, getActivity());
        contactDescription1.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        contactDescription1.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.
                getInstance(DefaultHyphenator.HyphenPattern.PT));
        contactDescription1.getDocumentLayoutParams().setHyphenated(true);
        contactDescription1.getViewportView().isClickable();
        linearLayout.addView(contactDescription1);
        contactDescription1.getViewportView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (details.getString(CONTACT_DESC_PART2).substring(0, 1).equals("e")) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/html");
                    intent.putExtra(Intent.EXTRA_EMAIL, details.getString(URL1));
                    startActivity(Intent.createChooser(intent, "Send Email"));
                } else {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("http://" + details.getString(URL1)));
                    startActivity(i);
                }
            }
        });

        //if(!details.getString(CONTACT_DESC_PART3).isEmpty()){
        ArticleBuilder articleBuilder2 = new ArticleBuilder();
        articleBuilder2.append(details.getString(CONTACT_DESC_PART3), true, new RelativeSizeSpan(1f), new JustifiedSpan());
        DocumentView contactDescription2 = util.addDocumentView(Html.toHtml(articleBuilder2),
                DocumentView.FORMATTED_TEXT, true, getActivity());
        contactDescription2.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        contactDescription2.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.
                getInstance(DefaultHyphenator.HyphenPattern.PT));
        contactDescription2.getDocumentLayoutParams().setHyphenated(true);
        linearLayout.addView(contactDescription2);
        contactDescription2.getViewportView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (details.getString(CONTACT_DESC_PART3).substring(0, 1).equals("e")) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/html");
                    intent.putExtra(Intent.EXTRA_EMAIL, details.getString(URL2));
                    startActivity(Intent.createChooser(intent, "Send Email"));
                } else {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("http://" + details.getString(URL2)));
                    startActivity(i);
                }
            }
        });
        // }

        Button contactNow = (Button) rootView.findViewById(R.id.contact_now);

        contactNumber = details.getString(CONTACT_NUMBER);

        contactName.setText(details.getString(CONTACT_NAME));

        contactNow.setText("Contact Now");
        contactNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactOptionsDialogBox contactOptionsDialogBox = ContactOptionsDialogBox.
                        newInstance(getString(R.string.contact) + " " + details.getString(CONTACT_NAME) + " " + getString(R.string.via),
                                getActivity(), OtherStaffContent.this);
                contactOptionsDialogBox.show(getActivity().getSupportFragmentManager(), getString(R.string.dialog_tag));
            }
        });
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.reporting_get_help);
        return rootView;
    }

    /**
     * Interface definition for a callback to be invoked when an item in this AdapterView has been clicked.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //For Voice Call
        if (position == 1) {
            Intent callingIntent = new Intent(Intent.ACTION_CALL);
            callingIntent.setData(Uri.parse("tel:" + contactNumber));
            startActivity(callingIntent);
        }
        //For Message
        else if (position == 2) {
            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setData(Uri.parse("sms:" + contactNumber));
            startActivity(smsIntent);
        }
    }
}
