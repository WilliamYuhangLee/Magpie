package com.example.Magpie;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {

    Context context;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    private static final String PREF_NAME = "MagpiePref";
    private static final int PRIVATE_MODE = 0;


    public Session(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    private static final String KEY_USERNAME = "username";
    private static final String KEY_LOGIN_STATUS = "login_status";

    public void login(String username) {
        editor.putString(KEY_USERNAME, username);
        editor.putBoolean(KEY_LOGIN_STATUS, true);
        editor.commit();
    }

    public String getCurrentUsername() {
        return pref.getString(KEY_USERNAME, null);
    }

    public void logout(String username) {
        editor.putString(KEY_USERNAME, null);
        editor.putBoolean(KEY_LOGIN_STATUS, false);
        editor.commit();
    }
}
