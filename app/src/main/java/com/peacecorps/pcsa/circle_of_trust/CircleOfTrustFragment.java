package com.peacecorps.pcsa.circle_of_trust;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.peacecorps.pcsa.Constants;
import com.peacecorps.pcsa.Constants.SmsConstants;
import com.peacecorps.pcsa.R;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/*
 * Circle of Trust main fragment
 *
 * @author calistus
 * @since 2015-08-18
 */
public class CircleOfTrustFragment extends Fragment {
    public static final String TAG = CircleOfTrustFragment.class.getSimpleName();
    private static final String MY_PREFERENCES = "MyPreference";
    private static final String NAME_KEY = "ComradeName";
    private static int REQUEST_CODE_TRUSTEES = 1001;
    private long VIBRATION_TIME = 300; // Length of vibration in milliseconds
    private long VIBRATION_PAUSE = 200;
    /**
     * TODO : Add info about vibration pattern in intro activity
     */
    private long[] patternSuccess = {0, // Start immediately
            VIBRATION_TIME
    };

    private long[] patternFailure = {0, // Start immediately
            VIBRATION_TIME, VIBRATION_PAUSE, VIBRATION_TIME, // Each element then alternates between vibrate, sleep, vibrate, sleep...
    };

    ImageView[] comradesViews;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private String[] phoneNumbers;
    LocationHelper locationHelper;

    private Vibrator vibrator;

    public final static String SENT = "300";
    private static boolean firstTime = false;
    private static int msgParts;
    private static List<Boolean> sent = new ArrayList<>();
    ArrayList<PendingIntent> sentIntents = new ArrayList<>();
    private String numbers[];
    public static BroadcastReceiver sentReceiver;
    static Map allNames = new HashMap();
    TextView comrade1Name,comrade2Name,comrade3Name,comrade4Name,comrade5Name,comrade6Name;
    TextView[] allTextViews;

