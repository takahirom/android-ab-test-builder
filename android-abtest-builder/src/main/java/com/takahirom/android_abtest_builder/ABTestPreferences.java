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
        sharedPreferences = context.getSharedPreferences("ab_test_preference", Modifier.PRIVATE);
    }

    public void putPattern (String patternsName, int patternName) {
        sharedPreferences.edit().putInt(patternsName, patternName).apply();
    }

    public int getPattern (String patternsName) {
        return sharedPreferences.getInt(patternsName, -1);
    }
}
