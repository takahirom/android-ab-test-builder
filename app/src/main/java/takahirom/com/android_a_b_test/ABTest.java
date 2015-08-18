package takahirom.com.android_a_b_test;

import android.content.Context;
import android.view.View;

/**
 * Created by takahirom on 15/08/19.
 */
// TODO: Implementation
public class ABTest {
    private OnVisitListener OnVisitListener;

    private ABTest(){
    }

    public ABTest(Context context, String s) {

    }

    public void setOnVisitListener(OnVisitListener onVisitListener) {
        this.OnVisitListener = onVisitListener;
    }

    public void visit(OnVisitListener onVisitListener) {
    }

    public void convert(OnConvertionListener onConvertionListener) {
    }

    public static ABTest getBuiltInstance(Context context, String buttonColor) {
        return null;
    }

    public static class Builder {
        public Builder(Context context) {

        }

        public Builder withName(String s) {
            return null;
        }

        public Builder addPattern(ABPattern abPattern) {
            return null;
        }

        public ABTest build() {
            // TODO: distribute pattern
            return new ABTest();
        }
    }
}
