package com.github.johnnysc.numberstesttask.main.presentation

import com.github.johnnysc.numberstesttask.details.presentation.NumberDetailsFragment
import com.github.johnnysc.numberstesttask.numbers.presentation.NumbersFragment

sealed class Screen {

    abstract fun fragment(): Class<out BaseFragment<*>>

    object Details : Screen() {
        override fun fragment(): Class<out BaseFragment<*>> = NumberDetailsFragment::class.java
    }

    object Numbers : Screen() {
        override fun fragment(): Class<out BaseFragment<*>> = NumbersFragment::class.java
    }
}