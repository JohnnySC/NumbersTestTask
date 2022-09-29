package com.github.johnnysc.numberstesttask.numbers.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.numberstesttask.R

/**
 * @author Asatryan on 29.09.2022
 */
class NumbersAdapter(private val clickListener: ClickListener) :
    RecyclerView.Adapter<NumberViewHolder>(), Mapper.Unit<List<NumberUi>> {

    private val list = mutableListOf<NumberUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NumberViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.number_layout, parent, false),
        clickListener
    )

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount() = list.size

    override fun map(source: List<NumberUi>) {
        val diff = DiffUtilCallback(list, source)
        val result = DiffUtil.calculateDiff(diff)
        list.clear()
        list.addAll(source)
        result.dispatchUpdatesTo(this)
    }
}

class NumberViewHolder(view: View, private val clickListener: ClickListener) :
    RecyclerView.ViewHolder(view) {

    private val title = itemView.findViewById<TextView>(R.id.titleTextView)
    private val subTitle = itemView.findViewById<TextView>(R.id.subTitleTextView)

    fun bind(model: NumberUi) {
        model.map(title, subTitle)
        itemView.setOnClickListener { clickListener.click(model) }
    }
}

interface ClickListener {
    fun click(item: NumberUi)
}

class DiffUtilCallback(
    private val oldList: List<NumberUi>,
    private val newList: List<NumberUi>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].map(newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].equals(newList[newItemPosition])
}