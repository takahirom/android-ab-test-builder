package com.takahirom.android_ab_test_sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.takahirom.android_abtest_builder.ABPattern;
import com.takahirom.android_abtest_builder.ABTest;
import com.takahirom.android_abtest_builder.ConvertDispatcher;
import com.takahirom.android_abtest_builder.VisitDispatcher;

/**
 * Using Enum Field Sample
 */
public class EnumFieldMainActivity extends AppCompatActivity {

    enum ButtonColorPatterns {
        RED("red", Color.RED), GREEN("green", Color.GREEN), YELLOW("yellow", Color.YELLOW);

        private final String patternName;
        private final int color;

        ButtonColorPatterns(String patternName, @ColorInt int color) {
            this.patternName = patternName;
            this.color = color;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);

        final ABTest<ButtonColorPatterns> buttonColorABTest = new ABTest.Builder<ButtonColorPatterns>(this)
                .with("ButtonColorPatterns", ButtonColorPatterns.class)
                .addPattern(new ABPattern<>(ButtonColorPatterns.RED, 60))
                .addPattern(new ABPattern<>(ButtonColorPatterns.GREEN, 20))
                .addPattern(new ABPattern<>(ButtonColorPatterns.YELLOW, 20))
                .buildIfFirstTime();

        buttonColorABTest.visit(new VisitDispatcher<ButtonColorPatterns>() {
            @Override
            public void dispatch(ABPattern<ButtonColorPatterns> pattern) {
                // visit
                button.setBackgroundColor(pattern.patternEnumValue.color);
                // You can send visit log with pattern.patternEnumValue.patternName
                // sendVisitLog(pattern.patternEnumValue.patternName);

                Toast.makeText(EnumFieldMainActivity.this, "show:" + pattern.getName(), Toast.LENGTH_SHORT).show();

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If ABTest already built,ABTest instance can created by patternEnumValue.
                ABTest<ButtonColorPatterns> builtInstance = ABTest.getBuiltInstance(EnumFieldMainActivity.this, "ButtonColorPatterns", ButtonColorPatterns.class);
                if (builtInstance == null) {
                    // If not already built returns null;
                    return;
                }
                builtInstance.convert(new ConvertDispatcher<ButtonColorPatterns>() {
                    @Override
                    public void dispatch(ABPattern<ButtonColorPatterns> pattern) {
                        // You can send conversion log with pattern.patternEnumValue.patternName
                        // sendConversionLog(pattern.patternEnumValue.patternName);

                        Toast.makeText(EnumFieldMainActivity.this, "click:" + pattern.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

}
