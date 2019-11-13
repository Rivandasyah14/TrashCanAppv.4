package com.rivan.trashcanv4;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    public static final String SP_EMAIL = "spMail";
    public static final String SP_PASS = "spPass";
    public static final String SP_SIGNED = "signed";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor spEditor;

    public SharedPreferencesManager(Context context){
        sharedPreferences = context.getSharedPreferences(SP_EMAIL, Context.MODE_PRIVATE);
        spEditor = sharedPreferences.edit();
    }

    public void saveSpString(String keySp, String valueSp){
        spEditor.putString(keySp, valueSp);
        spEditor.commit();
    }

    public void saveSpBoolean(String keySp, Boolean valueSp){
        spEditor.putBoolean(keySp, valueSp);
        spEditor.commit();
    }

    public String getSpEmail(){
        return sharedPreferences.getString(SP_EMAIL, "");
    }

    public Boolean getSigned(){
        return sharedPreferences.getBoolean(SP_SIGNED, false);
    }


}
