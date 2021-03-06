package com.takahirom.android_abtest_builder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

/**
 * This is main class of ABTest.
 * You can create ABTest instance by ABTest.Builder
 */
public class ABTest<T extends Enum<T>> {

    private final ABPattern<T> pattern;

    private ABTest(ABPattern<T> pattern) {
        this.pattern = pattern;
    }

    public void visit(VisitDispatcher<T> visitDispatcher) {
        visitDispatcher.dispatch(pattern);
    }

    public void convert(ConvertDispatcher<T> convertDispatcher) {
        convertDispatcher.dispatch(pattern);
    }

    /**
     * If ABTest already built,ABTest instance can created by patternEnumValue.
     * @param context
     * @param clazz clazz is Pattern Enum class
     * @param <T> T is Pattern Enum type
     * @return ABTest instance. If ABTest doesn't built yet, returns null
     */
    @Nullable
    public static <T extends Enum<T>> ABTest<T> getBuiltInstance(Context context, String name, Class<T> clazz) {
        return getBuiltInstance(new ABTestPreferences(context), name, clazz);
    }

    @Nullable
    protected static <T extends Enum<T>> ABTest<T> getBuiltInstance(ABTestPreferences abTestPreferences, String name, Class<T> clazz) {
        int index = abTestPreferences.getPattern(name);
        if (index == -1) {
            // Now, not exist pattern
            return null;
        }
        EnumSet<T> enumSet = EnumSet.allOf(clazz);
        ABPattern<T> abPattern = new ABPattern<>((T) enumSet.toArray()[index]);
        return new ABTest<>(abPattern);
    }

    public static class Builder<T extends Enum<T>> {
        private final Context context;
        // Visible for testing
        protected ABTestPreferences abTestPreferences;
        private List<ABPattern<T>> patterns = new ArrayList<>();
        private Class<T> clazz;
        private String name;

        public Builder(Context context) {
            abTestPreferences = new ABTestPreferences(context);
            this.context = context;
        }

        public Builder<T> with(String name, Class<T> clazz) {
            this.name = name;
            this.clazz = clazz;
            return this;
        }

        public Builder<T> addPattern(ABPattern<T> abPattern) {
            patterns.add(abPattern);
            return this;
        }

        public ABTest<T> buildIfFirstTime() {
            ABTest<T> builtInstance = ABTest.getBuiltInstance(context, name ,clazz);
            if (builtInstance != null) {
                return builtInstance;
            }
            return build();
        }

        @NonNull
        public ABTest<T> build() {
            ABPattern<T> resultPattern = chooseRandomABPattern();
            abTestPreferences.putPattern(name, resultPattern.patternEnumValue.ordinal());
            return new ABTest<>(resultPattern);
        }

        private ABPattern<T> chooseRandomABPattern() {
            int sum = 0;
            for (ABPattern<T> pattern : patterns) {
                sum += pattern.weight;
            }
            Random r = new Random();
            int randomInt = r.nextInt(sum);

            int countUp = 0;
            ABPattern<T> resultPattern = null;
            for (ABPattern<T> pattern : patterns) {
                countUp += pattern.weight;
                if (randomInt < countUp) {
                    resultPattern = pattern;
                    break;
                }
            }
            return resultPattern;
        }
    }
}
