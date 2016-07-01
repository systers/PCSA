package com.peacecorps.pcsa.get_help_now;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.peacecorps.pcsa.R;

/**
 * This adapter is to initialise views of the customised dialog box.
 *
 * @author Rohan
 * @since 25-02-2016
 */
public class CustomAdapter extends BaseAdapter {

    public static int[] caption = {R.string.dialog_call, R.string.dialog_message};
    Context context;
    public static int[] icons = {R.drawable.ic_call, R.drawable.ic_message};
    private static LayoutInflater inflater;

    public CustomAdapter(Object object)
    {
        context = (Context)object;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return caption.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView;
        rowView = inflater.inflate(R.layout.reporting_dialog_listitem, null);
        TextView textView = (TextView)rowView.findViewById(R.id.dialog_txt);
        ImageView imageView = (ImageView)rowView.findViewById(R.id.dialog_img);
        textView.setText(caption[position]);
        imageView.setImageResource(icons[position]);
        return rowView;
    }
}
