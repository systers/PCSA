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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;


public class Trustees extends AppCompatActivity {

    public static final int REQUEST_SELECT_CONTACT = 100;
    //EditText comrade1editText;

    @Bind({R.id.comrade1EditText,
            R.id.comrade2EditText,
            R.id.comrade3EditText,
            R.id.comrade4EditText,
            R.id.comrade5EditText,
            R.id.comrade6EditText})
    List<EditText> comradeList;

    @Bind(R.id.okButton)
    Button okButton;

    private View selectedButton;

    public static final String MyPREFERENCES = "MyPrefs" ;

    public static final String[]KEYS_COMRADE = {"comrade1Key",
            "comrade2Key",
            "comrade3Key",
            "comrade4Key",
            "comrade5Key",
            "comrade6Key",};

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trustees);
        ButterKnife.bind(this);


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        editor = sharedpreferences.edit();

        for(int i=0;i<comradeList.size();++i)
            comradeList.get(i).setText(sharedpreferences.getString(KEYS_COMRADE[i], ""));


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check_duplicate_number= check_duplicate_number();

                //To store previous values (numbers) of comrades
                //To store newly entered values (numbers) of comrades, if any
                List<String> oldComradeValue = new ArrayList<>(),newComradeValue = new ArrayList<>();

                //Retrieving stored values
                for (String COMRADE : KEYS_COMRADE)
                    oldComradeValue.add(sharedpreferences.getString(COMRADE, ""));


                //Retrieving new values
                for(EditText editText:comradeList)
                    newComradeValue.add(editText.getText().toString());

                if(check_duplicate_number)
                {
                    for(int i=0;i<KEYS_COMRADE.length;++i)
                        editor.putString(KEYS_COMRADE[i],newComradeValue.get(i));

                    boolean status = editor.commit();
                    if (status) {
                        //Check if any updation is required

                        if(oldComradeValue.containsAll(newComradeValue)&&newComradeValue.containsAll(oldComradeValue)) {
                            //Nothing to update
                            Toast.makeText(getApplicationContext(), getString(R.string.not_updated_phone_numbers), Toast.LENGTH_LONG).show();
                        }
                        else {
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
        if (view != null){
                int tag = Integer.getInteger((String) view.getTag());
            if(tag<=comradeList.size()) {
                return comradeList.get(tag-1);
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
    
      private boolean check_duplicate_number() {

        for(int i = 0;i<comradeList.size();++i){
            for(int j = 0;j<comradeList.size();++j){
                if(j==i || comradeList.get(i).getText().toString().isEmpty()
                        || comradeList.get(j).getText().toString().isEmpty())
                    continue;

                if(comradeList.get(j).getText().toString().equals(comradeList.get(i).getText().toString()))
                    return false;
            }
        }

        return true;
        //returns true if no duplicate number else returns false
    }

}
