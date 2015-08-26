package takahirom.com.android_a_b_test;

/**
 * Created by takahirom on 15/08/19.
 */
interface VisitDispatcher<T extends Enum<T>> {
    void dispatch(ABPattern<T> pattern);
}