    public CircleOfTrustFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_circle_of_trust, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.circle_title);

        sharedPreferences = getActivity().getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        comrade1Name = (TextView) rootView.findViewById(R.id.com1ButtonName);
        comrade2Name = (TextView) rootView.findViewById(R.id.com2ButtonName);
        comrade3Name = (TextView) rootView.findViewById(R.id.com3ButtonName);
        comrade4Name = (TextView) rootView.findViewById(R.id.com4ButtonName);
        comrade5Name = (TextView) rootView.findViewById(R.id.com5ButtonName);
        comrade6Name = (TextView) rootView.findViewById(R.id.com6ButtonName);
        allTextViews = new TextView[]{comrade1Name,comrade2Name,comrade3Name,comrade4Name,comrade5Name,comrade6Name};

        for(int i = 0; i<allTextViews.length; ++i)
            allTextViews[i].setText(sharedPreferences.getString(NAME_KEY+i,getString(R.string.unregistered)));

        //To verify if SMS is sent
        sentReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(firstTime)
                {
                    firstTime = false;
                    sent.clear();
                }
                boolean anyError = false;
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        anyError = true;
                        break;
                }
                sent.add(anyError);
                msgParts--;
                if (msgParts == 0) {
                    String logMessage = "";
                    for(int i =0; i<sent.size();++i)
                    {
                        if(!numbers[i].isEmpty())
                        {
                            if(!sent.get(i))
                                logMessage += numbers[i] + " : " + getString(R.string.sms_send_pass);
                            else
                                logMessage += numbers[i] + " : " + context.getString(R.string.sms_send_fail);
                            logMessage += "\n";
                        }

                    }
                    sent.clear();
                    //starts the dialog activity which will show the report
                    Intent intent1 = new Intent(context,SMSReportDialogActivity.class);
                    intent1.putExtra(SmsConstants.SMS_REPORT,logMessage);
                    context.startActivity(intent1);

                }
            }
        };
        // Get instance of Vibrator from current Context
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        ImageButton requestButton = (ImageButton) rootView.findViewById(R.id.requestButton);
        ImageButton editButton = (ImageButton) rootView.findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(),Trustees.class),REQUEST_CODE_TRUSTEES);
            }
        });
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkMobileNetworkAvailable(getActivity()))
                {
                    if (vibrator.hasVibrator()) {
                        // Only perform success pattern one time (-1 means "do not repeat")
                        vibrator.vibrate(patternSuccess, -1);
                    }
                    MessageDialogBox messageDialogBox = MessageDialogBox.newInstance(CircleOfTrustFragment.this,getActivity());
                    messageDialogBox.show(getActivity().getSupportFragmentManager(),getString(R.string.message_options));
                }
                else
                {
                    if (vibrator.hasVibrator()) {
                        // Only perform failure pattern one time (-1 means "do not repeat")
                        vibrator.vibrate(patternFailure, -1);
                    }
                    Toast.makeText(getActivity(),R.string.network_unavailable,Toast.LENGTH_LONG).show();
                }

            }
        });
        comradesViews = new ImageView[]{(ImageView) rootView.findViewById(R.id.com1Button),(ImageView) rootView.findViewById(R.id.com2Button),
                (ImageView) rootView.findViewById(R.id.com3Button),(ImageView) rootView.findViewById(R.id.com4Button),
                (ImageView) rootView.findViewById(R.id.com5Button),(ImageView) rootView.findViewById(R.id.com6Button)};
        loadContactPhotos();
        locationHelper = new LocationHelper(getActivity());
        return rootView;
    }

    /**
     * Checks whether the device is connected to a mobile network or not
     * @param appcontext
     * @return true if the device is connected
     */
    public static boolean checkMobileNetworkAvailable(Context appcontext) {
        TelephonyManager tel = (TelephonyManager) appcontext.getSystemService(Context.TELEPHONY_SERVICE);
        return (tel.getNetworkOperator() != null && tel.getNetworkOperator().equals("") ? false : true);
    }

    @Override
    public void onResume() {
        super.onResume();
        locationHelper.startAcquiringLocation();
    }

    @Override
    public void onPause() {
        super.onPause();
        locationHelper.stopAcquiringLocation();
    }

    /**
     * Loads contact photos from the device saved contacts for comrades' numbers
     */
    private void loadContactPhotos() {

        if (phoneNumbers == null) {
            loadPhoneNumbers();
        }
        //reset to defaults
        for(ImageView view:comradesViews)
        {
            view.setImageResource(R.drawable.ic_comrade);
        }

        for (int i = 0; i < phoneNumbers.length; i++) {
            String number = phoneNumbers[i];
            if (number != null && number.length() > 0) {
                ContactPhotoLoader contactPhotoLoader = new ContactPhotoLoader();
                contactPhotoLoader.setContext(this.getActivity());
                ImageView button = null;
                if (comradesViews.length > i) {
                    button = comradesViews[i];
                }

                if (button != null) {
                    contactPhotoLoader.setOutputView(button);
                    contactPhotoLoader.execute(number);
                }
            }
        }
    }

    /**
     * Sends a message to the comrades' phone numbers
     * @param optionSelected selected option
     */
    public void sendMessage(String optionSelected)
    {
        SmsManager sms = SmsManager.getDefault();
        String message = "";
        switch(optionSelected)
        {
            case SmsConstants.COME_GET_ME:
                Location location = locationHelper.retrieveLocation(false);
                if(location == null) {
                    message = getString(R.string.come_get_me_message);
                }else{
                    message = getString(R.string.come_get_me_message_with_location);
                    message = message.replace(Constants.TAG_LOCATION,location.getLatitude() +"," + location.getLongitude());
                    String locationUrl = Constants.LOCATION_URL.replace("LAT" , String.valueOf(location.getLatitude()))
                            .replace("LON" , String.valueOf(location.getLongitude()));
                    message = message.replace(Constants.TAG_LOCATION_URL,locationUrl);
                }
                break;
            case SmsConstants.CALL_NEED_INTERRUPTION:
                message = getString(R.string.interruption_message);
                break;
            case SmsConstants.NEED_TO_TALK:
                message = getString(R.string.need_to_talk_message);
                break;
        }

        sharedPreferences = this.getActivity().getSharedPreferences(Trustees.MY_PREFERENCES, Context.MODE_PRIVATE);

        if(phoneNumbers == null)
        {
            loadPhoneNumbers();
        }
        // The numbers variable holds the Comrades numbers
        numbers = phoneNumbers;

        int counter=0;

        //Fix sending messages if the length is more than single sms limit
        ArrayList<String> parts = sms.divideMessage(message);
        int numParts = parts.size();
        for (int i = 0; i < numParts; i++) {
            sentIntents.add(PendingIntent.getBroadcast(getActivity(), 0, new Intent(
                    SENT), 0));
        }
        int numRegisteredComrades = 0;
        for(String number : numbers) {
            if (!number.isEmpty()) {
                numRegisteredComrades++;
            }
        }
        msgParts = numParts * numRegisteredComrades;
        firstTime = true;
        for(String number : numbers) {
            if (!number.isEmpty()) {
                try{
                    sms.sendMultipartTextMessage(number, null, parts, sentIntents, null);
                }
                catch(Exception e){
                    Toast.makeText(getActivity(), R.string.message_failed + (counter+1), Toast.LENGTH_LONG).show();
                }
                counter++;
            }
        }
        if(counter!=0)
        {
            String contentToPost;

            //For 1 comrade
            if(counter == 1)
                contentToPost = getString(R.string.confirmation_message1)+ " " + counter + " "+ getString(R.string.confirmation_message3) +" " + getString(R.string.receive_log);
            else
                contentToPost = getString(R.string.confirmation_message1)+ " " + counter + " "+ getString(R.string.confirmation_message2)+ " " + getString(R.string.receive_log);
            CustomAlertDialogFragment customAlertDialogFragment = CustomAlertDialogFragment.newInstance(getString(R.string.msg_sent),contentToPost);
            customAlertDialogFragment.show(getActivity().getSupportFragmentManager(),getString(R.string.dialog_tag));
        }
        else
        {
            CustomAlertDialogFragment customAlertDialogFragment = CustomAlertDialogFragment.newInstance(getString(R.string.no_comrade_title),getString(R.string.no_comrade_msg));
            customAlertDialogFragment.show(getActivity().getSupportFragmentManager(),getString(R.string.dialog_tag));
        }
    }

    /**
     * Retrieve phone numbers saved in Trustees
     * @return true if the number retrieval is success
     */
    private boolean loadPhoneNumbers() {
        sharedPreferences = this.getActivity().getSharedPreferences(Trustees.MY_PREFERENCES, Context.MODE_PRIVATE);
        try {

            phoneNumbers = new String[Trustees.NUMBER_OF_COMRADES];
            for(int i = 0; i < Trustees.NUMBER_OF_COMRADES; i++) {
                phoneNumbers[i] = sharedPreferences.getString( Trustees.COMRADE_KEY.get( i ), "" );
            }

            return true;
        } catch (Exception e) {
            Log.e(TAG, "Unable to load comrades numbers from shared preferences", e);
        }

        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_TRUSTEES) {
            refreshPhotos();
            Iterator it = allNames.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                allTextViews[(Integer)pair.getKey() - 1].setText(pair.getValue().toString());
                editor.putString(NAME_KEY + ((Integer)pair.getKey()-1),pair.getValue().toString());
            }

            for(int i = 0; i < Trustees.NUMBER_OF_COMRADES; i++) {
                if(!allNames.containsKey(i+1) && !(phoneNumbers[i].isEmpty())){
                    allTextViews[i].setText(phoneNumbers[i]);
                    editor.putString(NAME_KEY + i,phoneNumbers[i]);
                }
                if(phoneNumbers[i].isEmpty()) {
                    allTextViews[i].setText(getString(R.string.unregistered));
                    editor.putString(NAME_KEY + i,getString(R.string.unregistered));
                }
            }
            editor.commit();
        }
    }

    /**
     * Invalidate current phone numbers and load again with contact photos
     */
    private void refreshPhotos() {
        phoneNumbers = null;
        loadContactPhotos();
    }
}

