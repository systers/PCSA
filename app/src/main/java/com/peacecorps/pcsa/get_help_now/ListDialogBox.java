package com.peacecorps.pcsa.get_help_now;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.peacecorps.pcsa.R;

/**
 * Created by Rohan on 13-03-2016.
 */
public abstract class ListDialogBox extends DialogFragment {

    protected Context context;
    protected Dialog listDialog;
    protected AdapterView.OnItemClickListener clickListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setRetainInstance(true);
        String title = getArguments().getString("title");
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
        textView.setText(title);
        textView.setTextColor(context.getResources().getColor(R.color.primary_text_default_material_dark));
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        textView.setGravity(Gravity.CENTER);
        list1.addHeaderView(textView);

        list1.setAdapter(getListAdapter());

        list1.setOnItemClickListener(getItemClickListener());

        return listDialog;
    }

    protected abstract ListAdapter getListAdapter();

    protected abstract AdapterView.OnItemClickListener getItemClickListener();
    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }
}

