package com.peacecorps.pcsa.circle_of_trust;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.peacecorps.pcsa.Constants;
import com.peacecorps.pcsa.Constants.SmsConstants;
import com.peacecorps.pcsa.R;

import java.util.ArrayList;


/*
 * Circle of Trust main fragment
 *
 * @author calistus
 * @since 2015-08-18
 */
public class CircleOfTrustFragment extends Fragment {
    private static final String TAG = CircleOfTrustFragment.class.getSimpleName();
    private static int REQUEST_CODE_TRUSTEES = 1001;

    ImageButton requestButton;
    ImageButton editButton;
    ImageView[] comradesViews;
    SharedPreferences sharedPreferences;

    private String[] phoneNumbers;
    LocationHelper locationHelper;

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
                startActivityForResult(new Intent(getActivity(),Trustees.class),REQUEST_CODE_TRUSTEES);
            }
        });
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkMobileNetworkAvailable(getActivity()))
                {
                    MessageDialogBox messageDialogBox = MessageDialogBox.newInstance(CircleOfTrustFragment.this,getActivity());
                    messageDialogBox.show(getActivity().getSupportFragmentManager(),getString(R.string.message_options));
                }
                else
                {
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
            view.setImageResource(R.mipmap.ic_comrade);
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

        try {
            sharedPreferences = this.getActivity().getSharedPreferences(Trustees.MY_PREFERENCES, Context.MODE_PRIVATE);

            if(phoneNumbers == null)
            {
                loadPhoneNumbers();
            }
            // The numbers variable holds the Comrades numbers
            String numbers[] = phoneNumbers;

            int counter=0;
            for(String number : numbers) {
                if (!number.isEmpty()) {
                    //Fix sending messages if the length is more than single sms limit
                    ArrayList<String> parts = sms.divideMessage(message);
                    sms.sendMultipartTextMessage(number, null, parts, null, null);
                    counter++;
                }
            }
            if(counter!=0)
            {
                String contentToPost;

                //For 1 comrade
                if(counter == 1)
                    contentToPost = getString(R.string.confirmation_message1)+ " " + counter + " "+ getString(R.string.confirmation_message3);
                else
                    contentToPost = getString(R.string.confirmation_message1)+ " " + counter + " "+ getString(R.string.confirmation_message2);
                CustomAlertDialogFragment customAlertDialogFragment = CustomAlertDialogFragment.newInstance(getString(R.string.msg_sent),contentToPost);
                customAlertDialogFragment.show(getActivity().getSupportFragmentManager(),getString(R.string.dialog_tag));
            }
            else
            {
                CustomAlertDialogFragment customAlertDialogFragment = CustomAlertDialogFragment.newInstance(getString(R.string.no_comrade_title),getString(R.string.no_comrade_msg));
                customAlertDialogFragment.show(getActivity().getSupportFragmentManager(),getString(R.string.dialog_tag));
            }

        }catch (Exception e)
        {
            Toast.makeText(getActivity(), R.string.message_failed, Toast.LENGTH_LONG).show();
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
            for(int i = 0; i < Trustees.NUMBER_OF_COMRADES; i++)
                phoneNumbers[i] = Trustees.COMRADE_KEY.get(i);

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

