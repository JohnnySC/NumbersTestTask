package com.github.johnnysc.numberstesttask.main.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * @author Asatryan on 01.10.2022
 */
interface NavigationStrategy {

    fun navigate(supportFragmentManager: FragmentManager, containerId: Int)

    abstract class Abstract(protected val fragment: Fragment) : NavigationStrategy {

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

    class Replace(fragment: Fragment) : Abstract(fragment) {
        override fun FragmentTransaction.executeTransaction(containerId: Int) =
            replace(containerId, fragment)
    }

    class Add(fragment: Fragment) : Abstract(fragment) {

        override fun FragmentTransaction.executeTransaction(containerId: Int) =
            add(containerId, fragment)
                .addToBackStack(fragment.javaClass.simpleName)
    }
}