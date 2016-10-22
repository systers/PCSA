package com.peacecorps.pcsa.policies_glossary;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.peacecorps.pcsa.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
 * Custom Adapter for Expandable List View in GlossaryFragment
 * @author rohan
 * @since 2016-07-30
 */
public class GlossaryAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> dataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> dataChild;
    private ExpandableListView listView;

    public GlossaryAdapter(Context context, List<String> listDataHeader,
                           HashMap<String, List<String>> listChildData, ExpandableListView expandableListView) {
        this._context = context;
        this.dataHeader = listDataHeader;
        this.dataChild = listChildData;
        this.listView = expandableListView;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.dataChild.get(this.dataHeader.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_glossary_meaning, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.word_meaning);

        txtListChild.setText(Html.fromHtml(childText));
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.dataChild.get(this.dataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.dataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.dataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_glossary_item, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.word_title);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filter(String textEntered)
    {
        prepareListData(_context, dataHeader, dataChild);
        Iterator listIt = dataHeader.iterator();
        while (listIt.hasNext())
        {
            String next = (String) listIt.next();
            if(next.length() < textEntered.length() || !next.toUpperCase().startsWith(textEntered.toUpperCase())){
                listIt.remove();
            }
        }

        for(Iterator<Map.Entry<String, List<String>>> it = dataChild.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, List<String>> entry = it.next();
            if(!dataHeader.contains(entry.getKey())) {
                it.remove();
            }
        }
        notifyDataSetChanged();
    }

    public static void prepareListData(Context context,List<String> listDataHeader,HashMap<String, List<String>> listDataChild)
    {
        listDataChild.clear();
        listDataHeader.clear();
        for(int i =0; i<23;i++)
            listDataHeader.add("");
        Collections.copy(listDataHeader,Arrays.asList(context.getResources().getStringArray(R.array.dataheaders)));
        // Adding child data
        List<String> assault = new ArrayList<String>();
        assault.add(context.getString(R.string.asexual_assault));

        List<String> assailant = new ArrayList<String>();
        assailant.add(context.getString(R.string.assailant_info));

        List<String> burglary = new ArrayList<String>();
        burglary.add(context.getString(R.string.burglary_info));

        List<String> intervention = new ArrayList<String>();
        intervention.add(context.getString(R.string.intervention_info));

        List<String> phenomenon = new ArrayList<String>();
        phenomenon.add(context.getString(R.string.phenomenon_info));

        List<String> cyber = new ArrayList<String>();
        cyber.add(context.getString(R.string.cyber_info));

        List<String> danger = new ArrayList<String>();
        danger.add(context.getString(R.string.danger_info));

        List<String> mitigate = new ArrayList<String>();
        mitigate.add(context.getString(R.string.mitigation_info));

        List<String> pii = new ArrayList<String>();
        pii.add(context.getString(R.string.pii_info));

        List<String> rape = new ArrayList<String>();
        rape.add(context.getString(R.string.rape_info));

        List<String> risk = new ArrayList<String>();
        risk.add(context.getString(R.string.risk_info));

        List<String> rob = new ArrayList<String>();
        rob.add(context.getString(R.string.robbery_info));

        List<String> safe = new ArrayList<String>();
        safe.add(context.getString(R.string.safety_info));

        List<String> security = new ArrayList<String>();
        security.add(context.getString(R.string.security_info));

        List<String> sexual_assault = new ArrayList<String>();

        sexual_assault.add(context.getString(R.string.sexual_assault_info1));

        List<String> exploit = new ArrayList<String>();
        exploit.add(context.getString(R.string.exploitation_info));

        List<String> harass = new ArrayList<String>();
        harass.add(context.getString(R.string.harassment_info));

        List<String> misconduct = new ArrayList<String>();
        misconduct.add(context.getString(R.string.misconduct_info));

        List<String> threat = new ArrayList<String>();
        threat.add(context.getString(R.string.threat_info));

        List<String> predator = new ArrayList<String>();
        predator.add(context.getString(R.string.predator_info));

        List<String> stalk = new ArrayList<String>();
        stalk.add(context.getString(R.string.stalking_info));

        List<String> theft = new ArrayList<String>();
        theft.add(context.getString(R.string.theft_info));

        List<String> vulnerability = new ArrayList<String>();
        vulnerability.add(context.getString(R.string.vulnerability_info));

        listDataChild.put(listDataHeader.get(0), assault);
        listDataChild.put(listDataHeader.get(1), assailant);
        listDataChild.put(listDataHeader.get(2), burglary);
        listDataChild.put(listDataHeader.get(3), intervention);
        listDataChild.put(listDataHeader.get(4), phenomenon);
        listDataChild.put(listDataHeader.get(5), cyber);
        listDataChild.put(listDataHeader.get(6), danger);
        listDataChild.put(listDataHeader.get(7), mitigate);
        listDataChild.put(listDataHeader.get(8), pii);
        listDataChild.put(listDataHeader.get(9), rape);
        listDataChild.put(listDataHeader.get(10), risk);
        listDataChild.put(listDataHeader.get(11), rob);
        listDataChild.put(listDataHeader.get(12), safe);
        listDataChild.put(listDataHeader.get(13), security);
        listDataChild.put(listDataHeader.get(14), sexual_assault);
        listDataChild.put(listDataHeader.get(15), exploit);
        listDataChild.put(listDataHeader.get(16), harass);
        listDataChild.put(listDataHeader.get(17), misconduct);
        listDataChild.put(listDataHeader.get(18), predator);
        listDataChild.put(listDataHeader.get(19), threat);
        listDataChild.put(listDataHeader.get(20), stalk);
        listDataChild.put(listDataHeader.get(21), theft);
        listDataChild.put(listDataHeader.get(22), vulnerability);
    }
}