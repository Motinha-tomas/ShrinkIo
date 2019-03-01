package com.example.shrinkio;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference {
    static final String PREF_USER_NAME = "username";
    static SharedPreferences getSharedPreferences(Context ctx){
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }
    public static void setUSerName(Context ctx, String username)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, username);
        editor.commit();
    }
    public static String getUserName(Context ctx)
    {
     return getSharedPreferences(ctx).getString(PREF_USER_NAME,"");
    }
}
