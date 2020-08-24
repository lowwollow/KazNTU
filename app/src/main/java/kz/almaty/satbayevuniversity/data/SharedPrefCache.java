package kz.almaty.satbayevuniversity.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefCache {

    public SharedPrefCache() {
    }

    private final static String PREFS_NAME = "token";

    public void setStr(String key, String value, Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getStr(String key, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(key, "DNF");
    }
}