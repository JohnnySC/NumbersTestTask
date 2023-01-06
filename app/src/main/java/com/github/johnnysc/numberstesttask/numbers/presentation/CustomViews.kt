package com.github.johnnysc.numberstesttask.numbers.presentation

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

/**
 * @author Asatryan on 06.01.2023
 */
interface CustomTextInputLayout {

    fun changeErrorEnabled(enabled: Boolean)

    fun showError(errorMessage: String)
}

class BaseCustomTextInputLayout : TextInputLayout, CustomTextInputLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun changeErrorEnabled(enabled: Boolean) {
        isErrorEnabled = enabled
    }

    override fun showError(errorMessage: String) {
        error = errorMessage
    }
}

interface CustomTextInputEditText {

    fun showText(text: String)
}

class BaseCustomTextInputEditText : TextInputEditText, CustomTextInputEditText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun showText(text: String) {
        setText(text)
    }
}