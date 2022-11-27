package com.example.soru1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class SettingsActivity extends AppCompatActivity {

    Button maxarti, maxeksi, minarti, mineksi;
    EditText maxt, mint;
    CheckBox ses, titresim;
    Integer ust, alt;
    Integer sayacmaks = 0;
    Integer sayacmin = 0;

    private SharedPref settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        settings = (SharedPref) getApplication();
        initWidgets();
        initEditTextListener();
        loadSharedPreferences();
    }

    private void initWidgets() {
        maxarti = (Button) findViewById(R.id.maxarti);
        maxeksi = (Button) findViewById(R.id.maxeksi);
        maxt = (EditText) findViewById(R.id.maxt);

        minarti = (Button) findViewById(R.id.minarti);
        mineksi = (Button) findViewById(R.id.mineksi);
        mint = (EditText) findViewById(R.id.mint);

        titresim = (CheckBox) findViewById(R.id.titresim);
        ses = (CheckBox) findViewById(R.id.ses);
    }

    private void loadSharedPreferences() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        Integer sayacmaks = sharedPreferences.getInt(SharedPref.USTLIMIT, 10);
        settings.setustLimit(String.valueOf(sayacmaks));
        Integer sayacmin = sharedPreferences.getInt(SharedPref.ALTLIMIT, -10);
        settings.setustLimit(String.valueOf(sayacmin));
        Boolean ses = sharedPreferences.getBoolean(SharedPref.SES, false);
        settings.setustLimit(String.valueOf(ses));
        Boolean titresim = sharedPreferences.getBoolean(SharedPref.TITRESIM, false);
        settings.setustLimit(String.valueOf(titresim));
    }

    private void initEditTextListener() {
        maxarti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sayacmaks++;
                maxt.setText(String.valueOf(sayacmaks));
                SharedPreferences.Editor editor = getSharedPreferences(SharedPref.PREFERENCES, MODE_PRIVATE).edit();
                editor.putString(SharedPref.USTLIMIT, settings.getUstLimit());
                editor.apply();
            }
        });
        maxeksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sayacmaks--;
                maxt.setText(String.valueOf(sayacmaks));
                SharedPreferences.Editor editor = getSharedPreferences(SharedPref.PREFERENCES, MODE_PRIVATE).edit();
                editor.putString(SharedPref.USTLIMIT, settings.getUstLimit());
                editor.apply();
            }
        });
        minarti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sayacmin++;
                mint.setText(String.valueOf(sayacmin));
                SharedPreferences.Editor editor = getSharedPreferences(SharedPref.PREFERENCES, MODE_PRIVATE).edit();
                editor.putString(SharedPref.ALTLIMIT, settings.getaltLimit());
                editor.apply();
            }
        });
        mineksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sayacmin--;
                mint.setText(String.valueOf(sayacmin));
                SharedPreferences.Editor editor = getSharedPreferences(SharedPref.PREFERENCES, MODE_PRIVATE).edit();
                editor.putString(SharedPref.ALTLIMIT, settings.getaltLimit());
                editor.apply();
                //updateView();
            }
        });
        ses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ses.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences(SharedPref.PREFERENCES, MODE_PRIVATE).edit();
                    editor.putString(SharedPref.SES, settings.getSound());
                    editor.apply();
                }
            }
        });
        titresim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titresim.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences(SharedPref.PREFERENCES, MODE_PRIVATE).edit();
                    editor.putString(SharedPref.TITRESIM, settings.getVibrate());
                    editor.apply();
                }
            }
        });
    }

    /*private void updateView() {
        final int altlimit = sayacmin;
        final int ustlimit = sayacmaks;
        mint.setText(String.valueOf(sayacmin));
        maxt.setText(String.valueOf(sayacmaks));
    }*/
}