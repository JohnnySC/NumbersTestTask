package com.github.johnnysc.numberstesttask

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.johnnysc.numberstesttask.main.presentation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Asatryan on 01.10.2022
 */
@RunWith(AndroidJUnit4::class)
class CheckItemReplacedTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_history() {
        //1. enter 1
        onView(ViewMatchers.withId(R.id.inputEditText)).perform(ViewActions.typeText("1"))
        closeSoftKeyboard()

        onView(ViewMatchers.withId(R.id.getFactButton)).perform(ViewActions.click())

        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(0, R.id.titleTextView))
            .check(matches(withText("1")))
        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(0, R.id.subTitleTextView))
            .check(matches(withText("fact about 1")))

        //2. enter 2
        onView(ViewMatchers.withId(R.id.inputEditText)).perform(ViewActions.typeText("2"))
        closeSoftKeyboard()

        onView(ViewMatchers.withId(R.id.getFactButton)).perform(ViewActions.click())

        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(0, R.id.titleTextView))
            .check(matches(withText("2")))
        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(0, R.id.subTitleTextView))
            .check(matches(withText("fact about 2")))
        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(1, R.id.titleTextView))
            .check(matches(withText("1")))
        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(1, R.id.subTitleTextView))
            .check(matches(withText("fact about 1")))

        //3. enter 1 again
        onView(ViewMatchers.withId(R.id.inputEditText)).perform(ViewActions.typeText("1"))
        closeSoftKeyboard()

        onView(ViewMatchers.withId(R.id.getFactButton)).perform(ViewActions.click())

        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(0, R.id.titleTextView))
            .check(matches(withText("1")))
        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(0, R.id.subTitleTextView))
            .check(matches(withText("fact about 1")))

        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(1, R.id.titleTextView))
            .check(matches(withText("2")))
        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(1, R.id.subTitleTextView))
            .check(matches(withText("fact about 2")))

    }

}