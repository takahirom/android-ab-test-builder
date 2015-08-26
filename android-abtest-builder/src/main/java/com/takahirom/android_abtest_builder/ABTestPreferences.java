package com.takahirom.android_abtest_builder;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.Modifier;

/**
 * Created by takahirom on 15/08/27.
 */
public class ABTestPreferences {

    private final SharedPreferences sharedPreferences;

    public ABTestPreferences(Context context){
        sharedPreferences = context.getSharedPreferences("sharedPreferences", Modifier.PRIVATE);
    }

    public void putPattern (String patternsName, String patternName) {
        sharedPreferences.edit().putString(patternsName, patternName).apply();
    }

    public String getPattern (String patternsName) {
        return sharedPreferences.getString(patternsName, null);
    }
}
