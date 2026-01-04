package com.vltv.play

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SearchResultAdapter(
    private val onClick: (SearchResultItem) -> Unit
) : ListAdapter<SearchResultItem, SearchResultAdapter.VH>(SearchDiffCallback()) {

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val tvName: TextView = v.findViewById(R.id.tvName)
        val imgPoster: ImageView = v.findViewById(R.id.imgPoster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vod, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        holder.tvName.text = item.title

        Glide.with(holder.itemView.context)
            .load(item.iconUrl ?: R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.imgPoster)

        holder.itemView.isFocusable = true
        holder.itemView.isClickable = true
        holder.itemView.setOnFocusChangeListener { _, hasFocus ->
            holder.itemView.alpha = if (hasFocus) 1.0f else 0.8f
        }

        holder.itemView.setOnClickListener { onClick(item) }
    }
}

class SearchDiffCallback : DiffUtil.ItemCallback<SearchResultItem>() {
    override fun areItemsTheSame(old: SearchResultItem, new: SearchResultItem) = old.id == new.id
    override fun areContentsTheSame(old: SearchResultItem, new: SearchResultItem) = old == new
}
