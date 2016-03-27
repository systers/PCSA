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
import android.text.Html;
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

        comrade1editText.setText(Html.fromHtml("<font color='black'>" + sharedpreferences.getString(comrade1, "") + "</font>"));
        comrade2editText.setText(Html.fromHtml("<font color='black'>" + sharedpreferences.getString(comrade2, "") + "</font>"));
        comrade3editText.setText(Html.fromHtml("<font color='black'>" + sharedpreferences.getString(comrade3, "") + "</font>"));
        comrade4editText.setText(Html.fromHtml("<font color='black'>" + sharedpreferences.getString(comrade4, "") + "</font>"));
        comrade5editText.setText(Html.fromHtml("<font color='black'>" + sharedpreferences.getString(comrade5, "") + "</font>"));
        comrade6editText.setText(Html.fromHtml("<font color='black'>" + sharedpreferences.getString(comrade6, "") + "</font>"));

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check_duplicate_number = check_duplicate_number();

                //To store previous values (numbers) of comrades
                String old_comrade1, old_comrade2, old_comrade3, old_comrade4, old_comrade5, old_comrade6;

                //To store newly entered values (numbers) of comrades, if any
                String new_comrade1, new_comrade2, new_comrade3, new_comrade4, new_comrade5, new_comrade6;

                //Retrieving stored values
                old_comrade1 = sharedpreferences.getString(comrade1, "");
                old_comrade2 = sharedpreferences.getString(comrade2, "");
                old_comrade3 = sharedpreferences.getString(comrade3, "");
                old_comrade4 = sharedpreferences.getString(comrade4, "");
                old_comrade5 = sharedpreferences.getString(comrade5, "");
                old_comrade6 = sharedpreferences.getString(comrade6, "");

                //Retrieving new values

                new_comrade1 = comrade1editText.getText().toString();
                new_comrade2 = comrade2editText.getText().toString();
                new_comrade3 = comrade3editText.getText().toString();
                new_comrade4 = comrade4editText.getText().toString();
                new_comrade5 = comrade5editText.getText().toString();
                new_comrade6 = comrade6editText.getText().toString();

                if (check_duplicate_number) {
                    editor.putString(comrade1, new_comrade1);
                    editor.putString(comrade2, new_comrade2);
                    editor.putString(comrade3, new_comrade3);
                    editor.putString(comrade4, new_comrade4);
                    editor.putString(comrade5, new_comrade5);
                    editor.putString(comrade6, new_comrade6);

                    boolean status = editor.commit();
                    if (status) {

                        //Check if any updation is required
                        if (old_comrade1.equals(new_comrade1) && old_comrade2.equals(new_comrade2) && old_comrade3.equals(new_comrade3) && old_comrade4.equals(new_comrade4) &&
                                old_comrade5.equals(new_comrade5) && old_comrade6.equals(new_comrade6)) {
                            //Nothing to update
                            Toast.makeText(getApplicationContext(), getString(R.string.not_updated_phone_numbers), Toast.LENGTH_LONG).show();
                        } else {
                            //Need to update
                            Toast.makeText(getApplicationContext(), getString(R.string.updated_phone_numbers), Toast.LENGTH_LONG).show();
                        }


                        //close activity after save
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.updated_phone_numbers_fail), Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.duplicate_number_errormessage), Toast.LENGTH_LONG).show();
                }
            }
        });

        //Function to show cursor on being clicked
        comrade1editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comrade1editText.setCursorVisible(true);
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
            int phoneIdx;
            try {
                Uri result = data.getData();
                String id = result.getLastPathSegment();
                cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[]{id}, null);
                phoneIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA);
                if (cursor.moveToFirst()) {
                    while (!cursor.isAfterLast()) {
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
                    String selectedNumber = phoneNumber;
                    selectedNumber = selectedNumber.replace("-", "");
                     if(check_duplicate_contact_number(selectedNumber)) {
                        phoneInput.setText(selectedNumber);
                    }
                    else
                    {
                      Toast.makeText(getApplicationContext(), getString(R.string.duplicate_number_errormessage), Toast.LENGTH_LONG).show();
                    }

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
    
    private boolean check_duplicate_contact_number(String selectedNumber)
    {
       if(selectedNumber.equals(comrade1editText.getText().toString())
                ||selectedNumber.equals(comrade2editText.getText().toString())
                ||selectedNumber.equals(comrade3editText.getText().toString())
                ||selectedNumber.equals(comrade4editText.getText().toString())
                ||selectedNumber.equals(comrade5editText.getText().toString())
                ||selectedNumber.equals(comrade6editText.getText().toString()))
        {
           return false;
        }
        else
        {
           return true;
        }
    }
    
      private boolean check_duplicate_number() {

        boolean check=true;
        if(!comrade1editText.getText().toString().equals("")) {
            if (comrade1editText.getText().toString().equals(comrade2editText.getText().toString())
                    || comrade1editText.getText().toString().equals(comrade3editText.getText().toString())
                    || comrade1editText.getText().toString().equals(comrade4editText.getText().toString())
                    || comrade1editText.getText().toString().equals(comrade5editText.getText().toString())
                    || comrade1editText.getText().toString().equals(comrade6editText.getText().toString()))

            {
                check = false;
            }
        }
        else if(!comrade2editText.getText().toString().equals("")) {

            if (comrade2editText.getText().toString().equals(comrade3editText.getText().toString())
                    || comrade2editText.getText().toString().equals(comrade4editText.getText().toString())
                    || comrade2editText.getText().toString().equals(comrade5editText.getText().toString())
                    || comrade2editText.getText().toString().equals(comrade6editText.getText().toString()))

            {
                check = false;
            }
        }
        else if(!comrade3editText.getText().toString().equals("")) {
            if (comrade3editText.getText().toString().equals(comrade4editText.getText().toString())
                    || comrade3editText.getText().toString().equals(comrade5editText.getText().toString())
                    || comrade3editText.getText().toString().equals(comrade6editText.getText().toString()))

            {
                check = false;
            }
        }
        else if(!comrade4editText.getText().toString().equals("")) {
            if (comrade4editText.getText().toString().equals(comrade5editText.getText().toString())
                    || comrade4editText.getText().toString().equals(comrade6editText.getText().toString()))

            {
                check = false;
            }
        }
        else if(!comrade5editText.getText().toString().equals("")) {
            if (comrade5editText.getText().toString().equals(comrade6editText.getText().toString())) {
                check = false;
            }
        }
        else
        {
            check = true;
        }
        return check;
        //returns true if no duplicate number else returns false
    }

}
