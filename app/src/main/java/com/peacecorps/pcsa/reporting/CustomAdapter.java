package com.peacecorps.pcsa.reporting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.peacecorps.pcsa.R;

/**
 * Created by Rohan on 25-02-2016.
 * Custom Adapter to initialise the ListView inside Dialog Box
 */
public class CustomAdapter extends BaseAdapter {

    String[] result;
    Context context;
    int[] icons;
    private static LayoutInflater inflater;

    public CustomAdapter(Object activity, String[] captions, int[] icons)
    {
        result = captions;
        context = (Context)activity;
        this.icons = icons;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return result.length;
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
        rowView = inflater.inflate(R.layout.dialogbox_listitem, null);
        TextView textView = (TextView)rowView.findViewById(R.id.dialog_text);
        ImageView imageView = (ImageView)rowView.findViewById(R.id.dialog_img);
        textView.setText(result[position]);
        imageView.setImageResource(icons[position]);
        return rowView;
    }
}
