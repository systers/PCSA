package com.peacecorps.pcsa.circle_of_trust;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.peacecorps.pcsa.R;

/**
 * List Adapter for showing multiple contact selection dialog
 *
 * @author chamika
 * @since 2016-04-13
 */
public class ContactListAdapter extends ArrayAdapter {

    private static LayoutInflater inflater;

    public ContactListAdapter(Context context, Object[] objects) {
        super(context, R.layout.dialog_list, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        rowView = inflater.inflate(R.layout.contacts_dialog_listitem, null);
        TextView textView = (TextView) rowView.findViewById(R.id.dialog_txt);
        Object item = getItem(position);
        if (item != null) {
            textView.setText(item.toString());
        } else {
            textView.setText("");
        }
        return rowView;
    }
}
