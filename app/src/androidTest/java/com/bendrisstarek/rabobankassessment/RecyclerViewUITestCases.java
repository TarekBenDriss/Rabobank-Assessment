package com.bendrisstarek.rabobankassessment;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.bendrisstarek.rabobankassessment.modules.main.MainActivity;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;


/**
 * A test class for testing several actions on the recyclerVIew
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RecyclerViewUITestCases {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
            MainActivity.class,
            true,
            true);


    /**
     * Test the visibility of the recyclerView
     */
    @Test
    public void testRecyclerVisibility() {
        Espresso.onView(ViewMatchers.withId(R.id.csvContentRecycler))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(activityRule.getActivity().getWindow().getDecorView())))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    /**
     * Test the click on the recyclerView
     */
    @Test
    public void testCaseForRecyclerClick() {
        Espresso.onView(ViewMatchers.withId(R.id.csvContentRecycler))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(activityRule.getActivity().getWindow().getDecorView())))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
    }

    /**
     * Test the scroll of the recyclerView
     */
    @Test
    public void testCaseForRecyclerScroll() {

        // Get total item of RecyclerView
        RecyclerView recyclerView = activityRule.getActivity().findViewById(R.id.csvContentRecycler);
        int itemCount = recyclerView.getAdapter().getItemCount();

        // Scroll to end of page with position
        Espresso.onView(ViewMatchers.withId(R.id.csvContentRecycler))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.is(activityRule.getActivity().getWindow().getDecorView())))
                .perform(RecyclerViewActions.scrollToPosition(itemCount - 1));
    }

}
