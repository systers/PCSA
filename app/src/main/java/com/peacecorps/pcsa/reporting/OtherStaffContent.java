package com.peacecorps.pcsa.reporting;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.peacecorps.pcsa.R;

/**
 * @author Buddhiprabha Erabadda
 *
 * Shows details of the Other Staff members to contact in case of crime
 * Allows user to call/send SMS to Other Staff members
 */
public class OtherStaffContent extends Activity implements AdapterView.OnItemClickListener {

    public static final String CONTACT_NUMBER = "contactNumber";
    public static final String CONTACT_NAME = "contactName";
    public static final String CONTACT_DESC = "contatDesc";
    TextView contactName;
    TextView contactDescription;
    Button contactNow;
    static String contactNumber;
    private Dialog listDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting_other_staff_content);

        contactName = (TextView) findViewById(R.id.reporting_contact_other_content);
        contactDescription = (TextView) findViewById(R.id.reporting_contact_description);
        contactNow = (Button) findViewById(R.id.contact_now);

        final Bundle details = getIntent().getExtras();
        contactNumber = details.getString(CONTACT_NUMBER);

        contactName.setText(details.getString(CONTACT_NAME));
        contactDescription.setText(details.getString(CONTACT_DESC));
        contactNow.setText("Contact Now");

        contactNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog("Contact "+details.getString(CONTACT_NAME) + " via");
            }
        });
    }

    /**
     * Creates a Dialog for the user to choose Dialer app or SMS app
     * @param title title of the dialog box
     */
    private void createDialog(final String title){
        //Initialising the dialog box
        listDialog = new Dialog(this);
        listDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        listDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        //Initialising the list view
        View view = layoutInflater.inflate(R.layout.dialog_list, null);
        listDialog.setContentView(view);
        ListView list1 = (ListView) listDialog.findViewById(R.id.dialog_listview);
        list1.setAdapter(new CustomAdapter(this));

        //Adding the header(title) to the dialogbox
        TextView textView = new TextView(this);
        textView.setText(title);
        textView.setTextColor(getResources().getColor(R.color.primary_text_default_material_dark));
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        textView.setGravity(Gravity.CENTER);
        list1.addHeaderView(textView);

        //Providing functionality to the listitems (Call and Message)
        list1.setOnItemClickListener(this);

        listDialog.show();
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
        listDialog.cancel();
    }
}
