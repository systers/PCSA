package com.peacecorps.pcsa.circle_of_trust;

import android.content.Context;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import com.peacecorps.pcsa.get_help_now.ListDialogBox;

/*
 * Dialog for showing multiple contact selection dialog
 *
 * @author chamika
 * @since 2016-04-13
 */
public class ContactListDialog extends ListDialogBox {

    private Object[] elements;
    private AdapterView.OnItemClickListener listener;

    public static ContactListDialog newInstance(Context context, String title, Object[] elements) {
        Bundle args = new Bundle();
        args.putString("title", title);
        ContactListDialog fragment = new ContactListDialog();
        fragment.context = context;
        fragment.setArguments(args);
        fragment.elements = elements;
        return fragment;
    }

    @Override
    protected ListAdapter getListAdapter() {
        return new ContactListAdapter(context, elements);
    }

    @Override
    protected AdapterView.OnItemClickListener getItemClickListener() {
        return listener;
    }

    public void setListener(AdapterView.OnItemClickListener listener) {
        this.listener = listener;
    }
}
