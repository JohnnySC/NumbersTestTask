package com.github.johnnysc.numberstesttask.main.sl

import com.github.johnnysc.numberstesttask.main.presentation.MainViewModel

/**
 * @author Asatryan on 01.10.2022
 */
class MainModule(private val provideNavigation: ProvideNavigation) : Module<MainViewModel> {

    override fun viewModel() = MainViewModel(provideNavigation.provideNavigation())
}