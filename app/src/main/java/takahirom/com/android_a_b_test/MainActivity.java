package takahirom.com.android_a_b_test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String RED = "red";
    public static final String GREEN = "green";
    public static final String BUTTON_COLOR = "button color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);

        final ABTest abTest = new ABTest.Builder(this)
                .withName(BUTTON_COLOR)
                .addPattern(new ABPattern(RED, 70))
                .addPattern(new ABPattern(GREEN, 30))
                .build();

        abTest.visit(new OnVisitListener() {
            @Override
            public void onVisit(ABPattern pattern) {
                // visit
                if (RED.equals(pattern.getName())) {
                    button.setBackgroundColor(Color.RED);
                    // sendLog("visit red")
                } else if (GREEN.equals(pattern.getName())) {
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
                ABTest.getBuiltInstance(MainActivity.this, BUTTON_COLOR).convert(new OnConvertionListener() {
                    @Override
                    public void onConvert(ABPattern pattern) {
                        // send conversion log
                        if (RED.equals(pattern.getName())) {
                            // sendLog("conversion red")
                        } else if (GREEN.equals(pattern.getName())) {
                            // sendLog("conversion green")
                        }
                    }
                });
            }
        });


    }

}
