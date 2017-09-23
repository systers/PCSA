package com.peacecorps.pcsa.get_help_now;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.TextView;

import com.peacecorps.pcsa.PermissionUtil;
import com.peacecorps.pcsa.R;

import static com.peacecorps.pcsa.Constants.PermissionConstants.PERMISSION_CALLBACK;

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
    public static final String CONTACT_DESC = "contatDesc";
    public static final String TAG = OtherStaffContent.class.getSimpleName();
    static String contactNumber;
    private final String[] runTimePermissions = new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_CONTACTS};
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_reporting_other_staff_content, container, false);

        TextView contactName = (TextView) rootView.findViewById(R.id.reporting_contact_other_content);
        TextView contactDescription = (TextView) rootView.findViewById(R.id.reporting_contact_description);
        Button contactNow = (Button) rootView.findViewById(R.id.contact_now);

        final Bundle details = getArguments();
        contactNumber = details.getString(CONTACT_NUMBER);

        contactName.setText(details.getString(CONTACT_NAME));
        contactDescription.setText(details.getString(CONTACT_DESC));
        contactNow.setText("Contact Now");
        contactNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactOptionsDialogBox contactOptionsDialogBox = ContactOptionsDialogBox.newInstance(getString(R.string.contact) + " " + details.getString(CONTACT_NAME) + " " + getString(R.string.via),
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
