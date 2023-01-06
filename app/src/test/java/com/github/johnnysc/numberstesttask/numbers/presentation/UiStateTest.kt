package com.github.johnnysc.numberstesttask.numbers.presentation

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author Asatryan on 06.01.2023
 */
class UiStateTest {

    @Test
    fun `test success`() {
        val success = UiState.Success()
        val input = FakeInputLayout()
        val editText = FakeInputEditText()
        success.apply(input, editText)
        assertEquals(true, editText.showTextCalled)
        assertEquals("", editText.showTextCalledWithArgument)
        assertEquals(false, input.showErrorCalled)
        assertEquals(false, input.changeErrorEnabledCalled)
    }

    @Test
    fun `test show error`() {
        val showError = UiState.ShowError("testErrorMessage")
        val input = FakeInputLayout()
        val editText = FakeInputEditText()
        showError.apply(input, editText)
        assertEquals(true, input.showErrorCalled)
        assertEquals("testErrorMessage", input.showErrorCalledWithArgument)
        assertEquals(true, input.changeErrorEnabledCalledWithArgument)
        assertEquals(true, input.changeErrorEnabledCalled)
        assertEquals(false, editText.showTextCalled)
    }

    @Test
    fun `test clear error`() {
        val showError = UiState.ClearError()
        val input = FakeInputLayout()
        val editText = FakeInputEditText()
        showError.apply(input, editText)
        assertEquals(true, input.showErrorCalled)
        assertEquals("", input.showErrorCalledWithArgument)
        assertEquals(false, input.changeErrorEnabledCalledWithArgument)
        assertEquals(true, input.changeErrorEnabledCalled)
        assertEquals(false, editText.showTextCalled)
    }
}

private class FakeInputLayout : CustomTextInputLayout {
    var changeErrorEnabledCalled = false
    var changeErrorEnabledCalledWithArgument = false
    override fun changeErrorEnabled(enabled: Boolean) {
        changeErrorEnabledCalled = true
        changeErrorEnabledCalledWithArgument = enabled
    }

    var showErrorCalled = false
    var showErrorCalledWithArgument = ""
    override fun showError(errorMessage: String) {
        showErrorCalled = true
        showErrorCalledWithArgument = errorMessage
    }
}

private class FakeInputEditText : CustomTextInputEditText {
    var showTextCalled = false
    var showTextCalledWithArgument = ""
    override fun showText(text: String) {
        showTextCalled = true
        showTextCalledWithArgument = text
    }
}