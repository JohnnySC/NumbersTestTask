package com.github.johnnysc.numberstesttask.numbers.presentation

import android.widget.TextView

/**
 * @author Asatryan on 18.09.2022
 */
data class NumberUi(private val id: String, private val fact: String) {

    fun map(head: TextView, subTitle: TextView) {
        head.text = id
        subTitle.text = fact
    }
}