package com.github.johnnysc.numberstesttask.details.presentation

import androidx.lifecycle.ViewModel
import com.github.johnnysc.numberstesttask.details.data.NumberFactDetails

/**
 * @author Asatryan on 01.10.2022
 */
class NumberDetailsViewModel(
    private val data: NumberFactDetails.Read
) : ViewModel(), NumberFactDetails.Read {

    override fun read(): String = data.read()
}