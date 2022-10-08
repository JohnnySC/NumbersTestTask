package com.github.johnnysc.numberstesttask.main.presentation

import com.github.johnnysc.numberstesttask.numbers.presentation.BaseTest
import com.github.johnnysc.numberstesttask.random.WorkManagerWrapper
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author Asatryan on 01.10.2022
 */
class MainViewModelTest : BaseTest() {

    @Test
    fun `test navigation at start`() {
        val navigation = TestNavigationCommunication()
        val workManagerWrapper = TestWorkManagerWrapper()
        val mainViewModel = MainViewModel(workManagerWrapper, navigation)

        mainViewModel.init(true)
        assertEquals(1, navigation.count)
        assertEquals(NavigationStrategy.Replace(Screen.Numbers), navigation.strategy)
        assertEquals(1, workManagerWrapper.startCalledCount)

        mainViewModel.init(false)
        assertEquals(1, navigation.count)
        assertEquals(1, workManagerWrapper.startCalledCount)
    }

    private class TestWorkManagerWrapper : WorkManagerWrapper {
        var startCalledCount = 0

        override fun start() {
            startCalledCount++
        }
    }
}