package com.caper.priceapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.caper.priceapp.databinding.PriceItemBinding
import com.caper.priceapp.entities.PriceItem

/**
 * [ListAdapter] for showing price items on main screen
 */
class PriceItemListAdapter: ListAdapter<PriceItem, PriceItemListAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PriceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    object DiffCallback: DiffUtil.ItemCallback<PriceItem>() {
        override fun areItemsTheSame(oldItem: PriceItem, newItem: PriceItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: PriceItem, newItem: PriceItem): Boolean =
            oldItem.name == newItem.name && oldItem.price == newItem.price && oldItem.qrUrl == newItem.qrUrl && oldItem.thumbnail == newItem.thumbnail
    }

    class ViewHolder(private val binding: PriceItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PriceItem) {
            binding.name.text = item.name
            binding.price.text = item.price
        }
    }
}