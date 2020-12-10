package co.com.ceiba.mobile.pruebadeingreso.view;

import android.support.test.runner.AndroidJUnit4;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.ceiba.mobile.pruebadeingreso.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
@Rule
    public ActivityTestRule<MainActivity> mActivityRule =
        new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureTextChangesWork(){

        onView(withId(R.id.editTextSearch))
                .perform(typeText("Juan"));
        onView(withId(R.id.recyclerViewSearchResults)).perform(click());
        onView(withId(R.id.recyclerViewSearchResults)).check(matches(withText("List is empty")));
    }
}