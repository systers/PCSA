package com.peacecorps.pcsa.circle_of_trust;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Toast;

import com.peacecorps.pcsa.Constants.SmsConstants;
import com.peacecorps.pcsa.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class CircleOfTrustFragment extends Fragment {
    ImageButton requestButton;
    ImageButton editButton;
    ImageButton comrade1,comrade2, comrade3, comrade4, comrade5, comrade6;
    SharedPreferences sharedPreferences;

    public String[] contacts = new String[6];
    private String optionSelected;
    public CircleOfTrustFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_circle_of_trust, container, false);
        requestButton = (ImageButton) rootView.findViewById(R.id.requestButton);
        comrade1= (ImageButton) rootView.findViewById(R.id.com1Button);
        comrade2= (ImageButton) rootView.findViewById(R.id.com2Button);
        comrade3= (ImageButton) rootView.findViewById(R.id.com3Button);
        comrade4= (ImageButton) rootView.findViewById(R.id.com4Button);
        comrade5= (ImageButton) rootView.findViewById(R.id.com5Button);
        comrade6= (ImageButton) rootView.findViewById(R.id.com6Button);
        editButton = (ImageButton) rootView.findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(),Trustees.class),1);
            }
        });

        //Get comrades Numbers via shared Preferences

        sharedPreferences = this.getActivity().getSharedPreferences(Trustees.MyPREFERENCES, Context.MODE_PRIVATE);
        contacts[0] = sharedPreferences.getString(Trustees.comrade1, "");
        contacts[1] = sharedPreferences.getString(Trustees.comrade2, "");
        contacts[2] = sharedPreferences.getString(Trustees.comrade3, "");
        contacts[3] = sharedPreferences.getString(Trustees.comrade4, "");
        contacts[4] = sharedPreferences.getString(Trustees.comrade5, "");
        contacts[5] = sharedPreferences.getString(Trustees.comrade6, "");



        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptions();
            }
        });

        //Setting Click actions for all 6 Comrades
        comrade1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                displayComrade(1,contacts[0]);
            }

        });
        comrade2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                displayComrade(2,contacts[1]);
            }

        });
        comrade3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                displayComrade(3,contacts[2]);
            }

        });
        comrade4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                displayComrade(4,contacts[3]);
            }

        });
        comrade5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                displayComrade(5,contacts[4]);
            }

        });
        comrade6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                displayComrade(6,contacts[5]);
            }

        });
        return rootView;
    }

    //Updating SharedPreferences if they were changed in trustees.java
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            sharedPreferences = this.getActivity().getSharedPreferences(Trustees.MyPREFERENCES, Context.MODE_PRIVATE);
            contacts[0] = sharedPreferences.getString(Trustees.comrade1, "");
            contacts[1] = sharedPreferences.getString(Trustees.comrade2, "");
            contacts[2] = sharedPreferences.getString(Trustees.comrade3, "");
            contacts[3] = sharedPreferences.getString(Trustees.comrade4, "");
            contacts[4] = sharedPreferences.getString(Trustees.comrade5, "");
            contacts[5] = sharedPreferences.getString(Trustees.comrade6, "");

        }
    }

    public void showOptions(){
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(
                getActivity());
        builderSingle.setTitle(R.string.select_request);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add(getString(R.string.come_get_me));
        arrayAdapter.add(getString(R.string.need_interruption));
        arrayAdapter.add(getString(R.string.need_to_talk));
        builderSingle.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


        builderSingle.setAdapter(arrayAdapter,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        optionSelected = arrayAdapter.getItem(which);
                        sendMessage(optionSelected);
                    }
                });
        builderSingle.show();
    }

    public void displayComrade(int i , String s)
    {
        // Display Dialog to show Comrade Details
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(
                getActivity());
        builderSingle.setTitle("Comrade  "+i);
        builderSingle.setIcon(R.mipmap.ic_comrade);
        builderSingle.setMessage("Contact Number : "+ s);
        builderSingle.show();
    }

    public void sendMessage(String optionSelected)
    {
        SmsManager sms = SmsManager.getDefault();
        String message = "";
        switch(optionSelected)
        {
            case SmsConstants.COME_GET_ME:
                message = getString(R.string.come_get_me_message);
                break;
            case SmsConstants.CALL_NEED_INTERRUPTION:
                message = getString(R.string.interruption_message);
                break;
            case SmsConstants.NEED_TO_TALK:
                message = getString(R.string.need_to_talk_message);
                break;
        }

        try {
            sharedPreferences = this.getActivity().getSharedPreferences(Trustees.MyPREFERENCES, Context.MODE_PRIVATE);
            // The numbers variable holds the Comrades numbers
            String numbers[] = {sharedPreferences.getString(Trustees.comrade1, ""), sharedPreferences.getString(Trustees.comrade2, ""),
                    sharedPreferences.getString(Trustees.comrade3, ""), sharedPreferences.getString(Trustees.comrade4, ""),
                    sharedPreferences.getString(Trustees.comrade5, ""), sharedPreferences.getString(Trustees.comrade6, ""),};

            for(String number : numbers) {
                if(number != ""){
                    sms.sendTextMessage(number, null, message, null, null);
                }
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.msg_sent); // title bar string
            builder.setPositiveButton(R.string.ok, null);

            builder.setMessage(getString(R.string.confirmation_message));

            AlertDialog errorDialog = builder.create();
            errorDialog.show(); // display the Dialog

        }catch (Exception e)
        {
            Toast.makeText(getActivity(), R.string.message_failed, Toast.LENGTH_LONG).show();
        }

    }
}
