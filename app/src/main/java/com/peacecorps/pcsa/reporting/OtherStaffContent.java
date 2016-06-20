package com.peacecorps.pcsa.reporting;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.peacecorps.pcsa.R;
import com.peacecorps.pcsa.getHelpNow.ContactOptionsDialogBox;

/**
 * Shows details of the Other Staff members to contact in case of crime
 * Allows user to call/send SMS to Other Staff members
 *
 * @author Buddhiprabha Erabadda
 * @since 07-08-2015
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

    /**
     * Interface definition for a callback to be invoked when an item in this AdapterView has been clicked.
     *
     * @param parent The AdapterView where the click happened.
     * @param view  The view within the AdapterView that was clicked (this will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id The row id of the item that was clicked.
     */
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
