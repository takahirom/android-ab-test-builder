# android-ab-test-builder
Simple tool which help you to implement A/B Test.

## Download
Comming soon.

## Code
### Preparation
Prepare enum.

```java
enum ButtonColorPatterns {
    RED, GREEN, YELLOW
}
```

### Build ABTest instance

```java
// Show button RED : GREEN : YELLOW = 80 : 10 : 10
final ABTest<ButtonColorPatterns> buttonColorABTest = new ABTest.Builder<ButtonColorPatterns>(this)
        .with("ButtonColorPatterns", ButtonColorPatterns.class)
        .addPattern(new ABPattern<>(ButtonColorPatterns.RED, 80))
        .addPattern(new ABPattern<>(ButtonColorPatterns.GREEN, 10))
        .addPattern(new ABPattern<>(ButtonColorPatterns.YELLOW, 10))
        .buildIfFirstTime();
```

### Send visit log

```java
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
```

### Send click log

```java
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
```

## License

This project is released under the Apache License, Version 2.0.

* [The Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
