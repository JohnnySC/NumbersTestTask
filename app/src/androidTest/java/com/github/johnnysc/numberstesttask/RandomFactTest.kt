package com.github.johnnysc.numberstesttask

import org.junit.Test

/**
 * @author Asatryan on 02.10.2022
 */
class RandomFactTest : BaseTest() {

    @Test
    fun test() = NumbersPage().run {
        randomButton.click()
        recycler.run {
            viewInRecycler(0, titleItem).checkText("1")
            viewInRecycler(0, subTitleItem).checkText("fact about 1")
        }
        randomButton.click()
        recycler.run {
            viewInRecycler(0, titleItem).checkText("2")
            viewInRecycler(0, subTitleItem).checkText("fact about 2")
            viewInRecycler(1, titleItem).checkText("1")
            viewInRecycler(1, subTitleItem).checkText("fact about 1")
        }

        input.typeText("1")
        getFactButton.click()

        recycler.run {
            viewInRecycler(0, titleItem).checkText("1")
            viewInRecycler(0, subTitleItem).checkText("fact about 1")
            viewInRecycler(1, titleItem).checkText("2")
            viewInRecycler(1, subTitleItem).checkText("fact about 2")
        }
    }
}