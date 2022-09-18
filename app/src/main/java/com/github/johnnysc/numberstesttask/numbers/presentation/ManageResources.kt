package com.github.johnnysc.numberstesttask.numbers.presentation

import android.content.Context
import androidx.annotation.StringRes

/**
 * @author Asatryan on 18.09.2022
 */
interface ManageResources {

    fun string(@StringRes id: Int): String

    class Base(private val context: Context) : ManageResources {
        override fun string(id: Int): String = context.getString(id)
    }
}