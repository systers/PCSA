package com.peacecorps.pcsa.reporting;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
    Dialog dialog;
    private static int[] icons = {R.mipmap.ic_call,R.mipmap.ic_message};
    private static String[] captions = {"Voice Call","Send message"};

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
                createDialog("Contact " + details.getString(CONTACT_NAME));
            }
        });
    }

    /**
     * Creates a Dialog for the user to choose Dialer app or SMS app
     * @param title the title to be displayed on the Dialog Box
     */
    private void createDialog(final String title){
        //Setting up Dialog Box
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Initialising listview
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.dialogbox_list, null);
        dialog.setContentView(view);
        ListView dialogList = (ListView) dialog.findViewById(R.id.dialog_list);
        dialogList.setAdapter(new CustomAdapter(this, captions, icons));

        //Appending Header
        TextView textView = new TextView(this);
        textView.setText(title + " via");
        textView.setTextColor(getResources().getColor(R.color.primary_text_default_material_dark));
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        textView.setGravity(Gravity.CENTER);
        dialogList.addHeaderView(textView);

        //Providing Behaviour to the List items
        dialogList.setOnItemClickListener(this);
        dialog.show();
    }

    /**
     * Decide whether to make a call or send a message
     * Position is 1 for "Voice Call" and is 2 for "Send Message" as seen in the Dialog Box
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if(position == 1)
        {
            Intent callingIntent = new Intent(Intent.ACTION_CALL);
            callingIntent.setData(Uri.parse("tel:" + contactNumber));
            startActivity(callingIntent);
        }
        else if(position == 2)
        {
            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setData(Uri.parse("sms:"+contactNumber));
            startActivity(smsIntent);
        }
        dialog.cancel();
    }
}
