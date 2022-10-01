package com.github.johnnysc.numberstesttask.numbers.presentation

import android.view.View
import com.github.johnnysc.numberstesttask.main.presentation.NavigationStrategy
import com.github.johnnysc.numberstesttask.numbers.domain.NumberFact
import com.github.johnnysc.numberstesttask.numbers.domain.NumberUiMapper
import com.github.johnnysc.numberstesttask.numbers.domain.NumbersInteractor
import com.github.johnnysc.numberstesttask.numbers.domain.NumbersResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * @author Asatryan on 17.09.2022
 */
class NumbersViewModelTest : BaseTest() {

    private lateinit var navigation: TestNavigationCommunication
    private lateinit var viewModel: NumbersViewModel
    private lateinit var communications: TestNumbersCommunications
    private lateinit var interactor: TestNumbersInteractor
    private lateinit var manageResources: TestManageResources

    @Before
    fun init() {
        navigation = TestNavigationCommunication()
        communications = TestNumbersCommunications()
        interactor = TestNumbersInteractor()
        manageResources = TestManageResources()
        val detailsMapper = TestUiMapper()
        viewModel =
            NumbersViewModel.Base(
                HandleNumbersRequest.Base(
                    TestDispatchersList(),
                    communications,
                    NumbersResultMapper(communications, NumberUiMapper())
                ),
                manageResources,
                communications,
                interactor,
                navigation,
                detailsMapper
            )
    }

    /**
     * Initial test
     * At start fetch data and show it
     * then try to get some data successfully
     * then re-init and check the result
     */
    @Test
    fun `test init and re-init`() = runBlocking {
        interactor.changeExpectedResult(NumbersResult.Success())
        viewModel.init(isFirstRun = true)

        assertEquals(View.VISIBLE, communications.progressCalledList[0])
        assertEquals(1, interactor.initCalledList.size)

        assertEquals(2, communications.progressCalledList.size)
        assertEquals(View.GONE, communications.progressCalledList[1])

        assertEquals(1, communications.stateCalledList.size)
        assertEquals(true, communications.stateCalledList[0] is UiState.Success)

        assertEquals(0, communications.numbersList.size)
        assertEquals(0, communications.timesShowList)

        //get some data
        interactor.changeExpectedResult(NumbersResult.Failure("no internet connection"))
        viewModel.fetchRandomNumberFact()

        assertEquals(View.VISIBLE, communications.progressCalledList[2])

        assertEquals(1, interactor.fetchAboutRandomNumberCalledList.size)

        assertEquals(4, communications.progressCalledList.size)
        assertEquals(View.GONE, communications.progressCalledList[3])

        assertEquals(2, communications.stateCalledList.size)
        assertEquals(UiState.ShowError("no internet connection"), communications.stateCalledList[1])
        assertEquals(0, communications.timesShowList)

        viewModel.init(isFirstRun = false)
        assertEquals(4, communications.progressCalledList.size)
        assertEquals(2, communications.stateCalledList.size)
        assertEquals(0, communications.timesShowList)
    }

    /**
     * Try to get information about empty number
     */
    @Test
    fun `fact about empty number`() = runBlocking {
        manageResources.makeExpectedAnswer("entered number is empty")
        viewModel.fetchNumberFact("")

        assertEquals(0, interactor.fetchAboutNumberCalledList.size)

        assertEquals(0, communications.progressCalledList.size)

        assertEquals(1, communications.stateCalledList.size)
        assertEquals(
            UiState.ShowError("entered number is empty"),
            communications.stateCalledList[0]
        )

        assertEquals(0, communications.timesShowList)
    }

    /**
     * Try to get information about some number
     */
    @Test
    fun `fact about some number`() = runBlocking {
        interactor.changeExpectedResult(
            NumbersResult.Success(listOf(NumberFact("45", "fact about 45")))
        )
        viewModel.fetchNumberFact("45")

        assertEquals(View.VISIBLE, communications.progressCalledList[0])

        assertEquals(1, interactor.fetchAboutNumberCalledList.size)
        assertEquals(
            NumbersResult.Success(listOf(NumberFact("45", "fact about 45"))),
            interactor.fetchAboutNumberCalledList[0]
        )

        assertEquals(2, communications.progressCalledList.size)
        assertEquals(View.GONE, communications.progressCalledList[1])

        assertEquals(1, communications.stateCalledList.size)
        assertEquals(true, communications.stateCalledList[0] is UiState.Success)

        assertEquals(1, communications.timesShowList)
        assertEquals(NumberUi("45", "fact about 45"), communications.numbersList[0])
    }

    @Test
    fun `test clear error`() {
        viewModel.clearError()

        assertEquals(1, communications.stateCalledList.size)
        assertEquals(true, communications.stateCalledList[0] is UiState.ClearError)
    }

    @Test
    fun `test navigation details`() {
        viewModel.showDetails(NumberUi("0", "fact"))

        assertEquals("0 fact", interactor.details)
        assertEquals(1, navigation.count)
        assertEquals(true, navigation.strategy is NavigationStrategy.Add)
    }

    private class TestManageResources : ManageResources {

        private var string: String = ""

        fun makeExpectedAnswer(expected: String) {
            string = expected
        }

        override fun string(id: Int): String = string
    }

    private class TestNumbersInteractor : NumbersInteractor {

        private var result: NumbersResult = NumbersResult.Success()

        val initCalledList = mutableListOf<NumbersResult>()
        val fetchAboutNumberCalledList = mutableListOf<NumbersResult>()
        val fetchAboutRandomNumberCalledList = mutableListOf<NumbersResult>()

        var details: String = ""

        fun changeExpectedResult(newResult: NumbersResult) {
            result = newResult
        }

        override fun saveDetails(details: String) {
            this.details = details
        }

        override suspend fun init(): NumbersResult {
            initCalledList.add(result)
            return result
        }

        override suspend fun factAboutNumber(number: String): NumbersResult {
            fetchAboutNumberCalledList.add(result)
            return result
        }

        override suspend fun factAboutRandomNumber(): NumbersResult {
            fetchAboutRandomNumberCalledList.add(result)
            return result
        }
    }

    private class TestDispatchersList(
        private val dispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
    ) : DispatchersList {

        override fun io(): CoroutineDispatcher = dispatcher
        override fun ui(): CoroutineDispatcher = dispatcher
    }

    private class TestUiMapper : NumberUi.Mapper<String> {
        override fun map(id: String, fact: String): String = "$id $fact"
    }
}