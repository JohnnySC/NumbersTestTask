package com.github.johnnysc.numberstesttask.numbers.presentation

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar

/**
 * @author Asatryan on 06.01.2023
 */
class CustomProgressBar : ProgressBar {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onSaveInstanceState(): Parcelable {
        val superState = super.onSaveInstanceState()!!
        val ss = CustomProgressBarState(superState)
        ss.isVisible = visibility == View.VISIBLE
        return ss
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val ss = state as CustomProgressBarState
        super.onRestoreInstanceState(ss.superState)

        visibility = if (ss.isVisible) View.VISIBLE else View.GONE
    }
}

class CustomProgressBarState : View.BaseSavedState {
    var isVisible = true

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel) : super(parcelIn) {
        isVisible = parcelIn.readInt() != 0
    }

    override fun writeToParcel(out: Parcel?, flags: Int) {
        super.writeToParcel(out, flags)
        out?.writeInt(if (isVisible) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CustomProgressBarState> {
        override fun createFromParcel(parcel: Parcel): CustomProgressBarState {
            return CustomProgressBarState(parcel)
        }

        override fun newArray(size: Int): Array<CustomProgressBarState?> {
            return arrayOfNulls(size)
        }
    }
}