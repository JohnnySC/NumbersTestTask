package com.github.johnnysc.numberstesttask.details.sl

import com.github.johnnysc.numberstesttask.details.presentation.NumberDetailsViewModel
import com.github.johnnysc.numberstesttask.main.sl.Module
import com.github.johnnysc.numberstesttask.main.sl.ProvideNumberDetails

/**
 * @author Asatryan on 01.10.2022
 */
class NumberDetailsModule(
    private val provideNumberDetails: ProvideNumberDetails
) : Module<NumberDetailsViewModel> {

    override fun viewModel() = NumberDetailsViewModel(provideNumberDetails.provideNumberDetails())
}