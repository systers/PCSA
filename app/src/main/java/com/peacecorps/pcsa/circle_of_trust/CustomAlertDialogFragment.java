package com.peacecorps.pcsa.circle_of_trust;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.peacecorps.pcsa.R;

/*
 * A template for alert dialogs which display the user about confirmation of message being sent to the comrades
 *
 * @author Rohan
 * @since 2016-03-13
 */
public class CustomAlertDialogFragment extends DialogFragment {

    public static CustomAlertDialogFragment newInstance(String title, String content)
    {
        CustomAlertDialogFragment customAlertDialogFragment = new CustomAlertDialogFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        args.putString("content", content);
        customAlertDialogFragment.setArguments(args);
        return customAlertDialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String title = getArguments().getString("title");
        String content = getArguments().getString("content");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // title bar string
        builder.setTitle(title);
        builder.setPositiveButton(R.string.ok, null);

        builder.setMessage(content);
        AlertDialog errorDialog = builder.create();
        // return the Dialog object
        return errorDialog;
    }
}
