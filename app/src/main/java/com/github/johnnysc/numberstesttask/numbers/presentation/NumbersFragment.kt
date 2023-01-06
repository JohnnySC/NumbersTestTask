package com.github.johnnysc.numberstesttask.numbers.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.numberstesttask.R
import com.github.johnnysc.numberstesttask.main.presentation.BaseFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

/**
 * @author Asatryan on 16.09.2022
 */
class NumbersFragment : BaseFragment<NumbersViewModel.Base>() {

    override val viewModelClass = NumbersViewModel.Base::class.java
    override val layoutId = R.layout.fragment_numbers

    private lateinit var inputEditText: BaseCustomTextInputEditText

    private val watcher = object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable?) = viewModel.clearError()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val factButton = view.findViewById<Button>(R.id.getFactButton)
        val randomButton = view.findViewById<Button>(R.id.randomFactButton)
        val inputLayout = view.findViewById<BaseCustomTextInputLayout>(R.id.textInputLayout)
        inputEditText = view.findViewById(R.id.inputEditText)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = NumbersAdapter(object : ClickListener {
            override fun click(item: NumberUi) = viewModel.showDetails(item)
        })
        recyclerView.adapter = adapter

        factButton.setOnClickListener {
            viewModel.fetchNumberFact(inputEditText.text.toString())
        }

        randomButton.setOnClickListener {
            viewModel.fetchRandomNumberFact()
        }

        viewModel.observeState(this) {
            it.apply(inputLayout, inputEditText)
        }

        viewModel.observeList(this) {
            adapter.map(it)
        }

        viewModel.observeProgress(this) {
            progressBar.visibility = it
        }

        viewModel.init(savedInstanceState == null)
    }

    override fun onResume() {
        inputEditText.addTextChangedListener(watcher)
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        inputEditText.removeTextChangedListener(watcher)
    }
}

abstract class SimpleTextWatcher : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
    override fun afterTextChanged(s: Editable?) = Unit
}