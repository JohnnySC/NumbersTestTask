package com.github.johnnysc.numberstesttask.main.presentation

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * @author Asatryan on 01.10.2022
 */
interface NavigationStrategy {

    fun navigate(supportFragmentManager: FragmentManager, containerId: Int)

    abstract class Abstract(protected open val screen: Screen) : NavigationStrategy {

        override fun navigate(
            supportFragmentManager: FragmentManager,
            containerId: Int
        ) {
            supportFragmentManager.beginTransaction()
                .executeTransaction(containerId)
                .commit()
        }

        protected abstract fun FragmentTransaction.executeTransaction(
            containerId: Int
        ): FragmentTransaction
    }

    data class Replace(override val screen: Screen) : Abstract(screen) {

        override fun FragmentTransaction.executeTransaction(containerId: Int) =
            replace(containerId, screen.fragment().newInstance())
    }

    data class Add(override val screen: Screen) : Abstract(screen) {

        override fun FragmentTransaction.executeTransaction(containerId: Int) =
            screen.fragment().let {
                add(containerId, it.newInstance()).addToBackStack(it.simpleName)
            }
    }
}