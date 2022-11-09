package com.example.gamesuit.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gamesuit.databinding.ItemViewAboutBinding

class HelpAdapterAbout: RecyclerView.Adapter<HelpAdapterAbout.HelpViewHolderAbout>() {

    class HelpViewHolderAbout(binding: ItemViewAboutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpViewHolderAbout {
        val binding = ItemViewAboutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HelpViewHolderAbout(binding)
    }

    override fun onBindViewHolder(holder: HelpViewHolderAbout, position: Int) {
    }

    override fun getItemCount(): Int = 1
}