package com.peacecorps.pcsa.circle_of_trust;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.peacecorps.pcsa.R;

import java.util.HashSet;
import java.util.Set;


public class Trustees extends AppCompatActivity {

    public static final int REQUEST_SELECT_CONTACT = 100;
    EditText comrade1editText;
    EditText comrade2editText;
    EditText comrade3editText;
    EditText comrade4editText;
    EditText comrade5editText;
    EditText comrade6editText;
    Button okButton;
    private View selectedButton;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String comrade1 = "comrade1Key";
    public static final String comrade2 = "comrade2Key";
    public static final String comrade3 = "comrade3Key";
    public static final String comrade4 = "comrade4Key";
    public static final String comrade5 = "comrade5Key";
    public static final String comrade6 = "comrade6Key";

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trustees);
        comrade1editText = (EditText) findViewById(R.id.comrade1EditText);
        comrade2editText = (EditText) findViewById(R.id.comrade2EditText);
        comrade3editText = (EditText) findViewById(R.id.comrade3EditText);
        comrade4editText = (EditText) findViewById(R.id.comrade4EditText);
        comrade5editText = (EditText) findViewById(R.id.comrade5EditText);
        comrade6editText = (EditText) findViewById(R.id.comrade6EditText);
        okButton = (Button) findViewById(R.id.okButton);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        comrade1editText.setText(sharedpreferences.getString(comrade1, ""));
        comrade2editText.setText(sharedpreferences.getString(comrade2, ""));
        comrade3editText.setText(sharedpreferences.getString(comrade3, ""));
        comrade4editText.setText(sharedpreferences.getString(comrade4, ""));
        comrade5editText.setText(sharedpreferences.getString(comrade5, ""));
        comrade6editText.setText(sharedpreferences.getString(comrade6, ""));


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString(comrade1, comrade1editText.getText().toString());
                editor.putString(comrade2, comrade2editText.getText().toString());
                editor.putString(comrade3, comrade3editText.getText().toString());
                editor.putString(comrade4, comrade4editText.getText().toString());
                editor.putString(comrade5, comrade5editText.getText().toString());
                editor.putString(comrade6, comrade6editText.getText().toString());

                boolean status = editor.commit();
                if (status) {
                    Toast.makeText(getApplicationContext(), getString(R.string.updated_phone_numbers), Toast.LENGTH_LONG).show();
                    //close activity after save
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.updated_phone_numbers_fail), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addContact(View v) {
        try {
            selectedButton = v;
            Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            startActivityForResult(intent, REQUEST_SELECT_CONTACT);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Finds the appropriate edit text for the given contact pick button
     *
     * @param view Contact pick button
     * @return
     */
    private EditText findInput(View view) {
        if (view != null) {
            switch ((String) view.getTag()) {
                case "1":
                    return comrade1editText;
                case "2":
                    return comrade2editText;
                case "3":
                    return comrade3editText;
                case "4":
                    return comrade4editText;
                case "5":
                    return comrade5editText;
                case "6":
                    return comrade6editText;
            }
        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_SELECT_CONTACT) {
            final EditText phoneInput = findInput(selectedButton);
            if(phoneInput == null){
                return;
            }
            Cursor cursor = null;
            String phoneNumber = "";
            Set<String> allNumbers = new HashSet<>();
            int phoneIdx = 0;
            try {
                Uri result = data.getData();
                String id = result.getLastPathSegment();
                cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[]{id}, null);
                phoneIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA);
                if (cursor.moveToFirst()) {
                    while (cursor.isAfterLast() == false) {
                        phoneNumber = cursor.getString(phoneIdx);
                        allNumbers.add(phoneNumber);
                        cursor.moveToNext();
                    }
                } else {
                    //no results actions
                    showNoPhoneNumberToast();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }

                final CharSequence[] items = allNumbers.toArray(new String[allNumbers.size()]);
                AlertDialog.Builder builder = new AlertDialog.Builder(Trustees.this);
                builder.setTitle(getString(R.string.choose_number));
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        String selectedNumber = items[item].toString();
                        selectedNumber = selectedNumber.replace("-", "");
                        phoneInput.setText(selectedNumber);
                    }
                });
                AlertDialog alert = builder.create();
                if (allNumbers.size() > 1) {
                    alert.show();
                } else {
                    String selectedNumber = phoneNumber.toString();
                    selectedNumber = selectedNumber.replace("-", "");
                    phoneInput.setText(selectedNumber);
                }

                if (phoneNumber.length() == 0) {
                    //no numbers found actions
                    showNoPhoneNumberToast();
                }
            }
        }

    }

    private void showNoPhoneNumberToast() {
        Toast.makeText(Trustees.this, R.string.no_phone_number, Toast.LENGTH_LONG).show();
    }
}
