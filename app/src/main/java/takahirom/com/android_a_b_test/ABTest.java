package takahirom.com.android_a_b_test;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by takahirom on 15/08/19.
 */
// TODO: Implementation
public class ABTest<T> {

    private ABTest(){
    }

    public ABTest(Context context, String s) {

    }

    public void visit(VisitDispatcher<T> visitDispatcher) {
    }

    public void convert(ConvertDispatcher<T> convertDispatcher) {
    }

    public static <T> ABTest<T> getBuiltInstance(Context context, String name) {
        return new ABTest<>();
    }

    public static class Builder<T> {

        private final Context context;
        private String name;
        private List<ABPattern<T>> patterns = new ArrayList<>();

        public Builder(Context context) {
            this.context = context;
        }

        public Builder<T> withName(String name) {
            this.name = name;
            return this;
        }

        public Builder<T> addPattern(ABPattern<T> abPattern) {
            patterns.add(abPattern);
            return this;
        }

        public ABTest<T> build() {
            // TODO: distribute pattern
            return new ABTest<>();
        }
    }
}
