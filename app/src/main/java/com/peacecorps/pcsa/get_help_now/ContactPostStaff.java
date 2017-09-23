package com.peacecorps.pcsa.get_help_now;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.peacecorps.pcsa.MainActivity;
import com.peacecorps.pcsa.PermissionUtil;
import com.peacecorps.pcsa.R;

import java.util.HashMap;
import java.util.Map;

import static com.peacecorps.pcsa.Constants.PermissionConstants.PERMISSION_CALLBACK;

/**
 * Allows the user to call Post Staff in case of crime. The details for the
 * current location will be set by changing the location
 *
 * @author Buddhiprabha Erabadda
 * @since 07-08-2015
 */
public class ContactPostStaff extends Fragment implements AdapterView.OnItemClickListener {

    public static final String TAG = ContactPostStaff.class.getSimpleName();
    private static final Map<String, LocationDetails> locationDetails;

    static {
        locationDetails = new HashMap<>();
    }

    private final String[] runTimePermissions = new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_CONTACTS};
    SharedPreferences sharedPreferences;
    TextView currentLocation;
    LocationDetails selectedLocationDetails;
    private String numberToContact;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_reporting_contact_post_staff, container, false);
        getActivity().getWindow().setBackgroundDrawable(null);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        locationDetails.put(getResources().getString(R.string.loc1_name), new LocationDetails(getResources().getString(R.string.loc1_name), getResources().getString(R.string.loc1_pcmo), getResources().getString(R.string.loc1_ssm), getResources().getString(R.string.loc1_sarl)));
        locationDetails.put(getResources().getString(R.string.loc2_name), new LocationDetails(getResources().getString(R.string.loc2_name), getResources().getString(R.string.loc2_pcmo), getResources().getString(R.string.loc2_ssm), getResources().getString(R.string.loc2_sarl)));
        locationDetails.put(getResources().getString(R.string.loc3_name), new LocationDetails(getResources().getString(R.string.loc3_name), getResources().getString(R.string.loc3_pcmo), getResources().getString(R.string.loc3_ssm), getResources().getString(R.string.loc3_sarl)));

        Button contactPcmo = (Button) rootView.findViewById(R.id.post_staff_pcmo);
        Button contactSsm = (Button) rootView.findViewById(R.id.post_staff_ssm);
        Button contactSarl = (Button) rootView.findViewById(R.id.post_staff_sarl);
        currentLocation = (TextView) rootView.findViewById(R.id.post_staff_current_location);
        currentLocation.setText(getString(R.string.reporting_current_location) + " " + sharedPreferences.getString(getString(R.string.key_country), "") + getString(R.string.reporting_current_post));
        ImageView contactOtherStaff = (ImageView) rootView.findViewById(R.id.link_to_other_staff);

        contactPcmo.setText(R.string.contact_pcmo);
        contactSsm.setText(R.string.contact_ssm);
        contactSarl.setText(R.string.contact_sarl);

        contactPcmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberToContact = selectedLocationDetails.getPcmoContact();
                ContactOptionsDialogBox contactOptionsDialogBox = ContactOptionsDialogBox.newInstance(getString(R.string.contact_pcmo_via),
                        getActivity(), ContactPostStaff.this);
                contactOptionsDialogBox.show(getActivity().getSupportFragmentManager(), getString(R.string.dialog_tag));
            }
        });

        contactSsm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberToContact = selectedLocationDetails.getSsmContact();
                ContactOptionsDialogBox contactOptionsDialogBox = ContactOptionsDialogBox.newInstance(getString(R.string.contact_ssm_via),
                        getActivity(), ContactPostStaff.this);
                contactOptionsDialogBox.show(getActivity().getSupportFragmentManager(), getString(R.string.dialog_tag));
            }
        });

        contactSarl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberToContact = selectedLocationDetails.getSarlContact();
                ContactOptionsDialogBox contactOptionsDialogBox = ContactOptionsDialogBox.newInstance(getString(R.string.contact_sarl_via),
                        getActivity(), ContactPostStaff.this);
                contactOptionsDialogBox.show(getActivity().getSupportFragmentManager(), getString(R.string.dialog_tag));
            }
        });

        contactOtherStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Swapping ContactOtherStaff into the fragment container dynamically
                Fragment contactOtherStaffFragment = new ContactOtherStaff();
                MainActivity.swapFragmentIn(getActivity(), contactOtherStaffFragment, ContactOtherStaff.TAG, true);
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
            callingIntent.setData(Uri.parse("tel:" + numberToContact));
            startActivity(callingIntent);
        }
        //For Message
        else if (position == 2) {
            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setData(Uri.parse("sms:" + numberToContact));
            startActivity(smsIntent);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        selectedLocationDetails = locationDetails.get(sharedPreferences.getString(getString(R.string.key_country), getString(R.string.country_default)));
        if (currentLocation != null)
            currentLocation.setText(getString(R.string.reporting_current_location) + " " + sharedPreferences.getString(getString(R.string.key_country), "") + getString(R.string.reporting_current_post));
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            requestRunTimePermissions();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CALLBACK) {
            boolean showRationale = false;
            for (int i = 0, len = permissions.length; i < len; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    showRationale = showRationale || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permissions[i]);
                }
            }
            if (showRationale) {
                requestPermissions(permissions, PERMISSION_CALLBACK);
            } else if (!PermissionUtil.areAllRunTimePermissionsGranted(permissions, getActivity())) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(false);
                builder.setTitle(getString(R.string.permission_dialog_title));
                builder.setMessage(getString(R.string.permission_dialog_message));
                builder.setPositiveButton(getString(R.string.permission_dialog_positive_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton(getString(R.string.permission_dialog_negative_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        showSnackBar();
                    }
                });
                builder.show();
            }
        }
    }

    /**
     * Method to request permissions at run time.
     */
    private void requestRunTimePermissions() {
        if (!PermissionUtil.areAllRunTimePermissionsGranted(runTimePermissions, getActivity())) {
            requestPermissions(runTimePermissions, PERMISSION_CALLBACK);
        }
    }

    /**
     * Method to show snackbar.
     */
    private void showSnackBar() {
        Snackbar.make(rootView, R.string.permission_toast, Snackbar.LENGTH_LONG)
                .setAction("Open Settings", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    }
                }).show();
    }
}
