package com.github.johnnysc.numberstesttask

import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers

/**
 * @author Asatryan on 02.10.2022
 */
abstract class Page {
    protected fun Int.view() = Espresso.onView(ViewMatchers.withId(this))
}