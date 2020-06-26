package com.wicak.projectuas;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
    public static final String PREF_NAME = "app_shared_preference";

    public SharedPreference() {
        super();
    }

    public void save(Context context, String key, String value) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.putString(key, value);
        editor.commit();
    }

    public String getValue(Context context, String key) {
        SharedPreferences settings;
        String value;

        settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        value = settings.getString(key, null);

        return value;
    }

    public void clearSharedPreference(Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.commit();
    }
}
