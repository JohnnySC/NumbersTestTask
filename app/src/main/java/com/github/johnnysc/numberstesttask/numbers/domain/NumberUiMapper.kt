package com.github.johnnysc.numberstesttask.numbers.domain

import com.github.johnnysc.numberstesttask.numbers.presentation.NumberUi

class NumberUiMapper : NumberFact.Mapper<NumberUi> {
    override fun map(id: String, fact: String): NumberUi = NumberUi(id, fact)
}