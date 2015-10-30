package com.peacecorps.pcsa.circle_of_trust;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
        builderSingle.setTitle("Select a request");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Come get me");
        arrayAdapter.add("Call I need an interruption");
        arrayAdapter.add("I need to talk");
        builderSingle.setNegativeButton("cancel",
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
                message = "Come and get me. I need help getting home Safely. Call ASAP to get my Location.Sent through First Aide's Circle of Trust.";
                break;
            case "Call I need an interruption":
                message = "Call and pretend you need me. I need an interruption. Message sent through First Aide's Circle of Trust";
                break;
            case "I need to talk":
                message = "I need to talk. Message sent through First Aide's Circle of Trust";
                break;
        }

        try {
            // The numbers variable holds the Comrades numbers
            String numbers[] = {"+2348103425729", "+2348111875720"};

            for(String number : numbers) {
                sms.sendTextMessage(number, null, message, null, null);
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Message sent"); // title bar string
            builder.setPositiveButton("OK", null);
            builder.setMessage("This message has been sent to 6 Comrades");
            AlertDialog errorDialog = builder.create();
            errorDialog.show(); // display the Dialog

        }catch (Exception e)
        {
            Toast.makeText(getActivity(), "Message Sending Failed, Try again Later", Toast.LENGTH_LONG).show();
        }

    }
}
