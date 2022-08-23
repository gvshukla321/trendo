package com.comfyapptech.trendo.ui.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.comfyapptech.libimgur.models.Image
import com.comfyapptech.trendo.R
import com.comfyapptech.trendo.databinding.ListItemGalleryImageBinding

class FeedRecyclerAdapter : ListAdapter<Image, FeedRecyclerAdapter.FeedViewHolder>(FeedDiffCallBack()) {

    class FeedViewHolder(val binding: ListItemGalleryImageBinding) : RecyclerView.ViewHolder(binding.root)

    private class FeedDiffCallBack : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.toString() == newItem.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val binding = ListItemGalleryImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val image = getItem(position)
        holder.binding.captionTextView.text = image.title
        holder.binding.imageView.load("https://i.imgur.com/${image.cover}.jpg"){
            placeholder(R.drawable.ic_placeholder_image)
            error(R.drawable.ic_placeholder_image)
        }
    }
}