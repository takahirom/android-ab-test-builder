package takahirom.com.android_a_b_test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum ButtonColorPattern {
        RED, GREEN,
    }

    public static final String BUTTON_COLOR = "button color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);

        final ABTest<ButtonColorPattern> abTest = new ABTest.Builder<ButtonColorPattern>(this)
                .withName(BUTTON_COLOR)
                .addPattern(new ABPattern<>(ButtonColorPattern.RED, 70))
                .addPattern(new ABPattern<>(ButtonColorPattern.GREEN, 30))
                .build();

        abTest.visit(new VisitDispatcher<ButtonColorPattern>() {
            @Override
            public void dispatch(ABPattern<ButtonColorPattern> pattern) {
                // visit
                if (pattern.isMatchPattern(ButtonColorPattern.RED)) {
                    button.setBackgroundColor(Color.RED);
                    // sendLog("visit red")
                } else if (pattern.isMatchPattern(ButtonColorPattern.GREEN)) {
                    button.setBackgroundColor(Color.GREEN);
                    // sendLog("visit green")
                }

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "clicked!", Toast.LENGTH_SHORT).show();
                // If ABTest already built,ABTest instance can created by name.
                ABTest.getBuiltInstance(MainActivity.this, BUTTON_COLOR).convert(new ConvertDispatcher<ButtonColorPattern>() {
                    @Override
                    public void convertDispatch(ABPattern<ButtonColorPattern> pattern) {
                        // send conversion log
                        if (pattern.isMatchPattern(ButtonColorPattern.RED)) {
                            // sendLog("conversion red")
                        } else if (pattern.isMatchPattern(ButtonColorPattern.GREEN)) {
                            // sendLog("conversion green")
                        }
                    }
                });
            }
        });


    }

}
