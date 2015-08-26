package com.takahirom.android_ab_test_sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.takahirom.android_abtest_builder.ABPattern;
import com.takahirom.android_abtest_builder.ABTest;
import com.takahirom.android_abtest_builder.ConvertDispatcher;
import com.takahirom.android_abtest_builder.VisitDispatcher;

public class MainActivity extends AppCompatActivity {

    enum ButtonColorPattern {
        RED, GREEN, YELLOW
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);

        final ABTest<ButtonColorPattern> buttonColorABTest = new ABTest.Builder<ButtonColorPattern>(this)
                .withClass(ButtonColorPattern.class)
                .addPattern(new ABPattern<>(ButtonColorPattern.RED, 80))
                .addPattern(new ABPattern<>(ButtonColorPattern.GREEN, 10))
                .addPattern(new ABPattern<>(ButtonColorPattern.YELLOW, 10))
                .buildIfFirstTime();

        buttonColorABTest.visit(new VisitDispatcher<ButtonColorPattern>() {
            @Override
            public void dispatch(ABPattern<ButtonColorPattern> pattern) {
                // visit
                switch (pattern.patternEnumValue) {
                    case RED:
                        button.setBackgroundColor(Color.RED);
                        // sendLog("visit red")
                        break;
                    case GREEN:
                        button.setBackgroundColor(Color.GREEN);
                        // sendLog("visit green")
                        break;
                    case YELLOW:
                        button.setBackgroundColor(Color.YELLOW);
                        // sendLog("visit yellow")
                        break;
                }
                Toast.makeText(MainActivity.this, "show:" + pattern.getName(), Toast.LENGTH_SHORT).show();

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If ABTest already built,ABTest instance can created by patternEnumValue.
                ABTest<ButtonColorPattern> builtInstance = ABTest.getBuiltInstance(MainActivity.this, ButtonColorPattern.class);
                if (builtInstance == null) {
                    // If not already built returns null;
                    return;
                }
                builtInstance.convert(new ConvertDispatcher<ButtonColorPattern>() {
                    @Override
                    public void dispatch(ABPattern<ButtonColorPattern> pattern) {
                        // send conversion log
                        switch (pattern.patternEnumValue) {
                            case RED:
                                // sendLog("conversion red")
                                break;
                            case GREEN:
                                // sendLog("conversion green")
                                break;
                            case YELLOW:
                                // sendLog("conversion yellow")
                                break;
                        }
                        Toast.makeText(MainActivity.this, "click:" + pattern.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

}
