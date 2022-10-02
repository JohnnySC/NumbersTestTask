package com.github.johnnysc.numberstesttask

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.johnnysc.numberstesttask.main.presentation.MainActivity
import org.junit.Rule
import org.junit.runner.RunWith

/**
 * @author Asatryan on 02.10.2022
 */
@RunWith(AndroidJUnit4::class)
abstract class BaseTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    protected fun Int.view() = onView(withId(this))

    protected fun ViewInteraction.typeText(value: String) {
        perform(ViewActions.typeText(value))
        closeSoftKeyboard()
    }

    protected fun ViewInteraction.checkText(value: String) {
        check(matches(withText(value)))
    }

    protected fun ViewInteraction.click() {
        perform(ViewActions.click())
    }

    protected fun Int.viewInRecycler(position: Int, viewId: Int): ViewInteraction =
        onView(RecyclerViewMatcher(this).atPosition(position, viewId))
}