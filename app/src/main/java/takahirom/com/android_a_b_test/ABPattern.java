package takahirom.com.android_a_b_test;

/**
 * Created by takahirom on 15/08/19.
 */
public class ABPattern {

    private final String name;
    private final int weight;

    public String getName() {
        return name;
    }

    public ABPattern(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public ABPattern(String name) {
        this(name, 1);
    }
}
