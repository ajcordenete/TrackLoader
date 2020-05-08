package com.aljon.trackloader.ui.tracks

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aljon.trackloader.R
import com.aljon.trackloader.data.entities.Track
import com.aljon.trackloader.databinding.ListTrackItemBinding


class TrackAdapter(private val onItemSelectListener: OnItemSelectListener): ListAdapter<Track, TrackAdapter.ViewHolderAllTracks>(TrackDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAllTracks {
        return ViewHolderAllTracks(ListTrackItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderAllTracks, position: Int) {
        val track = getItem(position)
        holder.bind(track)

        holder.itemView.setOnClickListener {
            onItemSelectListener.onClick(track, holder.itemView.findViewById(R.id.artwork_image))
        }
    }

    class ViewHolderAllTracks(private val binding: ListTrackItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(track: Track) {
            binding.track = track
            binding.executePendingBindings()
        }
    }

    companion object TrackDiffUtil: DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem.trackId == newItem.trackId
        }
    }

    class OnItemSelectListener(val clickListener: (track: Track, imageView: ImageView) -> Unit ) {
        fun onClick(track: Track, imageView: ImageView) = clickListener(track, imageView)
    }

}
