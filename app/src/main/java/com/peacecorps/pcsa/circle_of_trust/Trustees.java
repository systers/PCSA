package com.peacecorps.pcsa.circle_of_trust;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.peacecorps.pcsa.R;


public class Trustees extends ActionBarActivity {

    EditText comrade1editText;
    EditText comrade2editText;
    EditText comrade3editText;
    EditText comrade4editText;
    EditText comrade5editText;
    EditText comrade6editText;
    Button okButton;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String comrade1 = "comrade1Key";
    public static final String comrade2 = "comrade2Key";
    public static final String comrade3 = "comrade3Key";
    public static final String comrade4 = "comrade4Key";
    public static final String comrade5 = "comrade5Key";
    public static final String comrade6 = "comrade6Key";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trustees);
        comrade1editText = (EditText) findViewById(R.id.comrade1EditText);
        comrade2editText = (EditText) findViewById(R.id.comrade2EditText);
        comrade3editText = (EditText) findViewById(R.id.comrade3EditText);
        comrade4editText = (EditText) findViewById(R.id.comrade4EditText);
        comrade5editText = (EditText) findViewById(R.id.comrade5EditText);
        comrade6editText = (EditText) findViewById(R.id.comrade6EditText);
        okButton = (Button) findViewById(R.id.okButton);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 *This portion of code saves different comrades numbers.
                 *Since we don't have these numbers at hand, I decided to comment it.

                String com1  = comrade1editText.getText().toString();
                String com2  = comrade2editText.getText().toString();
                String com3  = comrade3editText.getText().toString();
                String com4  = comrade4editText.getText().toString();
                String com5  = comrade5editText.getText().toString();
                String com6  = comrade6editText.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(comrade1, com1);
                editor.putString(comrade2, com2);
                editor.putString(comrade3, com3);
                editor.putString(comrade4, com4);
                editor.putString(comrade5, com5);
                editor.putString(comrade6, com6);
                editor.commit();

                */
                Toast.makeText(getApplicationContext(),"Comrades phone numbers has been updated", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_trustees, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
