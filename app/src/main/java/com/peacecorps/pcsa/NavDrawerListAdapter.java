package com.peacecorps.pcsa;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This class is the adapter for the Expandable list view which has been defined in the Navigation Drawer
 * @author rohan
 * @since 11-06-2016.
 */
public class NavDrawerListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> dataHeader;
    private HashMap<String, List<String>> dataChild;

    public NavDrawerListAdapter(Context _context, List<String> dataHeader, HashMap<String, List<String>> dataChild) {
        this._context = _context;
        this.dataHeader = dataHeader;
        this.dataChild = dataChild;
    }

    /**
     * @return  Size of the dataheader list passed in the constructor
     */
    @Override
    public int getGroupCount() {
        return this.dataHeader.size();
    }

    /**
     * @param groupPosition Position of the group in the list
     * @return Size of the group whose position was mentioned as the parameter
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return this.dataChild.get(this.dataHeader.get(groupPosition))
                .size();
    }

    /**
     * @param groupPosition Position of group in the list
     * @return Object related to the position received as parameter
     */
    @Override
    public Object getGroup(int groupPosition) {
        return this.dataHeader.get(groupPosition);
    }

    /**
     * @param groupPosition Position of group in the list
     * @param childPosition Position of child in the group
     * @return Object related to the child in the group whose positions are received as parameters
    */
     @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.dataChild.get(this.dataHeader.get(groupPosition)).get(childPosition);
    }

    /**
     * @param groupPosition Position of group in the list
     * @return Group id related to the group position received as parameter
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     *
     * @param groupPosition Position of group in the list
     * @param childPosition Position of child in the group
     * @return Child id related to group and child positions received as parameter
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * @return false when called to indicate id may or may not refer to same object every time
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * @param groupPosition index of the group Title in the list which stores header titles
     * @param isExpanded true, if a particular group is expanded
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.navbar_list_header, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.header_text);
        ImageView iconListHeader = (ImageView) convertView
                .findViewById(R.id.navbar_header_icon);
        iconListHeader.setVisibility(View.VISIBLE);
        lblListHeader.setText(headerTitle);
        if(headerTitle.equals(_context.getString(R.string.settings)))
            iconListHeader.setImageResource(R.drawable.ic_settings);
        else if(headerTitle.equals(_context.getString(R.string.user_login)))
            iconListHeader.setImageResource(R.drawable.ic_lock);
        else if(headerTitle.equals(_context.getString(R.string.get_help)) || headerTitle.equals(_context.getString(R.string.circle_title)) || headerTitle.startsWith(_context.getString(R.string.user_login)))
            iconListHeader.setVisibility(View.INVISIBLE);
        else
        {
            if(isExpanded)
                iconListHeader.setImageResource(R.drawable.ic_down_arrow);
            else
                iconListHeader.setImageResource(R.drawable.ic_right_arrow);
        }
        return convertView;
    }

    /**
     * @param groupPosition index of the group's Title in the list which stores header titles
     * @param childPosition index of the child's Title in the list which stores all the children of a particular group whose title is the key in the
     *                      map.
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.navbar_list_item, null);
        }
        TextView txtListChild = (TextView) convertView.findViewById(R.id.list_text);
        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
