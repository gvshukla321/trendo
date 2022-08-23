package com.comfyapptech.trendo.ui.story

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.load
import coil.request.ImageRequest
import com.comfyapptech.libimgur.models.Image
import com.comfyapptech.trendo.databinding.PageItemStoryBinding

class StoryPagerAdapter : ListAdapter<Image, StoryPagerAdapter.StoryViewHolder>(StoryDiffCallback()) {

    class StoryViewHolder(val binding: PageItemStoryBinding) : RecyclerView.ViewHolder(binding.root)

    class StoryDiffCallback : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.toString() == newItem.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding = PageItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val image = getItem(position)
        val imgUrl = if (image?.isAlbum == true && image.imagesCount != 0) {
            image.images!![0].link!!
        } else {
            image.link
        }

        imgUrl?.let {
            holder.binding.storyImageView.load(imgUrl)
            holder.binding.imageUrlTextView.text = imgUrl
        }
        cacheNext(position, holder.binding.storyImageView)
    }

    private fun cacheNext(position: Int, imageView: ImageView) {
        val image = try { getItem(position + 1) } catch (e: Exception) { null }

        val imgUrl = if (image?.isAlbum == true && image.imagesCount != 0) {
            image.images!![0].link!!
        } else {
            image?.link
        }
        imgUrl?.let {
            val request = ImageRequest.Builder(imageView.context)
                .data(imgUrl)
                .build()
            Coil.imageLoader(imageView.context).enqueue(request)
        }
    }

}