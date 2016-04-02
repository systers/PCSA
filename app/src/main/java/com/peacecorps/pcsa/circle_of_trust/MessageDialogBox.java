package com.peacecorps.pcsa.circle_of_trust;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.ArrayAdapter;

import com.peacecorps.pcsa.R;
import com.peacecorps.pcsa.circle_of_trust.CircleOfTrustFragment;

/*
 * Dialog Box implementation to handle configuration change (recreation of dialogs)
 * Shows a list of options to the user which serves as a text message template.
 *
 * @author Rohan
 * @since 2016-03-11
 */
public class MessageDialogBox extends DialogFragment {

    private CircleOfTrustFragment objCircleOfTrustFragment;

    //Need a compulsory empty constructor for recreation of dialog while handling config changes
    public MessageDialogBox()
    {

    }
    public MessageDialogBox(CircleOfTrustFragment objCircleOfTrustFragment)
    {
        this.objCircleOfTrustFragment = objCircleOfTrustFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Code to build dialog box has been moved here.
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
                        String optionSelected = arrayAdapter.getItem(which);
                        objCircleOfTrustFragment.sendMessage(optionSelected);
                    }
                });
        AlertDialog alertDialog = builderSingle.create();
        return alertDialog;
    }
}
