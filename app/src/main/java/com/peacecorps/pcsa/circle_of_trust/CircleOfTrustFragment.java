package com.peacecorps.pcsa.circle_of_trust;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.peacecorps.pcsa.Constants.SmsConstants;
import com.peacecorps.pcsa.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class CircleOfTrustFragment extends Fragment {
    ImageButton requestButton;
    ImageButton editButton;
    SharedPreferences sharedPreferences;

    private TextView comrade1_phno;
    private TextView comrade2_phno;
    private TextView comrade3_phno;
    private TextView comrade4_phno;
    private TextView comrade5_phno;
    private TextView comrade6_phno;

    private String[] numbers;


    private String optionSelected;
    public CircleOfTrustFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_circle_of_trust, container, false);
        comrade1_phno = (TextView) rootView.findViewById(R.id.comrade1_textView);
        comrade2_phno = (TextView) rootView.findViewById(R.id.comrade2_textView);
        comrade3_phno = (TextView) rootView.findViewById(R.id.comrade3_textView);
        comrade4_phno = (TextView) rootView.findViewById(R.id.comrade4_textView);
        comrade5_phno = (TextView) rootView.findViewById(R.id.comrade5_textView);
        comrade6_phno = (TextView) rootView.findViewById(R.id.comrade6_textView);

        sharedPreferences = this.getActivity().getSharedPreferences(Trustees.MyPREFERENCES, Context.MODE_PRIVATE);
        if(sharedPreferences != null)
        {
            // The numbers variable holds the Comrades numbers
            numbers =new String[] {sharedPreferences.getString(Trustees.comrade1, ""), sharedPreferences.getString(Trustees.comrade2, ""),
                    sharedPreferences.getString(Trustees.comrade3, ""), sharedPreferences.getString(Trustees.comrade4, ""),
                    sharedPreferences.getString(Trustees.comrade5, ""), sharedPreferences.getString(Trustees.comrade6, "")};
            {
                if (!numbers[0].equals("")) {
                    comrade1_phno.setVisibility(View.VISIBLE);
                    comrade1_phno.setText(numbers[0]);
                }

                if (!numbers[1].equals("")) {
                    comrade2_phno.setVisibility(View.VISIBLE);
                    comrade2_phno.setText(numbers[1]);
                }

                if (!numbers[2].equals("")) {
                    comrade3_phno.setVisibility(View.VISIBLE);
                    comrade3_phno.setText(numbers[2]);
                }
                if (!numbers[3].equals("")) {
                    comrade4_phno.setVisibility(View.VISIBLE);
                    comrade4_phno.setText(numbers[3]);
                }
                if (!numbers[4].equals("")) {
                    comrade5_phno.setVisibility(View.VISIBLE);
                    comrade5_phno.setText(numbers[4]);
                }
                if (!numbers[5].equals("")) {
                    comrade6_phno.setVisibility(View.VISIBLE);
                    comrade6_phno.setText(numbers[5]);
                }
            }
        }

        requestButton = (ImageButton) rootView.findViewById(R.id.requestButton);
        editButton = (ImageButton) rootView.findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Trustees.class));
                getActivity().finish();
            }
        });
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptions();
            }
        });
        return rootView;
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
            if(sharedPreferences != null)
            {
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
            }
        }catch (Exception e)
        {
            Toast.makeText(getActivity(), R.string.message_failed, Toast.LENGTH_LONG).show();
        }

    }
}
