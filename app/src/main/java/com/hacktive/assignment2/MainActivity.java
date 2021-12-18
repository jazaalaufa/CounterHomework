package com.hacktive.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView angka;
    Button count;
    EditText input;
    private int mCount = 0;
    private static final String SAVED_TEXT_KEY = "SAVED_TEXT_KEY";
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        angka = (TextView) findViewById(R.id.tv_angka);
        count = (Button)   findViewById(R.id.btn_count);
        input = (EditText) findViewById(R.id.et_count);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        input.setText(prefs.getString(SAVED_TEXT_KEY,""));
        angka.setText(String.valueOf(mCount));
        count.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View v){
            mCount++;
            angka.setText(String.valueOf(mCount));
            }
        });
        if (savedInstanceState != null) {
            mCount = savedInstanceState.getInt("count");
            angka.setText(String.valueOf(mCount));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(SAVED_TEXT_KEY,input.getText().toString());
        editor.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVED_TEXT_KEY,input.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        input.setText(savedInstanceState.getString(SAVED_TEXT_KEY));
    }
}