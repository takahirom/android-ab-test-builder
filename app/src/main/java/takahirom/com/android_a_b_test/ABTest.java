package takahirom.com.android_a_b_test;

import android.content.Context;

/**
 * Created by takahirom on 15/08/19.
 */
// TODO: Implementation
public class ABTest<T> {
    private VisitDispatcher<T> VisitDispatcher;

    private ABTest(){
    }

    public ABTest(Context context, String s) {

    }

    public void setVisitDispatcher(VisitDispatcher visitDispatcher) {
        this.VisitDispatcher = visitDispatcher;
    }

    public void visit(VisitDispatcher<T> visitDispatcher) {
    }

    public void convert(ConvertDispatcher convertDispatcher) {
    }

    public static <T> ABTest<T> getBuiltInstance(Context context, String name) {
        return new ABTest<>();
    }

    public static class Builder<T> {
        public <T>Builder(Context context) {

        }

        public Builder<T> withName(String s) {
            return null;
        }

        public Builder<T> addPattern(ABPattern<T> abPattern) {
            return null;
        }

        public ABTest<T> build() {
            // TODO: distribute pattern
            return new ABTest<>();
        }
    }
}
