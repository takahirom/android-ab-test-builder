package takahirom.com.android_a_b_test;

/**
 * Created by takahirom on 15/08/19.
 */
public class ABPattern<T> {

    private final T name;
    private final int weight;

    public T getName() {
        return name;
    }

    public ABPattern(T name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public ABPattern(T name) {
        this(name, 1);
    }

    public boolean isMatchPattern(T name) {
        return name.equals(name);
    }
}
