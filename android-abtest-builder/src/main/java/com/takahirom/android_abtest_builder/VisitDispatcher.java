package com.takahirom.android_abtest_builder;

/**
 * Created by takahirom on 15/08/19.
 */
public interface VisitDispatcher<T extends Enum<T>> {
    void dispatch(ABPattern<T> pattern);
}
