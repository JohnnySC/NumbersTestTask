package com.github.johnnysc.numberstesttask.details.presentation

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.github.johnnysc.numberstesttask.R
import com.github.johnnysc.numberstesttask.main.presentation.BaseFragment

/**
 * @author Asatryan on 16.09.2022
 */
class NumberDetailsFragment : BaseFragment<NumberDetailsViewModel>() {

    override val viewModelClass = NumberDetailsViewModel::class.java
    override val layoutId = R.layout.fragment_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val value = viewModel.read()
        view.findViewById<TextView>(R.id.detailsTextView).text = value
    }
}