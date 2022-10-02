package com.github.johnnysc.numberstesttask

import androidx.test.espresso.Espresso.pressBack
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
class NavigationTest : BaseTest() {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun details_navigation() {
        val numbersPage = NumbersPage()
        numbersPage.run {
            input.view().typeText("10")
            getFactButton.view().click()

            recycler.run {
                viewInRecycler(0, titleItem).checkText("10")
                viewInRecycler(0, subTitleItem).checkText("fact about 10")
                viewInRecycler(0, subTitleItem).click()
            }
        }

        DetailsPage().details.view().checkText("10\n\nfact about 10")

        pressBack()

        numbersPage.run {
            recycler.run {
                viewInRecycler(0, titleItem).checkText("10")
                viewInRecycler(0, subTitleItem).checkText("fact about 10")
            }
        }
    }
}