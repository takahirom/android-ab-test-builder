package com.takahirom.android_abtest_builder;

/**
 * Created by takahirom on 15/08/19.
 */
public class ABPattern<T extends Enum<T>> {

    public final T patternEnumValue;
    public final int weight;

    public ABPattern(T patternEnumValue, int weight) {
        this.patternEnumValue = patternEnumValue;
        this.weight = weight;
    }

    public ABPattern(T patternEnumValue) {
        this(patternEnumValue, 1);
    }

    public String getName() {
        return patternEnumValue.name();
    }

    public boolean isMatchPattern(T name) {
        return this.patternEnumValue.equals(name);
    }
}
