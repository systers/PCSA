package com.peacecorps.pcsa.reporting;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
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
 * Created by Rohan on 25-02-2016.
 * This adapter is to initialise views of the customised dialog box.
 */
public class CustomAdapter extends BaseAdapter {

    public static String[] caption = {"Voice Call","Send message"};
    Context context;
    public static int[] icons = {R.mipmap.ic_call, R.mipmap.ic_message};
    private static LayoutInflater inflater;

    private static Dialog listDialog;

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
        rowView = inflater.inflate(R.layout.dialog_listitem, null);
        TextView textView = (TextView)rowView.findViewById(R.id.dialog_txt);
        ImageView imageView = (ImageView)rowView.findViewById(R.id.dialog_img);
        textView.setText(caption[position]);
        imageView.setImageResource(icons[position]);
        return rowView;
    }

    /**
     * Creates a Dialog for the user to choose Dialer app or SMS app
     * @param title title of the dialog box
     * @param activity The activity in which the dialog box needs to be instantiated
     *
     * Any activity that needs a dialog box should call this function with the above parameters.
     */
    public static Dialog createDialog(final String title, Activity activity ){

        //Initialising the dialog box
        listDialog = new Dialog(activity);
        listDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        listDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        LayoutInflater layoutInflater = LayoutInflater.from(activity);

        //Initialising the listview
        View view = layoutInflater.inflate(R.layout.dialog_list, null);
        listDialog.setContentView(view);
        ListView list1 = (ListView) listDialog.findViewById(R.id.dialog_listview);
        list1.setAdapter(new CustomAdapter(activity));

        //Adding the header(title) to the dialog box
        TextView textView = new TextView(activity);
        textView.setText(title);
        textView.setTextColor(activity.getResources().getColor(R.color.primary_text_default_material_dark));
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        textView.setGravity(Gravity.CENTER);
        list1.addHeaderView(textView);

        //Providing functionality to the listitems (Call and Message)
        list1.setOnItemClickListener((AdapterView.OnItemClickListener) activity);

        return listDialog;
    }
}
