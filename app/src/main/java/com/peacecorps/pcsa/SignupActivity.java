package com.peacecorps.pcsa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * This class registers the user name and country one-time when the app loads for the first time
 * @author rohan
 * @since 2016-07-24.
 */
public class SignupActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    private Toolbar toolbar;
    private Button loginButton;
    private EditText name;
    private Spinner country;
    private String selected_country;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_color));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SignupActivity.this);
        editor = sharedPreferences.edit();

        loginButton = (Button)findViewById(R.id.loginButton);
        name = (EditText)findViewById(R.id.edit_name);

        country = (Spinner) findViewById(R.id.spinner_country);
        String[] countries = getResources().getStringArray(R.array.countryArray);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, R.layout.textview, countries);
        adapter.setDropDownViewResource(R.layout.textview);
        country.setAdapter(adapter);
        country.setOnItemSelectedListener(this);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals(""))
                    Toast.makeText(SignupActivity.this, R.string.prompt_please,Toast.LENGTH_SHORT).show();
                else if(name.getText().toString().trim().equals("") || !name.getText().toString().matches("^[a-zA-Z ]*$"))
                    Toast.makeText(SignupActivity.this, R.string.prompt_please_valid,Toast.LENGTH_SHORT).show();
                else {
                    editor.putBoolean(getString(R.string.first_aide), false);
                    editor.putString(getString(R.string.key_country), selected_country);
                    editor.putString(getString(R.string.key_name), name.getText().toString().trim().replaceAll("[ ]+", " "));
                    editor.commit();
                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                    finish();
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selected_country = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
