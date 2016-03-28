package com.peacecorps.pcsa.reporting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.peacecorps.pcsa.R;

/**
 * @author Buddhiprabha Erabadda
 *
 * Shows details of the Other Staff members to contact in case of crime
 * Allows user to call/send SMS to Other Staff members
 */
public class OtherStaffContent extends FragmentActivity implements AdapterView.OnItemClickListener {

    public static final String CONTACT_NUMBER = "contactNumber";
    public static final String CONTACT_NAME = "contactName";
    public static final String CONTACT_DESC = "contatDesc";
    static String contactNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting_other_staff_content);

        TextView contactName = (TextView) findViewById(R.id.reporting_contact_other_content);
        TextView contactDescription = (TextView) findViewById(R.id.reporting_contact_description);
        Button contactNow = (Button) findViewById(R.id.contact_now);

        final Bundle details = getIntent().getExtras();
        contactNumber = details.getString(CONTACT_NUMBER);

        contactName.setText(details.getString(CONTACT_NAME));
        contactDescription.setText(details.getString(CONTACT_DESC));
        contactNow.setText("Contact Now");
        contactNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactOptionsDialogBox contactOptionsDialogBox = ContactOptionsDialogBox.newInstance(getString(R.string.contact)+" "+details.getString(CONTACT_NAME)+" "+getString(R.string.via),
                        OtherStaffContent.this);
                contactOptionsDialogBox.show(getSupportFragmentManager(), getString(R.string.dialog_tag));
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //For Voice Call
        if(position == 1)
        {
            Intent callingIntent = new Intent(Intent.ACTION_CALL);
            callingIntent.setData(Uri.parse("tel:" + contactNumber));
            startActivity(callingIntent);
        }
        //For Message
        else if(position == 2)
        {
            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setData(Uri.parse("sms:"+contactNumber));
            startActivity(smsIntent);
        }
    }
}
