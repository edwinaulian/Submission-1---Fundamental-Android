package com.storyApp.edwin.mainStoryApp.view.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.storyApp.edwin.mainStoryApp.databinding.ItemStoryBinding
import com.storyApp.edwin.mainStoryApp.model.ListStory

class MainAdapter : RecyclerView.Adapter<MainAdapter.StoryViewHolder>() {

    private val list = ArrayList<ListStory>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val view = ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryViewHolder(view)
    }

    inner class StoryViewHolder(val binding: ItemStoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListStory) {
            binding.apply {
                Glide.with(itemView)
                    .load(item.photoUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .into(tvUser)
                tvUsername.text = item.name
                Log.d("edwin", "${item.name}" )
            }
        }
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}