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

    enum ButtonColorPatterns {
        RED, GREEN, YELLOW
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);

        final ABTest<ButtonColorPatterns> buttonColorABTest = new ABTest.Builder<ButtonColorPatterns>(this)
                .with("ButtonColorPatterns", ButtonColorPatterns.class)
                .addPattern(new ABPattern<>(ButtonColorPatterns.RED, 80))
                .addPattern(new ABPattern<>(ButtonColorPatterns.GREEN, 10))
                .addPattern(new ABPattern<>(ButtonColorPatterns.YELLOW, 10))
                .buildIfFirstTime();

        buttonColorABTest.visit(new VisitDispatcher<ButtonColorPatterns>() {
            @Override
            public void dispatch(ABPattern<ButtonColorPatterns> pattern) {
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
                ABTest<ButtonColorPatterns> builtInstance = ABTest.getBuiltInstance(MainActivity.this, "ButtonColorPatterns", ButtonColorPatterns.class);
                if (builtInstance == null) {
                    // If not already built returns null;
                    return;
                }
                builtInstance.convert(new ConvertDispatcher<ButtonColorPatterns>() {
                    @Override
                    public void dispatch(ABPattern<ButtonColorPatterns> pattern) {
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
