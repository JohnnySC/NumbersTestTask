package com.github.johnnysc.numberstesttask.numbers.presentation

/**
 * @author Asatryan on 18.09.2022
 */
sealed class UiState {

    //todo views on fragment

    class Success : UiState() {

    }

    data class Error(private val message: String) : UiState() {
    }
}