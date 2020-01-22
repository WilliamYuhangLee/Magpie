package com.example.Magpie;

import android.accounts.AccountManager;
import android.content.ContentProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    Context context;
    SharedPreferences sp;

    private String name;

    public void setUsername(String username)
    {
        this.name = username;
        sp.edit().putString("username", name).commit();
    }

    public String getUsername()
    {
        return sp.getString("username", "");
    }
}
