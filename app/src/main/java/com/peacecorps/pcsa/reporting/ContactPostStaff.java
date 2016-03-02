package com.peacecorps.pcsa.reporting;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.peacecorps.pcsa.R;
import com.peacecorps.pcsa.reporting.LocationDetails;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Buddhiprabha Erabadda
 *
 * Allows the user to call Post Staff in case of crime. The details for the
 * current location will be set by changing the location
 */
public class ContactPostStaff extends Activity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    private static final String PREF_LOCATION = "location" ;

    SharedPreferences sharedPreferences;

    Button contactPcmo;
    Button contactSsm;
    Button contactSarl;
    TextView currentLocation;
    TextView contactOtherStaff;
    private String numberToContact;

    LocationDetails selectedLocationDetails;

    private static final Map<String, LocationDetails> locationDetails;
    static {
        locationDetails = new HashMap<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting_contact_post_staff);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        locationDetails.put(getResources().getString(R.string.loc1_name), new LocationDetails(getResources().getString(R.string.loc1_name), getResources().getString(R.string.loc1_pcmo), getResources().getString(R.string.loc1_ssm), getResources().getString(R.string.loc1_sarl)));
        locationDetails.put(getResources().getString(R.string.loc2_name), new LocationDetails(getResources().getString(R.string.loc2_name), getResources().getString(R.string.loc2_pcmo), getResources().getString(R.string.loc2_ssm), getResources().getString(R.string.loc2_sarl)));
        locationDetails.put(getResources().getString(R.string.loc3_name), new LocationDetails(getResources().getString(R.string.loc3_name), getResources().getString(R.string.loc3_pcmo), getResources().getString(R.string.loc3_ssm), getResources().getString(R.string.loc3_sarl)));

        contactPcmo = (Button) findViewById(R.id.post_staff_pcmo);
        contactSsm = (Button) findViewById(R.id.post_staff_ssm);
        contactSarl = (Button) findViewById(R.id.post_staff_sarl);
        currentLocation = (TextView) findViewById(R.id.post_staff_current_location);
        contactOtherStaff = (TextView) findViewById(R.id.link_to_other_staff);

        contactPcmo.setText(R.string.contact_pcmo);
        contactSsm.setText(R.string.contact_ssm);
        contactSarl.setText(R.string.contact_sarl);

        contactPcmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberToContact = selectedLocationDetails.getPcmoContact();
                CustomAdapter.createDialog(getString(R.string.contact_pcmo_via), ContactPostStaff.this).show();
            }
        });

        contactSsm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberToContact = selectedLocationDetails.getSsmContact();
                CustomAdapter.createDialog(getString(R.string.contact_ssm_via)+"via", ContactPostStaff.this).show();
            }
        });

        contactSarl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberToContact = selectedLocationDetails.getSarlContact();
                CustomAdapter.createDialog(getString(R.string.contact_sarl_via), ContactPostStaff.this).show();
            }
        });

        Spinner locationList = (Spinner) findViewById(R.id.reporting_locationlist);
        locationList.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.locations_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationList.setAdapter(adapter);
        //Load Last Location from Shared Preferences
        locationList.setSelection(sharedPreferences.getInt(PREF_LOCATION, 0));

        contactOtherStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherStaff = new Intent(ContactPostStaff.this, ContactOtherStaff.class);
                startActivity(otherStaff);
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = (String) parent.getItemAtPosition(position);
        currentLocation.setText(getResources().getString(R.string.reporting_current_location) + " " + selectedItem);

        // selectedLocationDetails holds all details about the location selected by the user
        selectedLocationDetails = locationDetails.get(selectedItem);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        //PREF_LOCATION field stores the index of Location in the list
        editor.putInt(PREF_LOCATION, position);
        editor.commit();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //For Voice Call
        if(position == 1)
        {
            Intent callingIntent = new Intent(Intent.ACTION_CALL);
            callingIntent.setData(Uri.parse("tel:" + numberToContact));
            startActivity(callingIntent);
        }
        //For Message
        else if(position == 2) {
            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setData(Uri.parse("sms:" + numberToContact));
            startActivity(smsIntent);
        }
    }
}
