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
import com.github.johnnysc.numberstesttask.numbers.domain.*
import com.github.johnnysc.numberstesttask.numbers.presentation.*

/**
 * @author Asatryan on 30.09.2022
 */
class NumbersModule(
    private val core: Core,
    private val provideRepository: ProvideNumbersRepository
) : Module<NumbersViewModel.Base> {

    override fun viewModel(): NumbersViewModel.Base {
        val repository = provideRepository.provideNumbersRepository()
        val communications = NumbersCommunications.Base(
            ProgressCommunication.Base(),
            NumbersStateCommunication.Base(),
            NumbersListCommunication.Base()
        )
        val mapper = NumbersResultMapper(communications, NumberUiMapper())
        val interactor = NumbersInteractor.Base(
            repository,
            HandleRequest.Base(
                HandleError.Base(core),
                repository
            ),
            core.provideNumberDetails()
        )
        return NumbersViewModel.Base(
            core.provideDispatchers(),
            NumbersInitialFeature(communications, mapper, interactor),
            NumbersFactFeature.Base(core, communications, mapper, interactor),
            RandomNumberFactFeature(interactor, communications, mapper),
            ShowDetails.Base(interactor, core.provideNavigation(), DetailsUi()),
            communications
        )
    }
}

interface ProvideNumbersRepository {

    fun provideNumbersRepository(): NumbersRepository

    class Base(private val core: Core) : ProvideNumbersRepository {

        override fun provideNumbersRepository(): NumbersRepository {
            val cacheDataSource = NumbersCacheDataSource.Base(
                core.provideDataBase().numbersDao(),
                NumberDataToCache()
            )
            return BaseNumbersRepository(
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
        }
    }
}