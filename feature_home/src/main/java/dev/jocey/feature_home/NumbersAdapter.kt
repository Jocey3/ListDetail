package dev.jocey.feature_home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.jocey.feature_home.databinding.ItemNumberBinding
import dev.jocey.feature_home.model.NumberView
import javax.inject.Inject

class NumbersAdapter @Inject constructor() :
    RecyclerView.Adapter<NumbersAdapter.NumbersViewHolder>() {

    var onNumberClickCallback: ((NumberView) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumbersViewHolder {
        val viewBinding = ItemNumberBinding.inflate(LayoutInflater.from(parent.context))
        return NumbersViewHolder(viewBinding).apply {
            itemView.setOnClickListener {
                onNumberClickCallback?.invoke(differ.currentList[absoluteAdapterPosition])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NumbersViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    class NumbersViewHolder(private val binding: ItemNumberBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NumberView) {
            binding.tvTextNumber.text = "${data.number} - ${data.text}"
        }
    }

    val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<NumberView> =
            object : DiffUtil.ItemCallback<NumberView>() {

                override fun areItemsTheSame(
                    oldValue: NumberView, newValue: NumberView
                ): Boolean {
                    return oldValue.number == newValue.number

                }

                override fun areContentsTheSame(
                    oldValue: NumberView, newValue: NumberView
                ): Boolean {
                    return oldValue.number == newValue.number && oldValue.text == newValue.text
                }
            }
    }
}