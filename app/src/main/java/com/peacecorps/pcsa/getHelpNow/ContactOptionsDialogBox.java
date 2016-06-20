package com.peacecorps.pcsa.getHelpNow;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import com.peacecorps.pcsa.reporting.ListDialogBox;

/**
 * Created by Rohan on 13-03-2016.
 */
public class ContactOptionsDialogBox extends ListDialogBox {

    public static ContactOptionsDialogBox newInstance(String title, Activity activity)
    {
        ContactOptionsDialogBox customAlertDialogFragment = new ContactOptionsDialogBox();
        Bundle args = new Bundle();
        args.putString("title",title);
        customAlertDialogFragment.setArguments(args);
        customAlertDialogFragment.context = activity;
        return customAlertDialogFragment;
    }

    @Override
    protected ListAdapter getListAdapter() {
        return new CustomAdapter(context);
    }

    @Override
    protected AdapterView.OnItemClickListener getItemClickListener() {
        //Providing functionality to the listitems (Call and Message)
        return (AdapterView.OnItemClickListener) context;
    }
}
