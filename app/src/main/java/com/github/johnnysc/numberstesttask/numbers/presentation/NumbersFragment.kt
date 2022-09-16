package com.github.johnnysc.numberstesttask.numbers.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.github.johnnysc.numberstesttask.R
import com.github.johnnysc.numberstesttask.details.presentation.DetailsFragment
import com.github.johnnysc.numberstesttask.main.presentation.ShowFragment

/**
 * @author Asatryan on 16.09.2022
 */
class NumbersFragment : Fragment() {

    private var showFragment: ShowFragment = ShowFragment.Empty()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        showFragment = context as ShowFragment
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
        view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
        //todo refactor and remove hardcode
        view.findViewById<View>(R.id.getFactButton).setOnClickListener {
            showFragment.show(DetailsFragment.newInstance("some information about the random number hardcoded"))
        }
    }

    override fun onDetach() {
        super.onDetach()
        showFragment = ShowFragment.Empty()
    }
}