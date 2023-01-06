package com.github.johnnysc.numberstesttask.numbers.presentation

import com.github.johnnysc.numberstesttask.numbers.domain.NumberFact
import com.github.johnnysc.numberstesttask.numbers.domain.NumberUiMapper
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author Asatryan on 18.09.2022
 */
class NumbersResultMapperTest : BaseTest() {

    @Test
    fun test_error() {
        val communications = TestNumbersCommunications()
        val mapper = NumbersResultMapper(communications, NumberUiMapper())

        mapper.map("not empty message")

        assertEquals(UiState.ShowError("not empty message"), communications.stateCalledList[0])
    }

    @Test
    fun test_success_no_list() {
        val communications = TestNumbersCommunications()
        val mapper = NumbersResultMapper(communications, NumberUiMapper())

        mapper.map(emptyList())

        assertEquals(0, communications.timesShowList)
        assertEquals(true, communications.stateCalledList[0] is UiState.Success)
    }

    @Test
    fun test_success_with_list() {
        val communications = TestNumbersCommunications()
        val mapper = NumbersResultMapper(communications, NumberUiMapper())

        mapper.map(listOf(NumberFact("5", "fact 5")))

        assertEquals(true, communications.stateCalledList[0] is UiState.Success)
        assertEquals(1, communications.timesShowList)
        assertEquals(NumberUi("5", "fact 5"), communications.numbersList[0])
    }
}