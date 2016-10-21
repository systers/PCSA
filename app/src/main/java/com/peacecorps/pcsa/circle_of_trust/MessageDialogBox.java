package com.peacecorps.pcsa.circle_of_trust;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.peacecorps.pcsa.R;

/**
 * Dialog Box implementation to handle configuration change (recreation of dialogs)
 * Shows a list of options to the user which serves as a text message template.
 *
 * @author Rohan
 * @since 2016-03-11
 */
public class MessageDialogBox extends DialogFragment {

    private static CircleOfTrustFragment objCircleOfTrustFragment;
    private static Context context;
    private Dialog listDialog;

    //Need a compulsory empty constructor for recreation of dialog while handling config changes
    public MessageDialogBox()
    {

    }

    /**
     * Sets up the dialogbox
     * @param objCircleOfTrustFragment an object of CircleofTrustFragment
     * @param activity Activity which holds the fragment, required for context
     * @return Dialog object
     */
    public static MessageDialogBox newInstance(CircleOfTrustFragment objCircleOfTrustFragment, Activity activity)
    {
        MessageDialogBox.objCircleOfTrustFragment = objCircleOfTrustFragment;
        context = activity;
        MessageDialogBox messageDialogBox = new MessageDialogBox();
        return messageDialogBox;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Initialising the dialog box
        listDialog = new Dialog(context);
        listDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        listDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //Initialising the listview
        View view = layoutInflater.inflate(R.layout.dialog_list, null);
        listDialog.setContentView(view);
        ListView list1 = (ListView) listDialog.findViewById(R.id.dialog_listview);

        //Adding the header(title) to the dialog box
        TextView textView = new TextView(context);
        textView.setText(getString(R.string.select_request));
        textView.setTextColor(context.getResources().getColor(R.color.primary_text_default_material_dark));
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        textView.setGravity(Gravity.CENTER);
        list1.addHeaderView(textView);

        list1.setAdapter(new MessageAdapter(context));

        //Providing functionality to the listitems (Send the selected message)
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position != 0) {
                    String optionSelected = getString(MessageAdapter.messages[position - 1]);
                    objCircleOfTrustFragment.sendMessage(optionSelected);
                    listDialog.dismiss();
                }
            }
        });

        return listDialog;
    }
}
