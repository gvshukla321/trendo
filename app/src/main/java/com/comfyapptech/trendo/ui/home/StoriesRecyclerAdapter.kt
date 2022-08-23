package com.comfyapptech.trendo.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.comfyapptech.libimgur.models.Tag
import com.comfyapptech.trendo.databinding.ListItemStoryHeadBinding
import com.comfyapptech.trendo.ui.story.StoryActivity

class StoriesRecyclerAdapter : ListAdapter<Tag, StoriesRecyclerAdapter.StoriesViewHolder>(StoriesDiffCallBack()) {

    class StoriesViewHolder(val binding: ListItemStoryHeadBinding) : RecyclerView.ViewHolder(binding.root)

    class StoriesDiffCallBack : DiffUtil.ItemCallback<Tag>() {
        override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem.toString() == newItem.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesViewHolder {
        val binding = ListItemStoryHeadBinding.inflate(LayoutInflater.from(parent.context))
        return StoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
        val tag = getItem(position)
        holder.binding.storyHeadTextView.text = tag.displayName
        holder.binding.storyHeadImageView.load("https://i.imgur.com/${tag.backgroundHash}.jpg")
        holder.binding.root.apply {
            setOnClickListener {
                context.startActivity(
                    Intent(context, StoryActivity::class.java).apply {
                        putExtra("tag", tag.name)
                    }
                )
            }
        }
    }

}