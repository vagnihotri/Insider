package com.insider.exercise.presentation.view.activity;

import android.content.pm.PackageManager;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.TextView;

import com.insider.exercise.presentation.R;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class EventsActivityTest {

    @Rule
    public final ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(
            MainActivity.class);
    @Test
    public void testViewElements() throws PackageManager.NameNotFoundException {
        onView(Matchers.allOf(isAssignableFrom(TextView.class),withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText(R.string.title_activity_main)));
    }

}
