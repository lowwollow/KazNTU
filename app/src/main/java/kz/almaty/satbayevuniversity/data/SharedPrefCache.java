package kz.almaty.satbayevuniversity.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import kz.almaty.satbayevuniversity.data.entity.Language;

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

    public static void setLang(Language language1, String val, Activity activity){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("lang", language1.getLanguageCode());
        editor.apply();
    }

    public static String getLang(Activity activity){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        String lang = sharedPref.getString("lang", "");
        return lang;
    }
}
