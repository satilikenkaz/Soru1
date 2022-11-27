package com.example.soru1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txv;
    Button settings;
    int currentValue;
    int UstLimit = 10;
    int altLimit = -10;
    boolean Vib;
    boolean Sound;
    SharedPref sharedPref;
    Vibrator vibrator = null;
    MediaPlayer player = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txv = (TextView) findViewById(R.id.tx);
        settings = findViewById(R.id.settings);

        Context context = getApplicationContext();
        //sharedPref = context.getSharedPreferences(context.getPackageName(), context.MODE_PRIVATE);
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        player = MediaPlayer.create(context, R.raw.err);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    public void OnPlusButtonClicked(View view){
        valueUpdate(+1);
    }

    public void OnMinusButtonClicked(View view){
        valueUpdate(-1);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_DOWN) {
                    valueUpdate(+5);
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {
                    valueUpdate(-5);
                    }
                return true;
                }
            return super.dispatchKeyEvent(event);
        }

    private void getPreferences() {
        //currentValue = sharedPref.currentValue;
        UstLimit = Integer.valueOf(sharedPref.getUstLimit());
        altLimit = Integer.valueOf(sharedPref.getaltLimit());
        Vib = Boolean.getBoolean(sharedPref.getVibrate());
        Sound = Boolean.getBoolean((sharedPref.getSound()));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void valueUpdate(int step){
        if(step < 0){
            if(currentValue + step < altLimit){
                currentValue = altLimit;
                player.start();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                }
            }else
                currentValue += step;
        }else{
            if(currentValue + step > UstLimit){
                currentValue = UstLimit;
                player.start();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                }
            }else{
                currentValue += step;
            }
        }
        txv.setText(String.valueOf(currentValue));
    }
}