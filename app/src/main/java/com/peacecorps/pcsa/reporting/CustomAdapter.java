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
 */
public class CustomAdapter extends BaseAdapter {

    public static String[] result = {"Voice Call","Send message"};
    Context context;
    public static int[] icons = {R.mipmap.ic_call, R.mipmap.ic_message};
    private static LayoutInflater inflater;

    public CustomAdapter(Object object)
    {
        context = (Context)object;
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
        rowView = inflater.inflate(R.layout.dialog_listitem, null);
        TextView textView = (TextView)rowView.findViewById(R.id.dialog_txt);
        ImageView imageView = (ImageView)rowView.findViewById(R.id.dialog_img);
        textView.setText(result[position]);
        imageView.setImageResource(icons[position]);
        return rowView;
    }
}
