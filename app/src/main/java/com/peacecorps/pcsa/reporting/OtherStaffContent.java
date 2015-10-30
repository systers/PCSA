package com.peacecorps.pcsa.reporting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.peacecorps.pcsa.R;

/**
 * @author Buddhiprabha Erabadda
 *
 * Shows details of the Other Staff members to contact in case of crime
 * Allows user to call/send SMS to Other Staff members
 */
public class OtherStaffContent extends Activity {

    TextView contactName;
    TextView contactDescription;
    Button contactNow;
    static String contactNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting_other_staff_content);

        contactName = (TextView) findViewById(R.id.reporting_contact_other_content);
        contactDescription = (TextView) findViewById(R.id.reporting_contact_description);
        contactNow = (Button) findViewById(R.id.contact_now);

        Bundle details = getIntent().getExtras();
        contactNumber = details.getString("contactNumber");

        contactName.setText(details.getString("contactName"));
        contactDescription.setText(details.getString("contatDesc"));
        contactNow.setText("Contact Now");

        contactNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(v, contactNumber);
            }
        });
    }

    /**
     * Creates a Dialog for the user to choose Dialer app or SMS app
     * @param v the view clicked
     * @param numberToContact contact number corresponding to the view
     */
    private void createDialog(View v, final String numberToContact){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int optionSelected) {
                contactStaff(optionSelected, numberToContact);
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(v.getContext(), R.style.pcsaAlertDialogStyle));
        builder.setMessage("Contact Via").setPositiveButton("Voice Call", dialogClickListener)
                .setNegativeButton("Send Message", dialogClickListener).show();
    }

    /**
     * Opens Dialer or SMS
     * @param action which app to open
     * @param contactNumber the contact number of the selected person
     */
    private void contactStaff(int action, String contactNumber){
        switch (action){
            case DialogInterface.BUTTON_POSITIVE:
                Intent callingIntent = new Intent(Intent.ACTION_CALL);
                callingIntent.setData(Uri.parse("tel:" + contactNumber));
                startActivity(callingIntent);
                break;

            case DialogInterface.BUTTON_NEGATIVE:
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setData(Uri.parse("sms:"+contactNumber));
                startActivity(smsIntent);
        }
    }
}
