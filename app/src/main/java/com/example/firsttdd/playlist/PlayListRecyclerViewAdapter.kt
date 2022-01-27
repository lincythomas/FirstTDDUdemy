package com.example.firsttdd.playlist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.example.firsttdd.databinding.ItemPlayListBinding
import com.example.firsttdd.recylerviewdata.PlayListItem


class PlayListRecyclerViewAdapter(
    private val values: List<PlayListItem>
) : RecyclerView.Adapter<PlayListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemPlayListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.name.text = item.name
        holder.category.text = item.category
        holder.image.setImageResource(item.drawable)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemPlayListBinding) : RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.playlistName
        val category: TextView = binding.playlistCategory
        val image: AppCompatImageView = binding.playlistImage

        override fun toString(): String {
            return super.toString() + " '" + category.text + "'"
        }
    }

}