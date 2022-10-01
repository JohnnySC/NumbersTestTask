package com.github.johnnysc.numberstesttask.numbers.presentation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.numberstesttask.R
import com.github.johnnysc.numberstesttask.details.presentation.DetailsFragment
import com.github.johnnysc.numberstesttask.main.presentation.ShowFragment
import com.github.johnnysc.numberstesttask.main.sl.ProvideViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

/**
 * @author Asatryan on 16.09.2022
 */
class NumbersFragment : Fragment() {

    private var showFragment: ShowFragment = ShowFragment.Empty()

    private lateinit var viewModel: NumbersViewModel
    private lateinit var inputEditText: TextInputEditText

    private val watcher = object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable?) = viewModel.clearError()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        showFragment = context as ShowFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity() as ProvideViewModel).provideViewModel(
            NumbersViewModel::class.java, this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_numbers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val factButton = view.findViewById<Button>(R.id.getFactButton)
        val randomButton = view.findViewById<Button>(R.id.randomFactButton)
        val inputLayout = view.findViewById<TextInputLayout>(R.id.textInputLayout)
        inputEditText = view.findViewById(R.id.inputEditText)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val mapper = DetailsUi()
        val adapter = NumbersAdapter(object : ClickListener {
            override fun click(item: NumberUi) =
                showFragment.show(DetailsFragment.newInstance(item.map(mapper)))
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

    override fun onDetach() {
        super.onDetach()
        showFragment = ShowFragment.Empty()
    }
}

abstract class SimpleTextWatcher : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
    override fun afterTextChanged(s: Editable?) = Unit
}