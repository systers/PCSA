package com.peacecorps.pcsa.circle_of_trust;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.peacecorps.pcsa.R;

/**
 * This adapter is to initialise views of the customised dialog box which gets invoked on pressing Help me button.
 *
 * @author Rohan
 * @since 25-02-2016
 */
public class MessageAdapter extends BaseAdapter {

    public static int[] messages = {R.string.come_get_me, R.string.need_interruption,R.string.need_to_talk};
    Context context;
    private static LayoutInflater inflater;
    private static Dialog listDialog;

    public MessageAdapter(Object object)
    {
        context = (Context)object;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return messages.length;
    }

    @Override
    public Object getItem(int position) {
        return messages[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        rowView = inflater.inflate(R.layout.circle_of_trust_dialog_listitem, null);
        TextView textView = (TextView)rowView.findViewById(R.id.messagedialog_txt);
        textView.setText(messages[position]);
        return rowView;
    }
}
