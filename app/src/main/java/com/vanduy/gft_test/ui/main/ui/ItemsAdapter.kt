package com.vanduy.gft_test.ui.main.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.vanduy.gft_test.databinding.ListItemBinding
import com.vanduy.gft_test.ui.main.data.PhotoItem

class ItemsAdapter(private var itemList: ArrayList<PhotoItem>) :
    RecyclerView.Adapter<ItemsAdapter.ItemViewHodler>() {

    inner class ItemViewHodler(private val binding: ListItemBinding) : ViewHolder(binding.root) {
        fun bind(item: PhotoItem) {
            binding.id.text = item.id
            Glide.with(binding.root.context)
                .load(item.photo)
                .centerCrop()
                .into(binding.photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHodler {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHodler(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHodler, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(it: List<PhotoItem>?) {
        it?.let {
            itemList.clear()
            itemList.addAll(it)
            notifyDataSetChanged()
        }
    }
}