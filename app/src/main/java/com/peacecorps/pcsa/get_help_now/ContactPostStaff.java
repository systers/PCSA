package com.peacecorps.pcsa.get_help_now;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.peacecorps.pcsa.MainActivity;
import com.peacecorps.pcsa.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Allows the user to call Post Staff in case of crime. The details for the
 * current location will be set by changing the location
 *
 * @author Buddhiprabha Erabadda
 * @since 07-08-2015
 */
public class ContactPostStaff extends Fragment implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    private static final String PREF_LOCATION = "location" ;
    public static final String TAG = "ContactPostStaff" ;

    SharedPreferences sharedPreferences;

    TextView currentLocation;
    LocationDetails selectedLocationDetails;
    private String numberToContact;

    private static final Map<String, LocationDetails> locationDetails;
    static {
        locationDetails = new HashMap<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_reporting_contact_post_staff,container,false);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        locationDetails.put(getResources().getString(R.string.loc1_name), new LocationDetails(getResources().getString(R.string.loc1_name), getResources().getString(R.string.loc1_pcmo), getResources().getString(R.string.loc1_ssm), getResources().getString(R.string.loc1_sarl)));
        locationDetails.put(getResources().getString(R.string.loc2_name), new LocationDetails(getResources().getString(R.string.loc2_name), getResources().getString(R.string.loc2_pcmo), getResources().getString(R.string.loc2_ssm), getResources().getString(R.string.loc2_sarl)));
        locationDetails.put(getResources().getString(R.string.loc3_name), new LocationDetails(getResources().getString(R.string.loc3_name), getResources().getString(R.string.loc3_pcmo), getResources().getString(R.string.loc3_ssm), getResources().getString(R.string.loc3_sarl)));

        Button contactPcmo = (Button) rootView.findViewById(R.id.post_staff_pcmo);
        Button contactSsm = (Button) rootView.findViewById(R.id.post_staff_ssm);
        Button contactSarl = (Button) rootView.findViewById(R.id.post_staff_sarl);
        currentLocation = (TextView) rootView.findViewById(R.id.post_staff_current_location);
        ImageView contactOtherStaff = (ImageView) rootView.findViewById(R.id.link_to_other_staff);

        contactPcmo.setText(R.string.contact_pcmo);
        contactSsm.setText(R.string.contact_ssm);
        contactSarl.setText(R.string.contact_sarl);

        contactPcmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberToContact = selectedLocationDetails.getPcmoContact();
                ContactOptionsDialogBox contactOptionsDialogBox = ContactOptionsDialogBox.newInstance(getString(R.string.contact_pcmo_via),
                        getActivity(),ContactPostStaff.this);
                contactOptionsDialogBox.show(getActivity().getSupportFragmentManager(),getString(R.string.dialog_tag));
            }
        });

        contactSsm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberToContact = selectedLocationDetails.getSsmContact();
                ContactOptionsDialogBox contactOptionsDialogBox = ContactOptionsDialogBox.newInstance(getString(R.string.contact_ssm_via),
                        getActivity(),ContactPostStaff.this);
                contactOptionsDialogBox.show(getActivity().getSupportFragmentManager(), getString(R.string.dialog_tag));
            }
        });

        contactSarl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberToContact = selectedLocationDetails.getSarlContact();
                ContactOptionsDialogBox contactOptionsDialogBox = ContactOptionsDialogBox.newInstance(getString(R.string.contact_sarl_via),
                        getActivity(),ContactPostStaff.this);
                contactOptionsDialogBox.show(getActivity().getSupportFragmentManager(), getString(R.string.dialog_tag));
            }
        });

        Spinner locationList = (Spinner) rootView.findViewById(R.id.reporting_locationlist);
        locationList.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.locations_array,R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationList.setAdapter(adapter);
        //Load Last Location from Shared Preferences
        locationList.setSelection(sharedPreferences.getInt(PREF_LOCATION,0));

        contactOtherStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Swapping ContactOtherStaff into the fragment container dynamically
                Fragment contactOtherStaffFragment = new ContactOtherStaff();
                MainActivity.swapFragmentIn(getActivity(),contactOtherStaffFragment,ContactOtherStaff.TAG);
            }
        });

        return rootView;
    }

    /**
     * Interface definition for a callback to be invoked when an item in Change Location view has been selected
     * and then currentLocation TextView is set according to the selected item.
     *
     * @param parent The AdapterView where the selection happened
     * @param view The view within the AdapterView that was clicked
     * @param position The position of the view in the adapter
     * @param id The row id of the item that is selected
     */
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

    /**
     * Interface definition for a callback to be invoked when no item in Change Location view has been selected
     *
     *  @param parent The AdapterView where the selection happened
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
