package com.github.johnnysc.numberstesttask.numbers.presentation

/**
 * @author Asatryan on 18.09.2022
 */
sealed class UiState {

    abstract fun apply(
        inputLayout: CustomTextInputLayout,
        textInputEditText: CustomTextInputEditText
    )

    class Success : UiState() {

        override fun apply(
            inputLayout: CustomTextInputLayout,
            textInputEditText: CustomTextInputEditText
        ) = textInputEditText.showText("")
    }

    abstract class AbstractError(
        private val message: String,
        private val errorEnabled: Boolean
    ) : UiState() {

        override fun apply(
            inputLayout: CustomTextInputLayout,
            textInputEditText: CustomTextInputEditText
        ) = with(inputLayout) {
            changeErrorEnabled(errorEnabled)
            showError(message)
        }
    }

    data class ShowError(private val text: String) : AbstractError(text, true)
    class ClearError : AbstractError("", false)
}