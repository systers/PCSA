package com.peacecorps.pcsa.circle_of_trust;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.peacecorps.pcsa.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Activity to add comrade phone numbers and save to sharedpreferences
 */
public class Trustees extends AppCompatActivity {
    Button okButton;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String comrade1 = "comrade1Key";
    public static final String comrade2 = "comrade2Key";
    public static final String comrade3 = "comrade3Key";
    public static final String comrade4 = "comrade4Key";
    public static final String comrade5 = "comrade5Key";
    public static final String comrade6 = "comrade6Key";

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    LinearLayout trustees;
    //the max number of comrade numbers possible
    private static final int MAX_COMRADES = 6;
    //maps INDEX NUMBERS to CURRENT WRITE STATUS (false = empty, true = hastext)
    Map<Integer, Boolean> current = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trustees);
        trustees = (LinearLayout) findViewById(R.id.comrades_box);
        okButton = (Button) findViewById(R.id.okButton);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        //add the first (default) layout
        addLayout();

        /**
         * Upon clicking, iterate through the current index numbers.
         * IF the EditText is displayed, save the index with the current information into sharedprefs
         * ELSE if the EditText is NOT displayed, save the index with an empty string
         */
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check each value inside the currently displayed edittexts, up to a max of six
                for (int i = 1; i <= MAX_COMRADES; i++) {
                    if (current.keySet().contains(i)) {
                        int editId = getResources().getIdentifier("comrade" + i + "EditText", "id", getPackageName());
                        //put the key and corresponding data into shared prefs
                        editor.putString("comrade" + i + "Key",
                                ((EditText) trustees.findViewById(editId)).getText().toString());
                    } else {
                        //if it is not available, then just add the empty string into sharedprefs
                        editor.putString("comrade" + i + "Key", "");
                    }
                }


                editor.apply();
                Toast.makeText(getApplicationContext(), getString(R.string.updated_phone_numbers), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Adds a layout dynamically based on the highest max possible value left.
     */
    private void addLayout() {
        LinearLayout layout = (LinearLayout) getLayoutInflater().inflate(R.layout.edittext_trustees, null);
        final EditText edit = (EditText) layout.findViewById(R.id.edit);

        int num = 0;
        //get the lowest index value that is currently not within the layout
        for (int i = 1; i <= MAX_COMRADES; i++) {
            if (!current.keySet().contains(i)) {
                num = i;
                break;
            }
        }

        //if there is a valid number, we continue adding our layout dynamically
        //else we don't add anymore because we've reached the max already
        if (num!=0) {
            //build id string
            int editId = getResources().getIdentifier("comrade" + num + "EditText", "id", getPackageName());
            int layoutId = getResources().getIdentifier("comradelayout" + num, "id", getPackageName());

            if (editId != 0) {
                //set the new edittext & linear layout ids
                edit.setId(editId);
                layout.setId(layoutId);

                //set starter text & hint
                edit.setHint("Comrade " + num);
                edit.setText("");
                //save the index number as a tag for future reference
                edit.setTag(num);

                //add an TextWatcher to keep track of future events that can add or subtract EditTexts
                edit.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    /**
                     * IF the text is changed to blank, check to see if we need to remove an EditText
                     * ELSE IF text is added, check to see if we need to ADD an EditText
                     *
                     * Status is based on the boolean value in the map, with false = empty and true = hasText
                     * @param editable
                     */
                    @Override
                    public void afterTextChanged(Editable editable) {

                        if (editable.toString().equals("")) {
                            //edit the status value in the map
                            boolean remove = false;
                            int num = (int) edit.getTag();
                            current.put(num, false);

                            //check to see if
                            // 1) there are MULTIPLE FALSE entries and
                            // 2) if this is the highest one, remove it
                            //if so, remove this
                            for (Map.Entry<Integer, Boolean> entry : current.entrySet()) {
                                //check for false entries
                                //if there is one higher than our current value, we use that instead
                                //if all false entries are below us, we just use num
                                if (!entry.getValue()) {
                                    if (entry.getKey() > num) {
                                        num = entry.getKey();
                                        remove = true;
                                        break;
                                    } else if (entry.getKey() < num) {
                                        remove = true;
                                        break;
                                    }
                                }
                            }

                            //remove
                            if (remove) {
                                removeLayout(num);
                            }

                        } else {
                            //if there is text inside, we update the write status to TRUE
                            boolean add = true;
                            current.put((int) edit.getTag(), true);

                            //check to see if there is at least one current edittext without data in it
                            //iterate through current, check for the true/false tag
                            Iterator it = current.entrySet().iterator();
                            while (it.hasNext()) {
                                Map.Entry<Integer, Boolean> entry = (Map.Entry<Integer, Boolean>) it.next();
                                //if there is a false one, that means there is currently one entry without anything in it
                                //which means we don't need to add a new one and we set the add marker to false
                                if (!entry.getValue()) {
                                    add = false;
                                    break;
                                }

                            }
                            //if the flag is true
                            if (add) {
                                // add a new edittext since all are currently full
                                addLayout();
                            }
                        }
                    }
                });

                //add the index number to the list of current entries
                current.put(num, false);
            }

            //add the layout
            trustees.addView(layout);
        }
    }

    /**
     * Given an index number, remove the layout and all corresponding information in the map.
     * @param num
     */
    private void removeLayout(int num) {
        try {
            //remove the parent layout from the container
            int layoutId = getResources().getIdentifier("comradelayout" + num, "id", getPackageName());
            LinearLayout layout = (LinearLayout) trustees.findViewById(layoutId);
            trustees.removeView(layout);

            //remove the entry
            current.remove(num);
        } catch (NullPointerException e) {
            e.printStackTrace();
            Log.i(this.getClass().toString(), "Could not remove layout");
        }

    }


}
