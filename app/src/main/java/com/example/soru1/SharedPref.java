package com.example.soru1;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref extends Application {
    public static final String PREFERENCES = "preferences";
    public static final String USTLIMIT = "ustLimit";
    public static final String ALTLIMIT = "altLimit";
    public static final String SES = "sound";
    public static final String TITRESIM = "vibrate";

    private String ustLimit;

    public String getUstLimit(){
        return ustLimit;
    }

    public void setustLimit(String ustLimit){
        this.ustLimit = ustLimit;
    }

    private String altLimit;

    public String getaltLimit(){
        return altLimit;
    }

    public void setaltLimit(String altLimit){
        this.altLimit = altLimit;
    }

    private String sound;

    public String getSound(){
        return sound;
    }

    public void setSound(String sound){
        this.sound = sound;
    }
    private String vibrate;

    public String getVibrate(){
        return vibrate;
    }

    public void setTitresim(String vibrate){
        this.vibrate = vibrate;
    }
}
