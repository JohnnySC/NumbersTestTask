package com.github.johnnysc.numberstesttask.numbers.data.cloud

import com.github.johnnysc.numberstesttask.numbers.data.NumberData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author Asatryan on 02.10.2022
 */
class NumbersCloudDataSourceTest {

    @Test
    fun `test number`() = runBlocking {
        val apiHeader = RandomApiHeader.Mock("test")
        val service = MockNumbersService(apiHeader)
        val dataSource = NumbersCloudDataSource.Base(service, apiHeader)

        val actual = dataSource.number("1")
        val expected = NumberData("1", "fact about 1")
        assertEquals(expected, actual)
    }

    @Test
    fun `test random success`() = runBlocking {
        val apiHeader = RandomApiHeader.Mock("test")
        val service = MockNumbersService(apiHeader)
        val dataSource = NumbersCloudDataSource.Base(service, apiHeader)

        val actual = dataSource.randomNumber()
        val expected = NumberData("1", "fact about 1")
        assertEquals(expected, actual)
    }

    @Test(expected = IllegalStateException::class)
    fun `test random failed`(): Unit = runBlocking {
        val apiHeader = RandomApiHeader.Mock("test")
        val service = MockNumbersService(apiHeader)
        val emptyHeader = RandomApiHeader.Mock("")
        val dataSource = NumbersCloudDataSource.Base(service, emptyHeader)

        dataSource.randomNumber()
    }
}