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
import android.widget.Toast;

import com.peacecorps.pcsa.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class CircleOfTrustFragment extends Fragment {
    ImageButton requestButton;
    ImageButton editButton;
    SharedPreferences sharedPreferences;

    private String optionSelected;
    public CircleOfTrustFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_circle_of_trust, container, false);
        requestButton = (ImageButton) rootView.findViewById(R.id.requestButton);
        editButton = (ImageButton) rootView.findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Trustees.class));
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
            case "Come get me":
                message = getString(R.string.come_get_me_message);
                break;
            case "Call I need an interruption":
                message = getString(R.string.interruption_message);
                break;
            case "I need to talk":
                message = getString(R.string.need_to_talk_message);
                break;
        }

        try {
            int totalComrades = 0;
            sharedPreferences = this.getActivity().getSharedPreferences(Trustees.MyPREFERENCES, Context.MODE_PRIVATE);
            // The numbers variable holds the Comrades numbers
            String numbers[] = {sharedPreferences.getString(Trustees.comrade1, ""), sharedPreferences.getString(Trustees.comrade2, ""),
                    sharedPreferences.getString(Trustees.comrade3, ""), sharedPreferences.getString(Trustees.comrade4, ""),
                    sharedPreferences.getString(Trustees.comrade5, ""), sharedPreferences.getString(Trustees.comrade6, ""),};

            for(String number : numbers) {
                if(number != ""){
                    sms.sendTextMessage(number, null, message, null, null);
                    totalComrades++;
                }
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.msg_sent); // title bar string
            builder.setPositiveButton(R.string.ok, null);

            //Can't say that you sent it to 6 people, if you don't have 6 comrades!
            if(totalComrades == 1){
                builder.setMessage("This message has been sent to " + totalComrades + " Comrade");
            }else{
                builder.setMessage("This message has been sent to " + totalComrades + " Comrades");
            }

            AlertDialog errorDialog = builder.create();
            errorDialog.show(); // display the Dialog

        }catch (Exception e)
        {
            Toast.makeText(getActivity(), R.string.message_failed, Toast.LENGTH_LONG).show();
        }

    }
}
