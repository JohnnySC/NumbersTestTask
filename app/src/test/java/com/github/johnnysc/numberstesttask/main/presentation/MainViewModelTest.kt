package com.github.johnnysc.numberstesttask.main.presentation

import com.github.johnnysc.numberstesttask.numbers.presentation.BaseTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author Asatryan on 01.10.2022
 */
class MainViewModelTest : BaseTest() {

    @Test
    fun `test navigation at start`() {
        val navigation = TestNavigationCommunication()
        val mainViewModel = MainViewModel(navigation)

        mainViewModel.init(true)
        assertEquals(1, navigation.count)
        assertEquals(NavigationStrategy.Replace(Screen.Numbers), navigation.strategy)

        mainViewModel.init(false)
        assertEquals(1, navigation.count)
    }
}