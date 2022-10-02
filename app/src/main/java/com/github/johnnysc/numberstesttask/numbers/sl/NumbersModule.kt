package com.github.johnnysc.numberstesttask.numbers.sl

import com.github.johnnysc.numberstesttask.main.sl.Core
import com.github.johnnysc.numberstesttask.main.sl.Module
import com.github.johnnysc.numberstesttask.numbers.data.BaseNumbersRepository
import com.github.johnnysc.numberstesttask.numbers.data.HandleDataRequest
import com.github.johnnysc.numberstesttask.numbers.data.HandleDomainError
import com.github.johnnysc.numberstesttask.numbers.data.NumberDataToDomain
import com.github.johnnysc.numberstesttask.numbers.data.cache.NumberDataToCache
import com.github.johnnysc.numberstesttask.numbers.data.cache.NumbersCacheDataSource
import com.github.johnnysc.numberstesttask.numbers.data.cloud.NumbersCloudDataSource
import com.github.johnnysc.numberstesttask.numbers.data.cloud.NumbersService
import com.github.johnnysc.numberstesttask.numbers.domain.HandleError
import com.github.johnnysc.numberstesttask.numbers.domain.HandleRequest
import com.github.johnnysc.numberstesttask.numbers.domain.NumberUiMapper
import com.github.johnnysc.numberstesttask.numbers.domain.NumbersInteractor
import com.github.johnnysc.numberstesttask.numbers.presentation.*

/**
 * @author Asatryan on 30.09.2022
 */
class NumbersModule(private val core: Core) : Module<NumbersViewModel.Base> {

    override fun viewModel(): NumbersViewModel.Base {
        val communications = NumbersCommunications.Base(
            ProgressCommunication.Base(),
            NumbersStateCommunication.Base(),
            NumbersListCommunication.Base()
        )
        val cacheDataSource = NumbersCacheDataSource.Base(
            core.provideDataBase().numbersDao(),
            NumberDataToCache()
        )
        val repository = BaseNumbersRepository(
            NumbersCloudDataSource.Base(
                core.service(NumbersService::class.java),
                core.provideRandomApiHeader()
            ),
            cacheDataSource,
            HandleDataRequest.Base(
                cacheDataSource,
                NumberDataToDomain(),
                HandleDomainError()
            ),
            NumberDataToDomain()
        )
        return NumbersViewModel.Base(
            HandleNumbersRequest.Base(
                core.provideDispatchers(),
                communications,
                NumbersResultMapper(communications, NumberUiMapper())
            ),
            core,
            communications,
            NumbersInteractor.Base(
                repository,
                HandleRequest.Base(
                    HandleError.Base(core),
                    repository
                ),
                core.provideNumberDetails()
            ),
            core.provideNavigation(),
            DetailsUi()
        )
    }
}