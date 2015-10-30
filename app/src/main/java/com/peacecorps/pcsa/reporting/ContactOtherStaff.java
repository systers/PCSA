package com.peacecorps.pcsa.reporting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.peacecorps.pcsa.R;

/**
 * @author Buddhiprabha Erabadda
 * Allows user to select the Other Staff member to contact
 * Buttons in the screen correspond to various office members
 */
public class ContactOtherStaff extends Activity {

    TextView contactPostStaff;
    Button contactPcSaves;  //Button to access PC Saves Anonymous HelpLine
    Button contactOva;      //Button to access Office of Victim Advocacy
    Button contactOig;      //Button to access Office of Inspector General
    Button contactOcrd;     //Button to access Office of Civil Rights and diversity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting_contact_other_staff);

        contactPcSaves = (Button) findViewById(R.id.pcsaves);
        contactOva = (Button) findViewById(R.id.ova);
        contactOig = (Button) findViewById(R.id.oig);
        contactOcrd = (Button) findViewById(R.id.ocrd);

        contactPostStaff = (TextView) findViewById(R.id.link_to_post_staff);

        contactPcSaves.setText(R.string.contact_pcsaves);
        contactOva.setText(R.string.contact_ova);
        contactOig.setText(R.string.contact_oig);
        contactOcrd.setText(R.string.contact_ocrd);

        contactPcSaves.setOnClickListener(contactOnClickListener);
        contactOva.setOnClickListener(contactOnClickListener);
        contactOig.setOnClickListener(contactOnClickListener);
        contactOcrd.setOnClickListener(contactOnClickListener);

        contactPostStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent postStaff = new Intent(ContactOtherStaff.this, ContactPostStaff.class);
                startActivity(postStaff);
                finish();
            }
        });
    }

    /**
     * the onClickListner for all Other Staff contacts
     */
    private View.OnClickListener contactOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.pcsaves:
                    Intent pcSavesContactDetails = new Intent(ContactOtherStaff.this, OtherStaffContent.class);
                    pcSavesContactDetails.putExtra("contactName", getResources().getString(R.string.contact_pcsaves));
                    pcSavesContactDetails.putExtra("contatDesc", getResources().getString(R.string.reporting_desc_pcsaves));
                    pcSavesContactDetails.putExtra("contactNumber", getResources().getString(R.string.reporting_contact_pcsaves));
                    startActivity(pcSavesContactDetails);
                    break;
                case R.id.ova:
                    Intent ovaContactDetails = new Intent(ContactOtherStaff.this, OtherStaffContent.class);
                    ovaContactDetails.putExtra("contactName", getResources().getString(R.string.contact_ova));
                    ovaContactDetails.putExtra("contatDesc", getResources().getString(R.string.reporting_desc_ova));
                    ovaContactDetails.putExtra("contactNumber", getResources().getString(R.string.reporting_contact_ova));
                    startActivity(ovaContactDetails);
                    break;
                case R.id.oig:
                    Intent oigContactDetails = new Intent(ContactOtherStaff.this, OtherStaffContent.class);
                    oigContactDetails.putExtra("contactName", getResources().getString(R.string.contact_oig));
                    oigContactDetails.putExtra("contatDesc", getResources().getString(R.string.reporting_desc_oig));
                    oigContactDetails.putExtra("contactNumber", getResources().getString(R.string.reporting_contact_oig));
                    startActivity(oigContactDetails);
                    break;
                case R.id.ocrd:
                    Intent ocrdContactDetails = new Intent(ContactOtherStaff.this, OtherStaffContent.class);
                    ocrdContactDetails.putExtra("contactName", getResources().getString(R.string.contact_ocrd));
                    ocrdContactDetails.putExtra("contatDesc", getResources().getString(R.string.reporting_desc_ocrd));
                    ocrdContactDetails.putExtra("contactNumber", getResources().getString(R.string.reporting_contact_ocrd));
                    startActivity(ocrdContactDetails);
                    break;
            }
        }
    };
}
